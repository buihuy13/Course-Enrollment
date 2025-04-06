package CNTTK18.JobBE.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import CNTTK18.JobBE.DTO.MonHoc.CreateSubjectDTO;
import CNTTK18.JobBE.Exception.DuplicateEntityException;
import CNTTK18.JobBE.Models.DieuKienTienQuyet;
import CNTTK18.JobBE.Models.Khoa;
import CNTTK18.JobBE.Models.MonHoc;
import CNTTK18.JobBE.Repositories.DieuKienTienQuyetRepo;
import CNTTK18.JobBE.Repositories.KhoaRepo;
import CNTTK18.JobBE.Repositories.MonHocRepo;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SubjectService {
    private final MonHocRepo subjectRepo;
    private final KhoaRepo khoaRepo;
    private final DieuKienTienQuyetRepo dieuKienTienQuyetRepo;

    public SubjectService(MonHocRepo subjectRepo, KhoaRepo khoaRepo, DieuKienTienQuyetRepo dieuKienTienQuyetRepo) {
        this.subjectRepo = subjectRepo;
        this.khoaRepo = khoaRepo;
        this.dieuKienTienQuyetRepo = dieuKienTienQuyetRepo;
    }

    public List<MonHoc> getAllSubjects() {
        return subjectRepo.findAll();
    }

    public MonHoc getSubjectById(String id) {
        MonHoc subject = subjectRepo.findMonHocByMaMH(id);
        if (subject == null) {
            throw new EntityNotFoundException("Subject not found with id: " + id);
        }
        return subject;
    }

    public void createSubject(CreateSubjectDTO newSubject) {
        MonHoc existingSubject = subjectRepo.findMonHocByMaMH(newSubject.getMaMH());
        if (existingSubject != null) {
            throw new DuplicateEntityException("Subject already exists with id: " + newSubject.getMaMH());
        } 
        
        MonHoc subject = new MonHoc();
        subject.setMaMH(newSubject.getMaMH());
        subject.setTenMH(newSubject.getTenMH());
        subject.setSoTinChi(newSubject.getSoTinChi());

        Khoa khoa = khoaRepo.findKhoaByMaKhoa(newSubject.getMaKhoa());
        if (khoa == null) {
            throw new EntityNotFoundException("Department not found with id: " + newSubject.getMaKhoa());
        }
        subject.setKhoa(khoa);
        if (newSubject.getMaMonTienQuyet() != null) {
            for (String maMonTienQuyet : newSubject.getMaMonTienQuyet()) {
                MonHoc monTienQuyet = subjectRepo.findMonHocByMaMH(maMonTienQuyet);
                if (monTienQuyet == null) {
                    throw new EntityNotFoundException("Prerequisite subject not found with id: " + maMonTienQuyet);
                }
                DieuKienTienQuyet dieuKien = new DieuKienTienQuyet();
                dieuKien.setMonHoc(subject);
                dieuKien.setMonHocTienQuyet(monTienQuyet);
            }
        }

    }

    public void deleteSubject(String id) {
        MonHoc subject = subjectRepo.findMonHocByMaMH(id);
        if (subject == null) {
            throw new EntityNotFoundException("Subject not found with id: " + id);
        }
        subjectRepo.delete(subject);
    }
}
