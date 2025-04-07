package CNTTK18.JobBE.DTO.User;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeacherDTO {
    private String id;
    private String email;
    private String name;
    private Date dateOfBirth;
    private String sex;
    private int role;
    private String msgv;
    private String makhoa;
}
