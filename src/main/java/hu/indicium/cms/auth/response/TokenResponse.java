package hu.indicium.cms.auth.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {
    private String token;
    private String refreshToken;

    public TokenResponse(String token) {
        this.token = token;
    }
}
