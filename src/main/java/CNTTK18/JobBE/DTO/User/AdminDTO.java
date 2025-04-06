package CNTTK18.JobBE.DTO.User;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminDTO {
    private String email;
    private String name;
    private Date dateOfBirth;
    private String sex;
    private String role;
}
