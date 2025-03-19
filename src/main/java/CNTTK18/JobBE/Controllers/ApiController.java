package CNTTK18.JobBE.Controllers;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import CNTTK18.JobBE.Services.ApiService;
import io.jsonwebtoken.ExpiredJwtException;

@RestController("/api")
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService)
    {
        this.apiService = apiService;
    }

    @PostMapping("/accesstoken")
    public ResponseEntity<String> RefreshAccessToken(@RequestHeader("X-Refresh-Token") String refToken) 
    {
        try {
            String accessToken = apiService.refreshAccessToken(refToken);
            return ResponseEntity.ok(accessToken);
        }
        catch (ExpiredJwtException e)
        {
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).body("Expired token");
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Invalid");
        }
    }
}
