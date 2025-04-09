package CNTTK18.JobBE.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import CNTTK18.JobBE.Models.DieuKienTienQuyet;

@Repository
public interface DieuKienTienQuyetRepo extends JpaRepository<DieuKienTienQuyet, Integer> {

    @Query("SELECT mtq.tenMH FROM DieuKienTienQuyet dktq JOIN dktq.monTienQuyet mtq WHERE dktq.monHoc.maMH = :maMH")
    List<String> findTenMonTienQuyetByMaMH(String maMH);
}
