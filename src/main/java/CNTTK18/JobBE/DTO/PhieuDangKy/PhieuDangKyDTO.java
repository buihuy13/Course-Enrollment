package CNTTK18.JobBE.DTO.PhieuDangKy;

import java.util.List;

import CNTTK18.JobBE.DTO.User.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhieuDangKyDTO {
    private String maPDK;
    private int hocKi;
    private String namHoc;
    private int tongTinChi;
    private List<PhieuDangKyLopHocDTO> phieuDangKyLopHocList;
    private StudentDTO sinhVien;
}
