package CNTTK18.JobBE.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CNTTK18.JobBE.Models.SinhVien;

@Repository
public interface SinhVienRepo extends JpaRepository<SinhVien, String> {
    SinhVien findByEmail(String email);

    SinhVien findSinhVienById(String id);
}
