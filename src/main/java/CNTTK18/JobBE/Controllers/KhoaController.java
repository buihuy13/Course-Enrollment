package CNTTK18.JobBE.Controllers;

import java.util.ArrayList;
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

import CNTTK18.JobBE.DTO.Khoa.KhoaDTO;
import CNTTK18.JobBE.Models.Khoa;
import CNTTK18.JobBE.Services.KhoaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/khoa")
public class KhoaController {

    private KhoaService service;

    public KhoaController(KhoaService sv) {
        service = sv;
    }

    public record message(String message) {
    }

    @GetMapping("")
    public ResponseEntity<?> getAllKhoa() {
        List<KhoaDTO> khoa = new ArrayList<>();
        khoa = service.getAllKhoa();
        if (khoa.isEmpty() != true) {
            return new ResponseEntity<>(khoa, HttpStatus.OK);
        } else
            return new ResponseEntity<>(new message("Không có khoa nào để trả về"), HttpStatus.OK);
    }

    @GetMapping("/{makhoa}")
    public ResponseEntity<?> getKhoaByMaKhoa(@PathVariable String makhoa) {
        KhoaDTO khoa = service.getKhoaByMaKhoa(makhoa);
        return new ResponseEntity<>(khoa, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createKhoa(@Valid @RequestBody Khoa khoa) {
        Khoa a = service.createKhoa(khoa);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    @PutMapping("/{makhoa}")
    public ResponseEntity<?> updateKhoa(@RequestBody Khoa khoa, @PathVariable String makhoa) {
        service.updateKhoaByMaKhoa(khoa, makhoa);
        return new ResponseEntity<>(new message("Update thành công!"), HttpStatus.OK);
    }

    @DeleteMapping("/{makhoa}")
    public ResponseEntity<?> deletedKhoaByMaKhoa(@PathVariable String makhoa) {
        service.deletedKhoa(makhoa);
        return new ResponseEntity<>(new message("Xóa thành công!"), HttpStatus.OK);
    }

}
