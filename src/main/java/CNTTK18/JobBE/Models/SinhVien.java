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

public class SinhVien extends Users {

    @Column(nullable = false, unique = true)
    @NotNull
    @Size(max = 100)
    private String mssv;

    @Column(nullable = false)
    @NotNull
    @Size(max = 100)
    private String hoten;

    @Column(nullable = false)
    @NotNull
    private Date ngaysinh;

    private String chuyennganh;

    @Column(nullable = false)
    @NotNull
    private String gioitinh;
}
