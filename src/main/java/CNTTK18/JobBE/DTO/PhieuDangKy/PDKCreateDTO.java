package CNTTK18.JobBE.DTO.PhieuDangKy;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PDKCreateDTO {
    @NotNull
    @Min(1)
    @Max(3)
    private int hocKi;
    @NotBlank
    private String namHoc;
    @NotNull
    private int soTinChi;
    @NotBlank
    private String maSV;
}
