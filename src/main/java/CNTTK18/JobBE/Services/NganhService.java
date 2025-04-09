package CNTTK18.JobBE.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import CNTTK18.JobBE.DTO.Nganh.NganhReturnDTO;
import CNTTK18.JobBE.Exception.DuplicateEntityException;
import CNTTK18.JobBE.Exception.ResourceNotFoundException;
import CNTTK18.JobBE.Models.ChuyenNganh;
import CNTTK18.JobBE.Models.Khoa;
import CNTTK18.JobBE.Repositories.ChuyenNganhRepo;
import CNTTK18.JobBE.Repositories.KhoaRepo;
import jakarta.persistence.EntityNotFoundException;

@Service
public class NganhService {
    private final ChuyenNganhRepo chuyenNganhRepo;
    private final KhoaRepo khoaRepo;

    public NganhService(ChuyenNganhRepo chuyenNganhRepo, KhoaRepo khoaRepo) {
        this.chuyenNganhRepo = chuyenNganhRepo;
        this.khoaRepo = khoaRepo;
    }

    public List<NganhReturnDTO> getAllMajors()
    {
        List<ChuyenNganh> majorList = chuyenNganhRepo.findAll();

        var result = majorList.stream().map(major -> {
            NganhReturnDTO nganhReturnDTO = new NganhReturnDTO();
            nganhReturnDTO.setMaNganh(major.getMaNganh());
            nganhReturnDTO.setTenNganh(major.getTenNganh());
            nganhReturnDTO.setMaKhoa(major.getKhoa().getMaKhoa());
            return nganhReturnDTO;
        }).toList();

        return result;
    }

    public NganhReturnDTO getMajorById(String id) {
        ChuyenNganh major = chuyenNganhRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find major with id: " + id));
        return new NganhReturnDTO(major.getMaNganh(), major.getTenNganh(), major.getKhoa().getMaKhoa());
    }

    public void createNganh(NganhReturnDTO nganhReturnDTO) {
        Khoa khoa = khoaRepo.findByMaKhoa(nganhReturnDTO.getMaKhoa());
        if (khoa == null)
        {
            throw new ResourceNotFoundException("Khoa not found with maKhoa: " + nganhReturnDTO.getMaKhoa());
        }
        if (chuyenNganhRepo.findChuyenNganhByMaNganh(nganhReturnDTO.getMaNganh()) != null) {
            throw new DuplicateEntityException("ChuyenNganh already exists with maNganh: " + nganhReturnDTO.getMaNganh());
        }
        ChuyenNganh chuyenNganh = new ChuyenNganh();
        chuyenNganh.setMaNganh(nganhReturnDTO.getMaNganh());
        chuyenNganh.setTenNganh(nganhReturnDTO.getTenNganh());
        chuyenNganh.setKhoa(khoa);
        chuyenNganhRepo.save(chuyenNganh);
    }

    public void updateNganh(String id, NganhReturnDTO nganhReturnDTO) {
        ChuyenNganh chuyenNganh = chuyenNganhRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find major with id: " + id));
        Khoa khoa = khoaRepo.findByMaKhoa(nganhReturnDTO.getMaKhoa());
        if (khoa == null)
        {
            throw new ResourceNotFoundException("Khoa not found with maKhoa: " + nganhReturnDTO.getMaKhoa());
        }
        chuyenNganh.setMaNganh(nganhReturnDTO.getMaNganh());
        chuyenNganh.setTenNganh(nganhReturnDTO.getTenNganh());
        chuyenNganh.setKhoa(khoa);
        chuyenNganhRepo.save(chuyenNganh);
    }

    public void deleteNganh(String id) {
        ChuyenNganh chuyenNganh = chuyenNganhRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find major with id: " + id));
        chuyenNganhRepo.delete(chuyenNganh);
    }
}

