package CNTTK18.JobBE.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import CNTTK18.JobBE.Models.GiangVien;

public interface GiangVienRepo extends JpaRepository<GiangVien, String> {
    GiangVien findByEmail(String email);

    GiangVien findGiangVienById(String id);
}
