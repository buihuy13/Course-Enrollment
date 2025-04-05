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
        if (khoa != null) {
            return new ResponseEntity<>(khoa, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createKhoa(@RequestBody Khoa khoa) {
        if (khoa.getMaKhoa() != null && khoa.getTenKhoa() != null) {
            Khoa a = service.createKhoa(khoa);
            if (a != null) {
                return new ResponseEntity<>(a, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new message("Tạo khoa thất bại"), HttpStatus.BAD_REQUEST);
            }
        } else
            return new ResponseEntity<>(new message("Mã khoa hoặc tên khoa bị null"), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{makhoa}")
    public ResponseEntity<?> updateKhoa(@RequestBody Khoa khoa, @PathVariable String makhoa) {
        if (service.getKhoaByMaKhoa(makhoa) != null) {
            service.updateKhoaByMaKhoa(khoa, makhoa);
            return new ResponseEntity<>(new message("Update thành công!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new message("Không tìm thấy khoa cần update!"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{makhoa}")
    public ResponseEntity<?> deletedKhoaByMaKhoa(@PathVariable String makhoa) {
        if (service.getKhoaByMaKhoa(makhoa) != null) {
            service.deletedKhoa(makhoa);
            return new ResponseEntity<>(new message("Xóa thành công!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new message("Không tìm thấy khoa để xóa"), HttpStatus.NOT_FOUND);
        }
    }

}
