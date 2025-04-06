package CNTTK18.JobBE.DTO;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {
    private String id;
    private String email;
    private String name;
    private Date dateOfBirth;
    private String sex;
    private int role;
    private String mssv;
    private String manganh;
    private String mapdk;
}
