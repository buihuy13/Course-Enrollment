package CNTTK18.JobBE.DTO.Class;

import java.sql.Date;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClassDetailsDTO {
    @NotNull
    private Date ngayBatDau;
    @NotNull
    private Date ngayKetThuc;
    @Size(max = 30)
    @NotBlank
    private String mamh;
    @Size(max = 30)
    @NotBlank
    private String magv;

    @NotBlank
    private String tenGV;

    @NotBlank
    private String tenMH;

    @NotBlank
    @Size(max = 30)
    private String malh;

    @NotNull
    private int tietBatDau;

    @NotNull
    private int tietKetThuc;

    @NotNull
    @Min(2)
    @Max(7)
    private int thu;

    @NotNull
    @Min(1)
    @Max(2)
    private int hocKi;

    @NotBlank
    private String namHoc;

    @NotNull
    private int soLuongSinhVien;

    @NotNull
    private int soLuongSinhVienToiDa;

    public ClassDetailsDTO(String malh, int hocKi, String namHoc, int tietBatDau, int tietKetThuc, int thu,
            String magv, String tenGV, String mamh, String tenMH, Date ngayBatDau, Date ngayKetThuc, int soLuongSinhVien, int soLuongSinhVienToiDa) {
        this.malh = malh;
        this.hocKi = hocKi;
        this.namHoc = namHoc;
        this.tietBatDau = tietBatDau;
        this.tietKetThuc = tietKetThuc;
        this.thu = thu;
        this.magv = magv;
        this.tenGV = tenGV;
        this.mamh = mamh;
        this.tenMH = tenMH;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.soLuongSinhVien = soLuongSinhVien;
        this.soLuongSinhVienToiDa = soLuongSinhVienToiDa;
    }
}
