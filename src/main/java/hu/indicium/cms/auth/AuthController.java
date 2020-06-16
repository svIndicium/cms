package hu.indicium.cms.auth;

import hu.indicium.cms.auth.request.RefreshTokenRequest;
import hu.indicium.cms.auth.request.TokenRequest;
import hu.indicium.cms.auth.response.TokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TokenResponse> getAuthToken(@RequestBody TokenRequest tokenRequest){
        if(Objects.equals(tokenRequest.getUsername(), "") || Objects.equals(tokenRequest.getPassword(), "")){
            return ResponseEntity.notFound().build();
        }
        TokenResponse tokenResponse = authService.loginUser(tokenRequest);
        if(tokenResponse == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TokenResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        TokenResponse tokenResponse = authService.refreshUser(refreshTokenRequest);
        if(tokenResponse == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(tokenResponse);
    }
}
