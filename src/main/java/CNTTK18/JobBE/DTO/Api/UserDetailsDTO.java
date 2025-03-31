package CNTTK18.JobBE.DTO.Api;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDTO {
    private String email;
    private String name;
    private Date dateOfBirth;
    private String sex;
    private String role;
    private String ms;
}
