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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import CNTTK18.JobBE.DTO.Class.ClassDTO;
import CNTTK18.JobBE.DTO.Class.ClassDetailsDTO;
import CNTTK18.JobBE.Helper.ExcelHelper;
import CNTTK18.JobBE.Services.ClassService;

@RestController
public class ClassController {
    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    record MessageResponse(String message) {}

    @GetMapping("classes")
    public ResponseEntity<?> getAllClasses() {
        List<ClassDetailsDTO> classes = classService.getAllClasses();
        return ResponseEntity.ok(classes);
    }

    @GetMapping("classes/{id}")
    public ResponseEntity<?> getClassById(@PathVariable String id) {
        ClassDetailsDTO lophoc = classService.getClassById(id);
        return ResponseEntity.ok(lophoc);
    }

    @PutMapping("classes/{id}")
    public ResponseEntity<?> updateClassById(@PathVariable String id, @RequestBody ClassDTO updatedClass) {
        classService.updateClassById(id, updatedClass);
        return ResponseEntity.ok(new MessageResponse("Cập nhật lớp học thành công!"));
    }

    @DeleteMapping("classes/{id}")
    public ResponseEntity<?> deleteClassById(@PathVariable String id) {
        classService.deleteClassById(id);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/classes/upload")
    public ResponseEntity<?> uploadClasses(@RequestParam("file") MultipartFile file) {
        try {
            if (ExcelHelper.hasExcelFormat(file)) {
                classService.processExcelFile(file);
                return ResponseEntity.ok().body(new MessageResponse("Upload file thành công!"));
            }
            return ResponseEntity.badRequest().body(new MessageResponse("Upload file sai định dạng, không phải file excel!"));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Upload file thất bại!"));
        }
    }
}
