package CNTTK18.JobBE.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import CNTTK18.JobBE.DTO.Auth.LoginDTO;
import CNTTK18.JobBE.Models.TokenResponse;
import CNTTK18.JobBE.Models.Users;
import CNTTK18.JobBE.Services.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    record LoginResponse(TokenResponse tokens, boolean rememberMe, String role, String id) {}
    record MessageResponse(String message) {}

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO model) {
        TokenResponse tokens = authService.login(model);
        Users user = authService.getUserByEmail(model.getUsername());
        return ResponseEntity.ok(new LoginResponse(tokens, model.isRememberme(),user.getRole().getRoleName(), user.getId()));
    }

    record UserRequest(String email) {}

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody UserRequest request) {
        String email = request.email();
        @SuppressWarnings("unused")
        boolean result = authService.logout(email);
        return ResponseEntity.ok(new MessageResponse("Logout successful"));
    }
}
