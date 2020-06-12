package hu.indicium.cms.auth;

import hu.indicium.cms.auth.response.TokenResponse;
import hu.indicium.cms.user.User;
import hu.indicium.cms.user.UserMapper;
import hu.indicium.cms.user.dto.UserDTO;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private int expiration;

    public TokenProvider() {
    }

    public TokenResponse generateToken(User user) {
        UserDTO userDTO = UserMapper.map(user);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, expiration);
        String token = Jwts.builder()
                .setSubject(userDTO.getEmail())
                .setExpiration(c.getTime())
                .claim("role", userDTO.getRole())
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        return new TokenResponse(token);
    }

    public UserDTO getPrincipal(String token) {
        if (token != null) {
            try {
                Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
                String email = claims.getSubject();
                String role = claims.get("role", String.class);
                return new UserDTO(email, role);
            } catch (SignatureException e) {
                return null;
            }
        }
        return null;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.split(" ")[1];
        }
        return null;
    }

    UsernamePasswordAuthenticationToken getAuthentication(final String token) {
        Collection<? extends GrantedAuthority> authorities = null;
        final JwtParser jwtParser = Jwts.parser().setSigningKey(secretKey);

        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

        final Claims claims = claimsJws.getBody();

        authorities =
                Arrays.stream(claims.get("role").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(getUsernameFromToken(token), null, authorities);
    }


    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
                username.equals(userDetails.getUsername())
                        && !isTokenExpired(token));
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}