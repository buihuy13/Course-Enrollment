package CNTTK18.JobBE.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import CNTTK18.JobBE.Models.SinhVien;

public interface SinhVienRepo extends JpaRepository<SinhVien, String> {
    SinhVien findByEmail(String email);
}
