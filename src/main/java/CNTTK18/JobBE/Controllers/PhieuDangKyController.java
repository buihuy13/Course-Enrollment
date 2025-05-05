package CNTTK18.JobBE.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import CNTTK18.JobBE.DTO.PDK.PDKDTO;
import CNTTK18.JobBE.DTO.PDK.PdkClass;
import CNTTK18.JobBE.Services.PhieuDangKyService;

@RestController
public class PhieuDangKyController {
    private PhieuDangKyService service;

    public PhieuDangKyController(PhieuDangKyService service) {
        this.service = service;
    }

    @PutMapping("phieudangky/{maPDK}")
    public ResponseEntity<?> updatePdk(@PathVariable String maPDK, @RequestBody PDKDTO pdk) {
        service.update(maPDK, pdk);
        return ResponseEntity.ok().build();
    }

    @PutMapping("phieudangky/addclass")
    public ResponseEntity<?> addClass(@RequestBody PdkClass hello) {
        service.addClass(hello);
        return ResponseEntity.ok().build();
    }

    @PutMapping("phieudangky/removeclass")
    public ResponseEntity<?> removeClass(@RequestBody PdkClass hi) {
        service.removeClass(hi);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("phieudangky/{maPDK}")
    public ResponseEntity<?> deletePDK(@PathVariable String maPDK) {
        service.deletePDK(maPDK);
        return ResponseEntity.ok().build();
    }

}
