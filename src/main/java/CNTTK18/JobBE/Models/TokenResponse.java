package CNTTK18.JobBE.Models;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponse {

    @NotBlank
    public String accessToken;
    @NotBlank
    public String refreshToken;

    public TokenResponse(String accessToken, String refreshToken)
    {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
