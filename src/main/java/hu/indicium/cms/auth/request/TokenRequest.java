package hu.indicium.cms.auth.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRequest {
    public String username;
    public String password;
}
