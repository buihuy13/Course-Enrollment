package CNTTK18.JobBE.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import CNTTK18.JobBE.Models.MonTienQuyet;

public interface MonTienQuyetRepo extends JpaRepository<MonTienQuyet, String>{
    MonTienQuyet findMonTienQuyetByMaMH(String mamh);
}
