package CNTTK18.JobBE.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import CNTTK18.JobBE.DTO.MonHoc.CreateSubjectDTO;
import CNTTK18.JobBE.Exception.DuplicateEntityException;
import CNTTK18.JobBE.Models.DieuKienTienQuyet;
import CNTTK18.JobBE.Models.Khoa;
import CNTTK18.JobBE.Models.MonHoc;
import CNTTK18.JobBE.Models.MonTienQuyet;
import CNTTK18.JobBE.Repositories.DieuKienTienQuyetRepo;
import CNTTK18.JobBE.Repositories.KhoaRepo;
import CNTTK18.JobBE.Repositories.MonHocRepo;
import CNTTK18.JobBE.Repositories.MonTienQuyetRepo;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SubjectService {
    private final MonHocRepo subjectRepo;
    private final KhoaRepo khoaRepo;
    private final DieuKienTienQuyetRepo dieuKienTienQuyetRepo;
    private final MonTienQuyetRepo monTienQuyetRepo;

    public SubjectService(MonHocRepo subjectRepo, KhoaRepo khoaRepo, DieuKienTienQuyetRepo dieuKienTienQuyetRepo, MonTienQuyetRepo monTienQuyetRepo) {
        this.monTienQuyetRepo = monTienQuyetRepo;
        this.subjectRepo = subjectRepo;
        this.khoaRepo = khoaRepo;
        this.dieuKienTienQuyetRepo = dieuKienTienQuyetRepo;
    }

    public List<CreateSubjectDTO> getAllSubjects() {
        List<MonHoc> subjects = subjectRepo.findAll();

        if (subjects.isEmpty()) {
            throw new EntityNotFoundException("No subjects found");
        }

        return subjects.stream().map(subject -> {
            CreateSubjectDTO dto = new CreateSubjectDTO();
            dto.setMaMH(subject.getMaMH());
            dto.setTenMH(subject.getTenMH());
            dto.setSoTinChi(subject.getSoTinChi());
            dto.setMaKhoa(subject.getKhoa().getMaKhoa());
            List<String> maMonTQ = dieuKienTienQuyetRepo.findTenMonTienQuyetByMaMH(subject.getMaMH());
            dto.setMaMonTienQuyet(maMonTQ);
            return dto;
        }).toList();
    }

    public CreateSubjectDTO getSubjectById(String id) {
        MonHoc subject = subjectRepo.findMonHocByMaMH(id);
        if (subject == null) {
            throw new EntityNotFoundException("Subject not found with id: " + id);
        }
        List<String> maMonTQ = dieuKienTienQuyetRepo.findTenMonTienQuyetByMaMH(subject.getMaMH());
        return new CreateSubjectDTO(subject.getMaMH(), subject.getTenMH(), subject.getSoTinChi(), subject.getKhoa().getMaKhoa(), maMonTQ);
    }

    @Transactional
    public void createSubject(CreateSubjectDTO newSubject) {
        MonHoc existingSubject = subjectRepo.findMonHocByMaMH(newSubject.getMaMH());
        if (existingSubject != null) {
            throw new DuplicateEntityException("Subject already exists with id: " + newSubject.getMaMH());
        } 
        
        MonHoc subject = new MonHoc();
        subject.setMaMH(newSubject.getMaMH());
        subject.setTenMH(newSubject.getTenMH());
        subject.setSoTinChi(newSubject.getSoTinChi());

        Khoa khoa = khoaRepo.findByMaKhoa(newSubject.getMaKhoa());
        if (khoa == null) {
            throw new EntityNotFoundException("Khoa not found with id: " + newSubject.getMaKhoa());
        }
        subject.setKhoa(khoa);
        subjectRepo.save(subject);

        if (newSubject.getMaMonTienQuyet() != null) {
            for (String maMonTienQuyet : newSubject.getMaMonTienQuyet()) {
                MonTienQuyet monTienQuyet = monTienQuyetRepo.findMonTienQuyetByMaMH(maMonTienQuyet);
                if (monTienQuyet == null) {
                    throw new EntityNotFoundException("Prerequisite subject not found with id: " + maMonTienQuyet);
                }
                DieuKienTienQuyet dieuKien = new DieuKienTienQuyet();
                dieuKien.setMonHoc(subject);
                dieuKien.setMonTienQuyet(monTienQuyet);
                dieuKienTienQuyetRepo.save(dieuKien);
            }
        }
    }

    @Transactional
    public void updateSubject(String id, CreateSubjectDTO dto)
    {
        if (!id.equals(dto.getMaMH())) {
            MonHoc existingSubject = subjectRepo.findMonHocByMaMH(dto.getMaMH());
            if (existingSubject != null) {
                throw new DuplicateEntityException("Subject already exists with id: " + dto.getMaMH());
            } 
        }
        MonHoc subject = subjectRepo.findMonHocByMaMH(id);
        if (subject == null) {
            throw new EntityNotFoundException("Subject not found with id: " + id);
        }

        Khoa khoa = khoaRepo.findByMaKhoa(dto.getMaKhoa());
        if (khoa == null) {
            throw new EntityNotFoundException("Khoa not found with id: " + dto.getMaKhoa());
        }

        subject.setMaMH(dto.getMaMH());
        subject.setTenMH(dto.getTenMH());
        subject.setSoTinChi(dto.getSoTinChi());
        subject.setKhoa(khoa);

        if (!subject.getDieuKienTienQuyet().isEmpty())
        {
            // Tạo bản sao để tránh ConcurrentModificationException
            List<DieuKienTienQuyet> dkToDelete = new ArrayList<>(subject.getDieuKienTienQuyet());
            for (DieuKienTienQuyet dk : dkToDelete) {
                dieuKienTienQuyetRepo.delete(dk);
            }
            subject.getDieuKienTienQuyet().clear();
            dieuKienTienQuyetRepo.flush(); // đảm bảo nó được xóa luôn để tránh vi phạm ràng buộc unique
        }

        subjectRepo.save(subject);

        if (dto.getMaMonTienQuyet() != null) {
            for (String maMonTienQuyet : dto.getMaMonTienQuyet()) {
                MonTienQuyet monTienQuyet = monTienQuyetRepo.findMonTienQuyetByMaMH(maMonTienQuyet);
                if (monTienQuyet == null) {
                    throw new EntityNotFoundException("Prerequisite subject not found with id: " + maMonTienQuyet);
                }
                DieuKienTienQuyet dieuKien = new DieuKienTienQuyet();
                dieuKien.setMonHoc(subject);
                dieuKien.setMonTienQuyet(monTienQuyet);
                dieuKienTienQuyetRepo.save(dieuKien);
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
