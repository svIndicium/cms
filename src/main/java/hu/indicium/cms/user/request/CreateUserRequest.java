package hu.indicium.cms.user.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateUserRequest {
    private String email;
    private int role;
    private String password;
}
