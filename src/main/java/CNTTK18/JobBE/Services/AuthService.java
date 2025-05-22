package CNTTK18.JobBE.Services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import CNTTK18.JobBE.DTO.Auth.LoginDTO;
import CNTTK18.JobBE.Models.TokenResponse;
import CNTTK18.JobBE.Models.Users;
import CNTTK18.JobBE.Repositories.UsersRepo;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AuthService {
    private final UsersRepo repo;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UsersRepo repo, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.repo = repo;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public TokenResponse login(LoginDTO model) {
        @SuppressWarnings("unused")
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(model.getUsername(), model.getPassword()));
        return new TokenResponse(jwtService.generateToken(model.getUsername()), jwtService.generateRefreshToken(model.getUsername()));
    }

    public boolean logout(String email) {
        Users user = repo.findByEmail(email);
        if (user == null) {
            throw new EntityNotFoundException("User not found with email " + email);
        }
        return true;
    }

    public Users getUserByEmail(String email)
    {
        return repo.findByEmail(email);
    }
}
