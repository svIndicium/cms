package hu.indicium.cms.auth.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenRequest {
    private String Token;
    private String RefreshToken;
}
