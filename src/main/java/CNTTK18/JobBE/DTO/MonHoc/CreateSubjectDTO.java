package CNTTK18.JobBE.DTO.MonHoc;

import java.util.List;

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
public class CreateSubjectDTO {
    @NotNull
    @Size(max = 30)
    private String maMH;
    @NotNull
    @Size(max = 100)
    private String tenMH;
    @NotNull
    private int soTinChi;
    @NotNull
    @Size(max = 30)
    private String maKhoa;
    private List<String> maMonTienQuyet; 
}
