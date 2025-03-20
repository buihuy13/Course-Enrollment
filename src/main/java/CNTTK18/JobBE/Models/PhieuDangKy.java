package CNTTK18.JobBE.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhieuDangKy {

    @Id
    private String maPDK;

    @NotNull
    @Column(nullable = false)
    private int hocKi;

    @NotNull
    @Column(nullable = false)
    private String namHoc;

    @NotNull
    @Column(nullable = false)
    private int tongTinChi;
}
