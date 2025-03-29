package CNTTK18.JobBE.Models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MonDaDat extends MonHoc {

    @NotNull
    private String namHoc;

    @NotNull
    private int hocKy;

    @OneToMany(mappedBy = "monDaDat", cascade = CascadeType.ALL)
    private List<SinhVienMonDaDat> sinhVienMonDaDatList;

    public List<SinhVienMonDaDat> getSinhVienMonDaDatList() {
        if (sinhVienMonDaDatList == null) {
            sinhVienMonDaDatList = new java.util.ArrayList<>();
        }
        return sinhVienMonDaDatList;
    }
}
