package CNTTK18.JobBE.Models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class GiangVien extends Users {

    @Column(nullable = false, unique = true)
    @NotNull
    @Size(max = 100)
    private String msgv;

    @Column(nullable = false)
    @NotNull
    @Size(max = 100)
    private String hoten;

    @Column(nullable = false)
    @NotNull
    private Date ngaysinh;

    @Column(nullable = false)
    @NotNull
    private String gioitinh;
}
