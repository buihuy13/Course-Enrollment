package CNTTK18.JobBE.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CNTTK18.JobBE.DTO.Nganh.NganhDTO;
import CNTTK18.JobBE.DTO.Nganh.NganhReturnDTO;
import CNTTK18.JobBE.DTO.Response.ResponseMessage;
import CNTTK18.JobBE.Services.NganhService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("majors")
@PreAuthorize("hasRole('ADMIN')")
public class NganhController {
    private final NganhService nganhService;

    public NganhController(NganhService nganhService) {
        this.nganhService = nganhService;
    }

    @GetMapping("")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getAllMajors() {
        return ResponseEntity.ok(nganhService.getAllMajors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMajorById(@PathVariable String id) {
        return ResponseEntity.ok(nganhService.getMajorById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> createNganh(@RequestBody @Valid NganhReturnDTO nganhReturnDTO) {
        nganhService.createNganh(nganhReturnDTO);
        return new ResponseEntity<>(new ResponseMessage("Create Nganh successfully!"), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNganh(@RequestBody @Valid NganhDTO nganhReturnDTO, @PathVariable String id) {
        nganhService.updateNganh(id, nganhReturnDTO);
        return ResponseEntity.ok(new ResponseMessage("Update Nganh successfully!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNganh(@PathVariable String id) {
        nganhService.deleteNganh(id);
        return ResponseEntity.ok(new ResponseMessage("Delete Nganh successfully!"));
    }
}
