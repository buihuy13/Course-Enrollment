package CNTTK18.JobBE.Controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CNTTK18.JobBE.DTO.Api.UserDetailsDTO;
import CNTTK18.JobBE.Exception.InvalidTokenException;
import CNTTK18.JobBE.Services.ApiService;
import io.jsonwebtoken.ExpiredJwtException;


@RestController
@RequestMapping("/api")
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService)
    {
        this.apiService = apiService;
    }
    record MessageResponse(String message) {}

    @PostMapping("/accesstoken")
    public ResponseEntity<?> RefreshAccessToken(@RequestHeader("Refresh-Token") String refToken) 
    {
        try {
            if (refToken.startsWith("Bearer"))
            {
                refToken = refToken.substring(7);
            }
            String accessToken = apiService.refreshAccessToken(refToken);
            Map<String,String> response = new HashMap<>();

            response.put("accessToken", accessToken);
            return ResponseEntity.ok(response);
        }
        catch (InvalidTokenException e)
        {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(new MessageResponse("Invalid refresh token"));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserByAccessToken(@RequestHeader("Authorization") String accessToken) {
        try {
            if (accessToken.startsWith("Bearer"))
            {
                accessToken = accessToken.substring(7);
            }
            UserDetailsDTO user = apiService.getUserByAccessToken(accessToken);
            if (user == null) {
                return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(new MessageResponse("User not found"));
            }
            return ResponseEntity.ok(user);
        } 
        catch (ExpiredJwtException e)
        {
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(new MessageResponse("Token hết hạn"));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }
}
