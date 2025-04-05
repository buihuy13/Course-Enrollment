package CNTTK18.JobBE.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import CNTTK18.JobBE.DTO.Class.ClassDTO;
import CNTTK18.JobBE.Models.GiangVien;
import CNTTK18.JobBE.Models.LopHoc;
import CNTTK18.JobBE.Models.MonHoc;
import CNTTK18.JobBE.Repositories.ClassRepo;
import CNTTK18.JobBE.Repositories.GiangVienRepo;
import CNTTK18.JobBE.Repositories.MonHocRepo;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ClassService {
    private final ClassRepo classRepo;
    private final GiangVienRepo giangVienRepo;
    private final MonHocRepo monHocRepo;

    public ClassService(ClassRepo classRepo, GiangVienRepo giangVienRepo, MonHocRepo monHocRepo) {
        this.classRepo = classRepo;
        this.giangVienRepo = giangVienRepo;
        this.monHocRepo = monHocRepo;
    }

    public List<LopHoc> getAllClasses() {
        return classRepo.findAll();        
    }

    public LopHoc getClassById(String id) {
        LopHoc lophoc = classRepo.findClassById(id);
        if (lophoc == null) {
            throw new EntityNotFoundException("Class not found with id: " + id);
        }

        return lophoc;
    }

    public void updateClassById(String id, ClassDTO updatedClass) {
        LopHoc existingClass = classRepo.findClassById(id);
        if (existingClass == null) {
            throw new EntityNotFoundException("Class not found with id: " + id);
        }
        GiangVien giangVien = giangVienRepo.findGiangVienById(updatedClass.getMagv());
        if (giangVien == null) {
            throw new EntityNotFoundException("GiangVien not found with id: " + updatedClass.getMagv());
        }
        MonHoc monHoc = monHocRepo.findMonHocById(updatedClass.getMamh());
        if (monHoc == null) {
            throw new EntityNotFoundException("MonHoc not found with id: " + updatedClass.getMamh());
        }

        existingClass.setMonHoc(monHoc);
        existingClass.setGiangVien(giangVien);
        existingClass.setNgayBatDau(updatedClass.getNgayBatDau());
        existingClass.setNgayKetThuc(updatedClass.getNgayKetThuc());

        classRepo.save(existingClass);
    }

    public void deleteClassById(String id) {
        LopHoc existingClass = classRepo.findClassById(id);
        if (existingClass == null) {
            throw new EntityNotFoundException("Class not found with id: " + id);
        }
        classRepo.delete(existingClass);
    }
}
