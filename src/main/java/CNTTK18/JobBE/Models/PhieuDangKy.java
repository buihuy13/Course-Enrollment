package CNTTK18.JobBE.Models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PHIEUDANGKY")
public class PhieuDangKy {

    @Id
    @Size(max = 30)
    @Column(name = "mapdk")
    private String maPDK;

    @NotNull
    @Column(name = "hocki")
    private int hocKi;

    @NotNull
    @Size(max = 30)
    @Column(name = "namhoc")
    private String namHoc;

    @NotNull
    @Column(name = "tongtinchi")
    private int tongTinChi;

    @OneToMany(mappedBy = "phieuDangKy", cascade = CascadeType.ALL)
    private List<PhieuDangKyLopHoc> phieuDangKyLopHocList;

    public List<PhieuDangKyLopHoc> getPhieuDangKyLopHocList() {
        if (phieuDangKyLopHocList == null) {
            phieuDangKyLopHocList = new java.util.ArrayList<>();
        }
        return phieuDangKyLopHocList;
    }

    @OneToOne
    @JoinColumn(name = "mssv")
    private SinhVien sinhVien;
}
