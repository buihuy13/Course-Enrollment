package CNTTK18.JobBE.Models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@AllArgsConstructor
@NoArgsConstructor

public class Khoa {

    @Id
    @Size(max = 10)
    private String maKhoa;

    @NotNull
    @Size(max = 100)
    private String tenKhoa;

    @OneToMany(mappedBy = "khoa", cascade = CascadeType.ALL)
    private List<ChuyenNganh> chuyenNganhList;

    public List<ChuyenNganh> getChuyenNganhList() {
        if (chuyenNganhList == null) {
            chuyenNganhList = new ArrayList<>();
        }
        return chuyenNganhList;
    }

    @OneToMany(mappedBy = "khoa", cascade = CascadeType.ALL)
    private List<GiangVien> giangVienList = new ArrayList<>();

    public List<GiangVien> getGiangVienList() {
        if (giangVienList == null) {
            giangVienList = new ArrayList<>();
        }
        return giangVienList;
    }
}
