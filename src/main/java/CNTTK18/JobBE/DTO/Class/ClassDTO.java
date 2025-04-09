package CNTTK18.JobBE.DTO.Class;

import java.sql.Date;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ClassDTO {
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
    private int soLuongSinhVien;
}
