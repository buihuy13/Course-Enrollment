package CNTTK18.JobBE.Models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
@Table(name = "monhoc")
@Inheritance(strategy = InheritanceType.JOINED)
public class MonHoc {
    @Id
    @Size(max = 30)
    @Column(name = "mamh")
    private String maMH;

    @NotNull
    @Size(max = 100)
    @Column(name = "tenmh")
    private String tenMH;

    @NotNull
    @Column(name = "sotinchi")
    private int soTinChi;

    @OneToMany(mappedBy = "monHoc", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DieuKienTienQuyet> dieuKienTienQuyet;

    public List<DieuKienTienQuyet> getDieuKienTienQuyet() {
        if (dieuKienTienQuyet == null) {
            dieuKienTienQuyet = new ArrayList<>();
        }
        return dieuKienTienQuyet;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "makhoa")
    private Khoa khoa;
}
