package CNTTK18.JobBE.Models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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

public class MonDaDat extends MonHoc {

    @NotNull
    @Size(max = 30)
    private String namHoc;

    @NotNull
    private int hocKi;

    @OneToMany(mappedBy = "monDaDat", cascade = CascadeType.ALL)
    private List<SinhVienMonDaDat> sinhVienMonDaDatList;

    public List<SinhVienMonDaDat> getSinhVienMonDaDatList() {
        if (sinhVienMonDaDatList == null) {
            sinhVienMonDaDatList = new java.util.ArrayList<>();
        }
        return sinhVienMonDaDatList;
    }
}
