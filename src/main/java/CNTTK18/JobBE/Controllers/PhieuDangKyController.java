package CNTTK18.JobBE.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CNTTK18.JobBE.DTO.PhieuDangKy.PhieuDangKyDTO;
import CNTTK18.JobBE.DTO.Response.ResponseMessage;
import CNTTK18.JobBE.Models.PhieuDangKy;
import CNTTK18.JobBE.Services.PhieuDangKyService;

@RestController
@RequestMapping("/registration-forms")
public class PhieuDangKyController {
    private final PhieuDangKyService phieuDangKyService;

    public PhieuDangKyController(PhieuDangKyService phieuDangKyService) {
        this.phieuDangKyService = phieuDangKyService;
    }

    @GetMapping
    public ResponseEntity<List<PhieuDangKyDTO>> getAllPDK() {
        List<PhieuDangKyDTO> dsPDK = phieuDangKyService.getAllPDK();
        return new ResponseEntity<>(dsPDK, HttpStatus.OK);
    } 

    @GetMapping("/{maPDK}")
    public ResponseEntity<PhieuDangKyDTO> getPDK(@PathVariable String maPDK) {
        PhieuDangKyDTO pdkDTO = phieuDangKyService.getPDK(maPDK);
        return new ResponseEntity<>(pdkDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PhieuDangKyDTO> createPhieuDangKy(@RequestBody PhieuDangKy pdk) {
        PhieuDangKyDTO phieuDangKy = phieuDangKyService.createPDK(pdk);
        return new ResponseEntity<>(phieuDangKy, HttpStatus.CREATED);
    } 

    @PutMapping("/{maPDK}")
    public ResponseEntity<PhieuDangKyDTO> updatePDK(@PathVariable("maPDK") String maPDK, @RequestBody PhieuDangKy updated) {
        PhieuDangKyDTO updatedPDKy = phieuDangKyService.updatePDK(maPDK, updated);
        return ResponseEntity.ok(updatedPDKy);
    }

    @DeleteMapping("/{maPDK}")
    public ResponseEntity<ResponseMessage> deletePDK(@PathVariable("maPDK") String maPDK) {
        phieuDangKyService.deletePDK(maPDK);
        return ResponseEntity.ok(new ResponseMessage("PDK with maPDK " + maPDK + " has been deleted successfully."));
    }

    @PostMapping("/{maPDK}/add-class/{maLH}")
    public ResponseEntity<ResponseMessage> addClassToRegistration(@PathVariable("maPDK") String maPDK, @PathVariable("maLH") String maLH) {
        phieuDangKyService.addClassToRegistration(maPDK, maLH);
        return ResponseEntity.ok(new ResponseMessage("Đã thêm lớp học"));
    }

    @DeleteMapping("{maPDK}/remove-class/{maLH}")
    public ResponseEntity<ResponseMessage> removeClassFromRegistration(@PathVariable("maPDK") String maPDK, @PathVariable("maLH") String maLH) {
        phieuDangKyService.removeClassFromRegistration(maPDK, maLH);
        return ResponseEntity.ok(new ResponseMessage("Đã xóa lớp học"));
    }
}
