package CNTTK18.JobBE.Models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    private Date ngayBatDau;

    @NotNull
    private Date ngayKetThuc;

    @NotNull
    private int hocKi;

    @NotNull
    private String namHoc;

    @NotNull
    private int soLuongSinhVien;

    @ManyToOne
    @JoinColumn(name = "maMonHoc")
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
    @JoinColumn(name = "maGiangVien")
    private GiangVien giangVien;
}
