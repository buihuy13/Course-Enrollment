package CNTTK18.JobBE.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

public class MonHoc {
    @Id
    @Size(max = 10)
    private String maMH;

    @Column(nullable = false, unique = true)
    @NotNull
    @Size(max = 100)
    private String tenMH;

    @Column(nullable = false)
    @NotNull
    private int soTinChi;

    @Column(nullable = false)
    @NotNull
    private int soLuongSinhVienToiDa;
}
