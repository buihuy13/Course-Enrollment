package CNTTK18.JobBE.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sinhvienmondadat")
public class SinhVienMonDaDat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @NotNull

    @JoinColumn(name = "mssv")
    private SinhVien sinhVien;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "MaMH")
    private MonHoc monDaDat;

    @NotNull
    @Size(max = 30)
    @Column(name = "namhoc")
    private String namHoc;

    @NotNull
    @Column(name = "hocki")
    private int hocKi;
}
