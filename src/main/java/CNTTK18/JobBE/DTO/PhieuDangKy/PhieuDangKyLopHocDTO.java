package CNTTK18.JobBE.DTO.PhieuDangKy;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhieuDangKyLopHocDTO {
    private String maLopHoc;
    private String tenMonHoc;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private int hocKi;
    private String namHoc;
    private int soLuongSinhVien;
    private int soLuongSinhVienToiDa;
    private int tietBatDau;
    private int tietKetThuc; 
    private int thu;
    private int soTinChi;
}
