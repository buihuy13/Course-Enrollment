package CNTTK18.JobBE.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
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
    @Column(nullable = false)
    private String namHoc;

    @NotNull
    @Column(nullable = false)
    private int hocKy;
}
