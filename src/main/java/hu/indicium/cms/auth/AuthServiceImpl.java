package hu.indicium.cms.auth;

import hu.indicium.cms.auth.request.TokenRequest;
import hu.indicium.cms.auth.response.TokenResponse;
import hu.indicium.cms.user.User;
import hu.indicium.cms.user.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private int expiration;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public TokenResponse loginUser(TokenRequest tokenRequest) {
        //Check if the user exists
        User user = userRepository.findUserByEmail(tokenRequest.getUsername());

        //Check if password is correct
        if(BCrypt.checkpw(tokenRequest.password, user.getPasswordHash())){
            //User is validated
            return generateToken(user);
        }else{
            //User entered wrong password
            return null;
        }
    }

    private TokenResponse generateToken(User user){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, expiration);
        String token = Jwts.builder()
                .setSubject(user.getEmail())
                .setExpiration(c.getTime())
                .claim("role", user.getRole() )
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        return new TokenResponse(token);
    }
}
