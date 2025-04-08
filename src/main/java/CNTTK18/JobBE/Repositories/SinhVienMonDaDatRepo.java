package CNTTK18.JobBE.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import CNTTK18.JobBE.Models.SinhVienMonDaDat;

public interface SinhVienMonDaDatRepo extends JpaRepository<SinhVienMonDaDat, Integer> {
    
    List<SinhVienMonDaDat> findBySinhVien_Mssv(String masv);
}
