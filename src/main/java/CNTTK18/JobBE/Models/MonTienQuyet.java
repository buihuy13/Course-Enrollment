package CNTTK18.JobBE.Models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MonTienQuyet extends MonHoc {

    @OneToMany(mappedBy = "monTienQuyet", cascade = CascadeType.ALL)
    private List<DieuKienTienQuyet> dieuKienTienQuyet;

    public List<DieuKienTienQuyet> getDieuKienTienQuyet() {
        if (dieuKienTienQuyet == null) {
            dieuKienTienQuyet = new java.util.ArrayList<>();
        }
        return dieuKienTienQuyet;
    }
}
