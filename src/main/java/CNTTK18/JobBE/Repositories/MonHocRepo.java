package CNTTK18.JobBE.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import CNTTK18.JobBE.Models.MonHoc;

public interface MonHocRepo extends JpaRepository<MonHoc, String> {

    MonHoc findMonHocById(String mamh);

}
