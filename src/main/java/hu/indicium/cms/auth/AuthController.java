package hu.indicium.cms.auth;

import hu.indicium.cms.auth.request.TokenRequest;
import hu.indicium.cms.auth.response.TokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse getAuthToken(@RequestBody TokenRequest tokenRequest){
        return new TokenResponse();
    }
}
