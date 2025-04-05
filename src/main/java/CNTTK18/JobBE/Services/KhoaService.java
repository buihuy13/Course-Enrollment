package CNTTK18.JobBE.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import CNTTK18.JobBE.DTO.Khoa.KhoaDTO;
import CNTTK18.JobBE.Exception.DuplicateEntityException;
import CNTTK18.JobBE.Models.Khoa;
import CNTTK18.JobBE.Repositories.KhoaRepo;
import jakarta.persistence.EntityNotFoundException;

@Service
public class KhoaService {

    private KhoaRepo repo;

    public KhoaService(KhoaRepo rp) {
        repo = rp;
    }

    public List<KhoaDTO> getAllKhoa() {
        return repo.findAllKhoa();
    }

    public KhoaDTO getKhoaByMaKhoa(String makhoa) {
        Khoa a = repo.findByMaKhoa(makhoa);
        if (a == null) {
            throw new EntityNotFoundException("Không tìm thấy khoa với mã khoa: " + makhoa);
        }
        return new KhoaDTO(a.getMaKhoa(), a.getTenKhoa());
    }

    public Khoa createKhoa(Khoa khoa) {
        if (repo.findByMaKhoa(khoa.getMaKhoa()) != null) {
            throw new DuplicateEntityException("Trùng khóa chính");
        }
        return repo.save(khoa);
    }

    public void updateKhoaByMaKhoa(Khoa khoa, String makhoa) {
        if (repo.findByMaKhoa(makhoa) == null) {
            throw new EntityNotFoundException("Không tìm thấy khoa với mã khoa: " + makhoa);
        }
        khoa.setMaKhoa(makhoa);
        repo.save(khoa);
    }

    public void deletedKhoa(String makhoa) {
        Khoa khoa = repo.findByMaKhoa(makhoa);
        if (khoa == null) {
            throw new EntityNotFoundException("Không tìm thấy khoa với mã khoa: " + makhoa);
        }
        repo.delete(khoa);
    }
}