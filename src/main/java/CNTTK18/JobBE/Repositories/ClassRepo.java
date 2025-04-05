package CNTTK18.JobBE.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CNTTK18.JobBE.Models.LopHoc;  

@Repository
public interface ClassRepo extends JpaRepository<LopHoc, String> {
    LopHoc findLopHocById(String id);
}
