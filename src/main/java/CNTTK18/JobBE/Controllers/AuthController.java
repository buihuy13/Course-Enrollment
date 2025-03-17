package CNTTK18.JobBE.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfigurationSource;
import CNTTK18.JobBE.DTO.Auth.LoginDTO;
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
        var userDetails = authService.login(model);

        if (userDetails == null) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
        return ResponseEntity.ok(userDetails);
    }
}
