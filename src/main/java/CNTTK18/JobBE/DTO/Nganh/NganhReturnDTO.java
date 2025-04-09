package CNTTK18.JobBE.DTO.Nganh;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NganhReturnDTO {
    @NotBlank
    @Size(max = 30)
    private String maNganh;
    @NotBlank
    @Size(max = 30)
    private String tenNganh;

    @NotBlank
    @Size(max = 30)
    public String maKhoa;
}
