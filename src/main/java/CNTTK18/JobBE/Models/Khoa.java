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
@AllArgsConstructor
@NoArgsConstructor

public class Khoa {

    @Id
    @Size(max = 10)
    private String maKhoa;

    @NotNull
    @Column(nullable = false, unique = true)
    @Size(max = 100)
    private String tenKhoa;
}
