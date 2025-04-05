package CNTTK18.JobBE.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import CNTTK18.JobBE.DTO.Class.ClassDTO;
import CNTTK18.JobBE.Models.LopHoc;
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
        List<LopHoc> classes = classService.getAllClasses();
        return ResponseEntity.ok(classes);
    }

    @GetMapping("classes/{id}")
    public ResponseEntity<?> getClassById(@PathVariable String id) {
        LopHoc lophoc = classService.getClassById(id);
        return ResponseEntity.ok(lophoc);
    }

    @PutMapping("classes/{id}")
    public ResponseEntity<?> updateClassById(@PathVariable String id, @RequestBody ClassDTO updatedClass) {
        classService.updateClassById(id, updatedClass);
        return ResponseEntity.ok(new MessageResponse("Cập nhật lớp học thành công!"));
    }

    @DeleteMapping("classes/{id}")
    public ResponseEntity<?> deleteClassById(String id) {
        classService.deleteClassById(id);
        return ResponseEntity.ok("ok");
    }
}
