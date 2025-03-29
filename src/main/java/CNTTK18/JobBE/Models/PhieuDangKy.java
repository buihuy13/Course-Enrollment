package CNTTK18.JobBE.Models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhieuDangKy {

    @Id
    private String maPDK;

    @NotNull
    private int hocKi;

    @NotNull
    private String namHoc;

    @NotNull
    private int tongTinChi;

    @OneToMany(mappedBy = "phieuDangKy", cascade = CascadeType.ALL)
    private List<PhieuDangKyLopHoc> phieuDangKyLopHocList;

    public List<PhieuDangKyLopHoc> getPhieuDangKyLopHocList() {
        if (phieuDangKyLopHocList == null) {
            phieuDangKyLopHocList = new java.util.ArrayList<>();
        }
        return phieuDangKyLopHocList;
    }
}
