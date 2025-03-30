package CNTTK18.JobBE.Models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class GiangVien extends Users {

    @NotNull
    @Size(max = 30)
    private String msgv;

    @ManyToOne
    @JoinColumn(name = "MaKhoa")
    private Khoa khoa;

    @OneToMany(mappedBy = "giangVien", cascade = CascadeType.ALL)
    private List<LopHoc> lopHocList;

    public List<LopHoc> getLopHocList() {
        if (lopHocList == null) {
            lopHocList = new java.util.ArrayList<>();
        }
        return lopHocList;
    }
}
