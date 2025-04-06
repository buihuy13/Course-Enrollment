package CNTTK18.JobBE.Controllers;

import java.util.List;

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

import CNTTK18.JobBE.DTO.Request.UserRequest;
import CNTTK18.JobBE.DTO.Response.ResponseMessage;
import CNTTK18.JobBE.DTO.User.StudentDTO;
import CNTTK18.JobBE.DTO.User.TeacherDTO;
import CNTTK18.JobBE.Services.AdminService;



@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final AdminService adminsService;

    public AdminController(AdminService adminsService) {
        this.adminsService = adminsService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserRequest>> getAllUsers() {
        List<UserRequest> userList = adminsService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<UserRequest> getUserById(@PathVariable("userId")String userId) {
        UserRequest userDTO = adminsService.getUserById(userId);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/user/student")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody UserRequest userRequest) {
        StudentDTO studentDTO = adminsService.createStudent(userRequest);
        return new ResponseEntity<>(studentDTO, HttpStatus.CREATED);
    }

    @PostMapping("/user/teacher")
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody UserRequest userRequest) {
        TeacherDTO teacherDTO = adminsService.createTeacher(userRequest);
        return new ResponseEntity<>(teacherDTO, HttpStatus.CREATED);
    }

    @PutMapping("/user/student/{userId}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable("userId") String userId, @RequestBody UserRequest userRequest) {
        StudentDTO userDTO = adminsService.updateStudent(userId, userRequest);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/user/teacher/{userId}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable("userId") String userId, @RequestBody UserRequest userRequest) {
        TeacherDTO userDTO = adminsService.updateTeacher(userId, userRequest);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<ResponseMessage> deleteUser(@PathVariable("userId") String userId) {
        adminsService.deleteUser(userId);
        return ResponseEntity.ok(new ResponseMessage("User with ID " + userId + " has been deleted successfully."));
    }
}
