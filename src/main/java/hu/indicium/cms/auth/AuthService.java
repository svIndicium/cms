package hu.indicium.cms.auth;

import hu.indicium.cms.auth.request.RefreshTokenRequest;
import hu.indicium.cms.auth.request.TokenRequest;
import hu.indicium.cms.auth.response.TokenResponse;

public interface AuthService {
    TokenResponse loginUser(TokenRequest tokenRequest);
    TokenResponse refreshUser(RefreshTokenRequest refreshTokenRequest);

}
