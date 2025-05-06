package CNTTK18.JobBE.Models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sinhvien")
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "id"
)
public class SinhVien extends Users {

    @NotNull
    @Size(max = 30)
    private String mssv;

    @ManyToOne
    @JoinColumn(name = "manganh")
    private ChuyenNganh chuyenNganh;

    @OneToMany(mappedBy = "sinhVien", cascade = CascadeType.ALL)
    private List<SinhVienMonDaDat> sinhVienMonDaDatList;

    public List<SinhVienMonDaDat> getSinhVienMonDaDatList() {
        if (sinhVienMonDaDatList == null) {
            sinhVienMonDaDatList = new ArrayList<>();
        }
        return sinhVienMonDaDatList;
    }
}
