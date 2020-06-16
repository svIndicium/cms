package hu.indicium.cms.auth;

import hu.indicium.cms.auth.request.RefreshTokenRequest;
import hu.indicium.cms.auth.request.TokenRequest;
import hu.indicium.cms.auth.response.TokenResponse;
import hu.indicium.cms.user.User;
import hu.indicium.cms.user.UserRepository;
import io.jsonwebtoken.Claims;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final RefreshRepository refreshRepository;


    public AuthServiceImpl(UserRepository userRepository, TokenProvider tokenProvider, RefreshRepository refreshRepository) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.refreshRepository = refreshRepository;
    }


    @Override
    public TokenResponse loginUser(TokenRequest tokenRequest) {
        //Check if the user exists
        User user = userRepository.findUserByEmail(tokenRequest.getUsername());
        if(user == null){
            return null;
        }

        //Check if password is correct
        if(BCrypt.checkpw(tokenRequest.getPassword(), user.getPasswordHash())){
            //User is validated
            return tokenProvider.generateToken(user);
        }else{
            //User entered wrong password
            return null;
        }
    }

    @Override
    public TokenResponse refreshUser(RefreshTokenRequest refreshTokenRequest) {
        String email = tokenProvider.getUsernameFromToken(refreshTokenRequest.getToken());
        User user = findByEmail(email);

        if(user == null){
            return null;
        }


        //Find refreshToken
        RefreshToken refreshToken = refreshRepository.findRefreshTokenByToken(refreshTokenRequest.getRefreshToken());
        if(refreshToken == null){
            return null;
        }
        //Invalidate refresh
        refreshToken.setInvalidated(true);

        //Validate token
        String tokenId = tokenProvider.getClaimFromToken(refreshTokenRequest.getToken(), Claims::getId);
        if(!refreshToken.getJwtId().equals(tokenId)){
            return null;
        }
        //Generate new token
        return tokenProvider.generateToken(user);
    }


    private User findByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
}
