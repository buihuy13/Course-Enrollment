package CNTTK18.JobBE.Models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
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
@Table(name = "khoa")
public class Khoa {

    @Id
    @Size(max = 30)
    @Column(name = "makhoa")
    private String maKhoa;

    @NotNull
    @Size(max = 100)
    @Column(name = "tenkhoa")
    private String tenKhoa;

    @OneToMany(mappedBy = "khoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ChuyenNganh> chuyenNganhList;

    public List<ChuyenNganh> getChuyenNganhList() {
        if (chuyenNganhList == null) {
            chuyenNganhList = new ArrayList<>();
        }
        return chuyenNganhList;
    }

    @OneToMany(mappedBy = "khoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GiangVien> giangVienList;

    public List<GiangVien> getGiangVienList() {
        if (giangVienList == null) {
            giangVienList = new ArrayList<>();
        }
        return giangVienList;
    }

    @OneToMany(mappedBy = "khoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MonHoc> monHocList;

    public List<MonHoc> getMonHocList() {
        if (monHocList == null) {
            monHocList = new ArrayList<>();
        }
        return monHocList;
    }
}
