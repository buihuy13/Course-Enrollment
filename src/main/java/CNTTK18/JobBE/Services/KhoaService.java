package CNTTK18.JobBE.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import CNTTK18.JobBE.DTO.Khoa.KhoaDTO;
import CNTTK18.JobBE.Models.Khoa;
import CNTTK18.JobBE.Repositories.KhoaRepo;

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
        if (a != null) {
            return new KhoaDTO(a.getMaKhoa(), a.getTenKhoa());
        } else {
            return null;
        }
    }

    public Khoa createKhoa(Khoa khoa) {
        return repo.save(khoa);
    }

    public void updateKhoaByMaKhoa(Khoa khoa, String makhoa) {
        khoa.setMaKhoa(makhoa);
        repo.save(khoa);
    }

    public void deletedKhoa(String makhoa) {
        repo.delete(repo.findByMaKhoa(makhoa));
    }
}