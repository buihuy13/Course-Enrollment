package CNTTK18.JobBE.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import CNTTK18.JobBE.DTO.Khoa.KhoaDTO;
import CNTTK18.JobBE.Models.Khoa;

@Repository
public interface KhoaRepo extends JpaRepository<Khoa, String> {
    @Query("SELECT new CNTTK18.JobBE.DTO.Khoa.KhoaDTO(k.maKhoa, k.tenKhoa) FROM Khoa k")
    List<KhoaDTO> findAllKhoa();

    Khoa findByMaKhoa(String maKhoa);
}
