package CNTTK18.JobBE.Models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Users {
    @Id
    @Size(max = 30)
    private String id;

    @NotNull
    @Size(max = 255)
    private String password;

    @NotNull
    @Email
    @Size(max = 50)
    private String email;

    @NotNull
    @Size(max = 100)
    private String hoten;

    @NotNull
    private Date ngaysinh;

    @NotNull
    @Size(max = 20)
    private String gioitinh;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles role;
}
