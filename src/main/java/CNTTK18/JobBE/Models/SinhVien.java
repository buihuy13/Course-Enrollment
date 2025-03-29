package CNTTK18.JobBE.Models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class SinhVien extends Users {

    @NotNull
    @Size(max = 100)
    private String mssv;

    @ManyToOne
    @JoinColumn(name = "maCNganh")
    private ChuyenNganh chuyenNganh;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "maPDK")
    private PhieuDangKy phieuDangKy;

    @OneToMany(mappedBy = "sinhVien", cascade = CascadeType.ALL)
    private List<SinhVienMonDaDat> sinhVienMonDaDatList;

    public List<SinhVienMonDaDat> getSinhVienMonDaDatList() {
        if (sinhVienMonDaDatList == null) {
            sinhVienMonDaDatList = new ArrayList<>();
        }
        return sinhVienMonDaDatList;
    }
}
