package CNTTK18.JobBE.Models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

@Inheritance(strategy = InheritanceType.JOINED)
public class MonHoc {
    @Id
    @Size(max = 30)
    private String maMH;

    @NotNull
    @Size(max = 100)
    private String tenMH;

    @NotNull
    private int soTinChi;

    @NotNull
    private int soLuongSinhVienToiDa;

    @OneToMany(mappedBy = "monHoc", cascade = CascadeType.ALL)
    private List<DieuKienTienQuyet> dieuKienTienQuyet;

    public List<DieuKienTienQuyet> getDieuKienTienQuyet() {
        if (dieuKienTienQuyet == null) {
            dieuKienTienQuyet = new ArrayList<>();
        }
        return dieuKienTienQuyet;
    }

    @ManyToOne
    @JoinColumn(name = "maKhoa")
    private Khoa khoa;
}
