package CNTTK18.JobBE.Models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chuyennganh")
public class ChuyenNganh { 
    @Id
    @Size(max = 30)
    @Column(name = "manganh")
    private String maNganh;

    @NotNull
    @Size(max = 100)
    @Column(name = "tennganh")
    private String tenNganh;

    @OneToMany(mappedBy = "chuyenNganh", cascade = CascadeType.ALL)
    private List<SinhVien> sinhVienList;

    public List<SinhVien> getSinhVienList() {
        if (sinhVienList == null) {
            sinhVienList = new ArrayList<>();
        }
        return sinhVienList;
    }

    @ManyToOne
    @JoinColumn(name = "makhoa")
    private Khoa khoa;
}
