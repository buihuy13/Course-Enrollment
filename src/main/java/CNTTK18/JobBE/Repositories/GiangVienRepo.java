package CNTTK18.JobBE.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CNTTK18.JobBE.Models.GiangVien;

@Repository
public interface GiangVienRepo extends JpaRepository<GiangVien, String> {
    GiangVien findByEmail(String email);

    GiangVien findGiangVienById(String id);

    GiangVien findGiangVienByMsgv(String msgv);
}
