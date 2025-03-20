package CNTTK18.JobBE.Models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LopHoc {

    @Id
    @Size(max = 10)
    private String maLH;

    @NotNull
    @Column(nullable = false)
    private Date ngayBatDau;

    @NotNull
    @Column(nullable = false)
    private Date ngayKetThuc;

    @NotNull
    @Column(nullable = false)
    private int hocKi;

    @NotNull
    @Column(nullable = false)
    private String namHoc;

    @NotNull
    @Column(nullable = false)
    private int soLuongSinhVien;
}
