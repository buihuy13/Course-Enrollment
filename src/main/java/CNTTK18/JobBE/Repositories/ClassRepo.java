package CNTTK18.JobBE.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import CNTTK18.JobBE.DTO.Class.ClassDetailsDTO;
import CNTTK18.JobBE.Models.LopHoc;  

@Repository
public interface ClassRepo extends JpaRepository<LopHoc, String> {
    LopHoc findLopHocByMaLH(String malh);

    @Query("SELECT new CNTTK18.JobBE.DTO.Class.ClassDetailsDTO(l.maLH, l.hocKi, l.namHoc, " +
           "l.tietBatDau, l.tietKetThuc, l.thu, " +
           "gv.msgv, gv.hoten, mh.maMH, mh.tenMH, " +
           "l.ngayBatDau, l.ngayKetThuc, l.soLuongSinhVien) " +
           "FROM LopHoc l JOIN l.giangVien gv JOIN l.monHoc mh")
    List<ClassDetailsDTO> findAllLopHoc();

    @Query("SELECT new CNTTK18.JobBE.DTO.Class.ClassDetailsDTO(l.maLH, l.hocKi, l.namHoc, " +
    "l.tietBatDau, l.tietKetThuc, l.thu, " +
    "gv.msgv, gv.hoten, mh.maMH, mh.tenMH, " +
    "l.ngayBatDau, l.ngayKetThuc, l.soLuongSinhVien) " +
    "FROM LopHoc l JOIN l.giangVien gv JOIN l.monHoc mh " +
    "WHERE l.maLH = :id")
    ClassDetailsDTO findLopHoc(String id);
}
