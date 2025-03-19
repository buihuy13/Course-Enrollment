package CNTTK18.JobBE.Controllers;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import CNTTK18.JobBE.DTO.Auth.LoginDTO;
import CNTTK18.JobBE.Models.TokenResponse;
import CNTTK18.JobBE.Services.AuthService;
import jakarta.validation.Valid;

@RestController("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO model) {
        try {
            TokenResponse tokens = authService.login(model);
            return ResponseEntity.ok(tokens);
        }
        catch (BadCredentialsException e)
        {
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).body("Invalid username or password");
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Invalid");
        }
    }
}
