package CNTTK18.JobBE.Models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "LOPHOC")
public class LopHoc {

    @Id
    @Size(max = 30)
    @Column(name = "malh")
    private String maLH;

    @NotNull
    @Column(name = "ngaybatdau")
    private Date ngayBatDau;

    @NotNull
    @Column(name = "ngayketthuc")
    private Date ngayKetThuc;

    @NotNull
    @Column(name = "hocki")
    private int hocKi;

    @NotNull
    @Size(max = 30)
    @Column(name = "namhoc")
    private String namHoc;

    @NotNull
    @Column(name = "soluongsinhvien")
    private int soLuongSinhVien;

    @ManyToOne
    @JoinColumn(name = "mamh")
    private MonHoc monHoc;

    @OneToMany(mappedBy = "lopHoc", cascade = CascadeType.ALL)
    private List<PhieuDangKyLopHoc> phieuDangKyLopHocList;

    public List<PhieuDangKyLopHoc> getPhieuDangKyLopHocList() {
        if (phieuDangKyLopHocList == null) {
            phieuDangKyLopHocList = new java.util.ArrayList<>();
        }
        return phieuDangKyLopHocList;
    }

    @ManyToOne
    @JoinColumn(name = "magv")
    private GiangVien giangVien;
}
