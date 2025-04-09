package CNTTK18.JobBE.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CNTTK18.JobBE.Models.SinhVienMonDaDat;

@Repository
public interface SinhVienMonDaDatRepo extends JpaRepository<SinhVienMonDaDat, Integer> {
    
    List<SinhVienMonDaDat> findBySinhVien_Mssv(String masv);
}
