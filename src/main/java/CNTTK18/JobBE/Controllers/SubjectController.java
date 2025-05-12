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

import CNTTK18.JobBE.DTO.MonHoc.CreateSubjectDTO;
import CNTTK18.JobBE.DTO.MonHoc.SubjectDTO;
import CNTTK18.JobBE.Services.SubjectService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/subjects")
@PreAuthorize("hasRole('ADMIN')")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    record MessageResponse(String message) {}

    @GetMapping("")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getAllSubjects() {
        var subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubjectById(@PathVariable String id) {
        var subject = subjectService.getSubjectById(id);
        return ResponseEntity.ok(subject);
    }

    @PostMapping("")
    public ResponseEntity<MessageResponse> createSubject(@Valid @RequestBody CreateSubjectDTO newSubject) {
        subjectService.createSubject(newSubject);

        return new ResponseEntity<>(new MessageResponse("Subject created successfully"), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateSubject(@PathVariable String id, @Valid @RequestBody SubjectDTO newSubject) {
        subjectService.updateSubject(id, newSubject);

        return new ResponseEntity<>(new MessageResponse("Subject updated successfully"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteSubject(@PathVariable String id) {
        subjectService.deleteSubject(id);
        return new ResponseEntity<>(new MessageResponse("Subject deleted successfully"), HttpStatus.OK);
    }

}
