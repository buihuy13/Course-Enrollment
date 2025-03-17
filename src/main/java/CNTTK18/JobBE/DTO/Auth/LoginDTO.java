package CNTTK18.JobBE.DTO.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    //NotBlank đảm bảo username và password không được để trống, không null, không được chỉ có mỗi khoảng trắng
    @NotBlank
    @Email
    private String username;
    @NotBlank
    private String password;
    private boolean rememberMe;
}
