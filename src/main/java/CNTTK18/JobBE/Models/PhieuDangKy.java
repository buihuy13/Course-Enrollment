package CNTTK18.JobBE.Models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@NoArgsConstructor
@AllArgsConstructor
public class PhieuDangKy {

    @Id
    @Size(max = 30)
    private String maPDK;

    @NotNull
    private int hocKi;

    @NotNull
    @Size(max = 30)
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
