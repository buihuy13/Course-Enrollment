package CNTTK18.JobBE.Services;

import org.springframework.stereotype.Service;

import CNTTK18.JobBE.DTO.PDK.PDKDTO;
import CNTTK18.JobBE.DTO.PDK.PdkClass;
import CNTTK18.JobBE.Models.LopHoc;
import CNTTK18.JobBE.Models.PhieuDangKy;
import CNTTK18.JobBE.Models.PhieuDangKyLopHoc;
import CNTTK18.JobBE.Repositories.ClassRepo;
import CNTTK18.JobBE.Repositories.PhieuDangKyLopHocRepo;
import CNTTK18.JobBE.Repositories.PhieuDangKyRepo;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PhieuDangKyService {

    private PhieuDangKyRepo pdkrepo;
    private ClassRepo classrepo;
    private PhieuDangKyLopHocRepo pdkclassrepo;

    public PhieuDangKyService(PhieuDangKyRepo pdkrepo, ClassRepo classrepo, PhieuDangKyLopHocRepo pdkclassrepo) {
        this.pdkrepo = pdkrepo;
        this.classrepo = classrepo;
        this.pdkclassrepo = pdkclassrepo;
    }

    public void addClass(PdkClass hello) {
        PhieuDangKyLopHoc a = new PhieuDangKyLopHoc();
        PhieuDangKy pdk = pdkrepo.findByMaPDK(hello.getIdPdk());
        LopHoc lop = classrepo.findLopHocByMaLH(hello.getIdClass());

        if (lop == null) {
            throw new EntityNotFoundException("Không tìm thấy lớp!");
        }
        if (pdk == null) {
            throw new EntityNotFoundException("Không tìm thấy phiếu đăng ký!");
        }
        a.setPhieuDangKy(pdk);
        a.setLopHoc(lop);
        pdkclassrepo.save(a);
    }

    public void removeClass(PdkClass hi) {
        Integer a = pdkclassrepo.getID(hi.getIdPdk(), hi.getIdClass());
        if (a == null) {
            throw new EntityNotFoundException(
                    "Lớp này chưa tồn tại trong phiếu đăng ký hoặc phiếu đăng ký và lớp chưa tồn tại!");
        }
        pdkclassrepo.deleteById(a);
    }

    public void deletePDK(String maPDK) {
        PhieuDangKy a = pdkrepo.findByMaPDK(maPDK);
        if (a == null) {
            throw new EntityNotFoundException("Không tìm thấy phiếu đăng ký để xóa!");
        }
        pdkrepo.delete(a);
    }

    public void update(String maPDK, PDKDTO pdk) {
        PhieuDangKy a = pdkrepo.findByMaPDK(maPDK);
        if (a == null) {
            throw new EntityNotFoundException("Không tìm thấy phiếu đăng ký để cập nhật!");
        }
        a.setHocKi(pdk.getHocki());
        a.setNamHoc(pdk.getNamhoc());
        pdkrepo.save(a);
    }

}
