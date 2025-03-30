package CNTTK18.JobBE.Services;

import org.springframework.stereotype.Service;

import CNTTK18.JobBE.Repositories.UsersRepo;
import io.jsonwebtoken.ExpiredJwtException;

@Service
public class ApiService {
    private final JwtService jwtService;
    private final UsersRepo repo;

    public ApiService(JwtService jwtService, UsersRepo repo)
    {
        this.jwtService = jwtService;
        this.repo = repo;
    }

    public String refreshAccessToken(String refToken) throws Exception
    {
        try {
            String accessToken = jwtService.refreshAccessToken(refToken);
            return accessToken;
        }
        catch (ExpiredJwtException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
