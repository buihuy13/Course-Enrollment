package CNTTK18.JobBE.DTO.Class;

import java.sql.Date;

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
    public Date ngayBatDau;
    public Date ngayKetThuc;
    @Size(max = 30)
    public String mamh;
    @Size(max = 30)
    public String magv;
}
