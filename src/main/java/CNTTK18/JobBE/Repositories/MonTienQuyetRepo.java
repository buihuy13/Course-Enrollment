package CNTTK18.JobBE.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CNTTK18.JobBE.Models.MonTienQuyet;

@Repository
public interface MonTienQuyetRepo extends JpaRepository<MonTienQuyet, String>{
    MonTienQuyet findMonTienQuyetByMaMH(String mamh);
}
