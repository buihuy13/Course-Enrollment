package CNTTK18.JobBE.Services;

import java.util.List;
import org.springframework.stereotype.Service;

import CNTTK18.JobBE.DTO.PhieuDangKy.PDKCreateDTO;
import CNTTK18.JobBE.DTO.PhieuDangKy.PDKUpdateDTO;
import CNTTK18.JobBE.DTO.PhieuDangKy.PhieuDangKyDTO;
import CNTTK18.JobBE.Models.LopHoc;
import CNTTK18.JobBE.Models.PhieuDangKy;
import CNTTK18.JobBE.Models.PhieuDangKyLopHoc;
import CNTTK18.JobBE.Models.SinhVien;
import CNTTK18.JobBE.Repositories.LopHocRepo;
import CNTTK18.JobBE.Repositories.PhieuDangKyLopHocRepo;
import CNTTK18.JobBE.Repositories.PhieuDangKyRepo;
import CNTTK18.JobBE.Repositories.SinhVienRepo;
import CNTTK18.JobBE.Utils.Utils;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PhieuDangKyService {
    private final PhieuDangKyRepo phieuDangKyRepo;
    private final LopHocRepo lopHocRepo;
    private final PhieuDangKyLopHocRepo phieuDangKyLopHocRepo;
    private final SinhVienRepo sinhVienRepo;

    public PhieuDangKyService(PhieuDangKyRepo phieuDangKyRepo, LopHocRepo lopHocRepo, PhieuDangKyLopHocRepo phieuDangKyLopHocRepo, SinhVienRepo sinhVienRepo) {
        this.phieuDangKyRepo = phieuDangKyRepo;
        this.lopHocRepo = lopHocRepo;
        this.phieuDangKyLopHocRepo = phieuDangKyLopHocRepo;
        this.sinhVienRepo = sinhVienRepo;
    }

    public String generateMaPK() {
        List<String> listMa = phieuDangKyRepo.findAllMaPDK();
        int max = 0;

        if (!listMa.isEmpty()) {
            String lastMa = listMa.get(0); // mã lớn nhất
            String numberPart = lastMa.substring(3); // bỏ "PK"
            try {
                max = Integer.parseInt(numberPart);
            } catch (NumberFormatException ignored) {}
        }

        return String.format("PDK%03d", max + 1);
    }

    public List<PhieuDangKyDTO> getAllPDK() {
        List<PhieuDangKy> pdk = phieuDangKyRepo.findAll();
        if(pdk.isEmpty()) {
            throw new EntityNotFoundException("Not Found PDK");
        }
        List<PhieuDangKyDTO> pdkdtos = Utils.mapPDKListEntityToPDKListDTO(pdk);
        return pdkdtos;
    }

    public PhieuDangKyDTO getPDK(String maPDK) {
        PhieuDangKy pdk = phieuDangKyRepo.findById(maPDK).orElseThrow(() -> new EntityNotFoundException("PDK not found with maPDK " + maPDK));
        PhieuDangKyDTO pdkDTO = Utils.mapPhieuDangKyToPhieuDangKyDTO(pdk);
        return pdkDTO;
    }

    public PhieuDangKyDTO createPDK(PDKCreateDTO pdk) {
        PhieuDangKy pdkEntity = new PhieuDangKy();
        pdkEntity.setHocKi(pdk.getHocKi());
        pdkEntity.setNamHoc(pdk.getNamHoc());
        pdkEntity.setTongTinChi(pdk.getSoTinChi());
        pdkEntity.setMaPDK(generateMaPK());
        
        SinhVien existingSinhVien = sinhVienRepo.findById(pdk.getMaSV())
            .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy sinh viên với ID: " + pdk.getMaSV()));

        pdkEntity.setSinhVien(existingSinhVien);
        PhieuDangKy entity = phieuDangKyRepo.save(pdkEntity);
        PhieuDangKyDTO entityDTO = Utils.mapPhieuDangKyToPhieuDangKyDTO(entity);
        return entityDTO;
    }

    public PhieuDangKyDTO updatePDK(String maPDK, PDKUpdateDTO updated) {
        PhieuDangKy existing = phieuDangKyRepo.findById(maPDK)
                                .orElseThrow(() -> new EntityNotFoundException("PhieuDangKy not found with maPDK: " + maPDK));

        existing.setHocKi(updated.getHocKi());
        existing.setNamHoc(updated.getNamHoc());
        existing.setTongTinChi(updated.getSoTinChi());

        phieuDangKyRepo.save(existing);

        PhieuDangKyDTO pdkDTO = Utils.mapPhieuDangKyToPhieuDangKyDTO(existing);
        return pdkDTO;
    }

    public void deletePDK(String maPDK) {
        if (!phieuDangKyRepo.existsById(maPDK)) {
            throw new EntityNotFoundException("Không tìm thấy phiếu đăng ký để xóa: " + maPDK);
        }
        phieuDangKyRepo.deleteById(maPDK);
    }

    public void addClassToRegistration(String maPDK, String maLH) {
        PhieuDangKy phieuDangKy =  phieuDangKyRepo.findById(maPDK)
                                    .orElseThrow(() -> new EntityNotFoundException("PhieuDangKy not found with maPDK: " + maPDK));
                                    
        LopHoc lopHoc = lopHocRepo.findById(maLH).orElseThrow(() -> new EntityNotFoundException("LopHoc not found with maLH " + maLH));

        PhieuDangKyLopHoc phieuDangKyLopHoc = new PhieuDangKyLopHoc();
        phieuDangKyLopHoc.setPhieuDangKy(phieuDangKy);
        phieuDangKyLopHoc.setLopHoc(lopHoc);

        phieuDangKyLopHocRepo.save(phieuDangKyLopHoc);
    }

    public void removeClassFromRegistration(String maPDK, String maLH) {
        PhieuDangKyLopHoc pdklh = phieuDangKyLopHocRepo
            .findByPhieuDangKyMaPDKAndLopHocMaLH(maPDK, maLH)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy lớp học trong phiếu đăng ký"));
        
        phieuDangKyLopHocRepo.delete(pdklh);
    }

    public List<PhieuDangKyDTO> getPDKByStudent(String maSV) {
        SinhVien sinhVien = sinhVienRepo.findByMssv(maSV)
            .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy sinh viên với mssv: " + maSV));

        List<PhieuDangKy> pdk = phieuDangKyRepo.findBySinhVien(sinhVien)
            .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy phiếu đăng ký nào cho sinh viên với mssv: " + maSV));

        List<PhieuDangKyDTO> pdkDTO = Utils.mapPDKListEntityToPDKListDTO(pdk);
        return pdkDTO;
    }
}
