package CNTTK18.JobBE.Models;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @Column(nullable = false, unique = true)
    @NotNull
    @Size(max = 100)
    private String msgv;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "maKhoa")
    private Khoa khoa;
}
