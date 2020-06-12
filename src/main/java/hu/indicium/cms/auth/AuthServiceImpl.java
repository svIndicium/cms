package hu.indicium.cms.auth;

import hu.indicium.cms.auth.request.TokenRequest;
import hu.indicium.cms.auth.response.TokenResponse;
import hu.indicium.cms.user.User;
import hu.indicium.cms.user.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;



    public AuthServiceImpl(UserRepository userRepository, TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
    }


    @Override
    public TokenResponse loginUser(TokenRequest tokenRequest) {
        //Check if the user exists
        User user = userRepository.findUserByEmail(tokenRequest.getUsername());

        //Check if password is correct
        if(BCrypt.checkpw(tokenRequest.password, user.getPasswordHash())){
            //User is validated
            return tokenProvider.generateToken(user);
        }else{
            //User entered wrong password
            return null;
        }
    }
}
