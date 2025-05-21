package CNTTK18.JobBE.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import CNTTK18.JobBE.Models.PhieuDangKy;
import CNTTK18.JobBE.Models.SinhVien;

@Repository
public interface PhieuDangKyRepo extends JpaRepository<PhieuDangKy, String> {
    @Query("SELECT pdk.maPDK FROM PhieuDangKy pdk WHERE pdk.maPDK LIKE 'PDK%' ORDER BY pdk.maPDK DESC")
    List<String> findAllMaPDK();

    Optional<List<PhieuDangKy>> findBySinhVien(SinhVien sinhVien);
}
