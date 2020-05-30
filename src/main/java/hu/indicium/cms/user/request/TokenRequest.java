package hu.indicium.cms.user.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRequest {
    public String username;
    public String password;
}
