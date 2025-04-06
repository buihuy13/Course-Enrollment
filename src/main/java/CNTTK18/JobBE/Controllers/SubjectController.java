package CNTTK18.JobBE.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import CNTTK18.JobBE.Models.MonHoc;
import CNTTK18.JobBE.Services.SubjectService;

@RestController
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    record MessageResponse(String message) {}

    @GetMapping("/subjects")
    public ResponseEntity<List<MonHoc>> getAllSubjects() {
        List<MonHoc> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<?> getSubjectById(@PathVariable String id) {
        MonHoc subject = subjectService.getSubjectById(id);
        return ResponseEntity.ok(subject);
    }

    @PostMapping("/subjects")
    public String createSubject(String subject) {
        return "Subject created: " + subject; // Replace with actual implementation to create a new subject
    }

    @PutMapping("/subjects/{id}")
    public String updateSubject(String id, String subject) {
        return "Subject updated for ID: " + id + " with details: " + subject; // Replace with actual implementation to update a subject
    }

    @DeleteMapping("/subjects/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable String id) {
        subjectService.deleteSubject(id);
        return new ResponseEntity<>(new MessageResponse("Subject deleted successfully"), HttpStatus.OK);
    }

}
