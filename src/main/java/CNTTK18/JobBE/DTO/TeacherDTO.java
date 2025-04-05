package CNTTK18.JobBE.DTO;

import java.sql.Date;

import lombok.Data;

@Data
public class TeacherDTO {
    private String id;
    private String email;
    private String name;
    private Date dateOfBirth;
    private String sex;
    private String role;
    private String msgv;
    private String makhoa;
}
