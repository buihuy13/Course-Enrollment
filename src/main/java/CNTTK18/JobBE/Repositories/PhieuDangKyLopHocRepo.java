package CNTTK18.JobBE.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import CNTTK18.JobBE.Models.LopHoc;
import CNTTK18.JobBE.Models.PhieuDangKy;
import CNTTK18.JobBE.Models.PhieuDangKyLopHoc;

@Repository
public interface PhieuDangKyLopHocRepo extends JpaRepository<PhieuDangKyLopHoc, Integer> {

    @Query("SELECT p.id FROM PhieuDangKyLopHoc p WHERE p.phieuDangKy.maPDK = :mapdk AND p.lopHoc.maLH = :malh")
    Integer getID(@Param("mapdk") String mapdk, @Param("malh") String malh);

}
