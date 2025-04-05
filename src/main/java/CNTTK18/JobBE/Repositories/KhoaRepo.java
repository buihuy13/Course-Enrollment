package CNTTK18.JobBE.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CNTTK18.JobBE.Models.Khoa;

@Repository
public interface KhoaRepo extends JpaRepository<Khoa, String> {

}
