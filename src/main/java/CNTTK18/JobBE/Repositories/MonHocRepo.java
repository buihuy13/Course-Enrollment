package CNTTK18.JobBE.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CNTTK18.JobBE.Models.MonHoc;

@Repository
public interface MonHocRepo extends JpaRepository<MonHoc, String> {

    MonHoc findMonHocByMaMH(String mamh);

}
