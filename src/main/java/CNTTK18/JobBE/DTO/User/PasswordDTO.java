package CNTTK18.JobBE.DTO.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordDTO {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String oldpassword;
    @NotBlank
    private String newpassword;
}
