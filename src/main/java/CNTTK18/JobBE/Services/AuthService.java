package CNTTK18.JobBE.Services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import CNTTK18.JobBE.DTO.Auth.LoginDTO;
import CNTTK18.JobBE.Models.Users;
import CNTTK18.JobBE.Repositories.UsersRepo;

public class AuthService {
    private final UsersRepo repo;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UsersRepo repo, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.repo = repo;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public Object login(LoginDTO model) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(model.getUsername(), model.getPassword()));
        if (!authentication.isAuthenticated()) {
            return null;
        }
        Users user = repo.findByEmail(model.getUsername());
        return new {
            token = jwtService.generateToken(user.getUsername(), user.getId());
        }
    }

}
