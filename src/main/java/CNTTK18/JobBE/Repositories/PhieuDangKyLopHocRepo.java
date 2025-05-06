package CNTTK18.JobBE.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CNTTK18.JobBE.Models.PhieuDangKyLopHoc;

@Repository
public interface PhieuDangKyLopHocRepo extends JpaRepository<PhieuDangKyLopHoc, Integer> {
    Optional<PhieuDangKyLopHoc> findByPhieuDangKyMaPDKAndLopHocMaLH(String maPDK, String maLH);
}
