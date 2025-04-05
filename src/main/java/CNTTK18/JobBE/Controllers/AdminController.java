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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import CNTTK18.JobBE.DTO.Api.UserDetailsDTO;
import CNTTK18.JobBE.DTO.UserDTO;
import CNTTK18.JobBE.Models.Users;
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
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userList = adminsService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId")String userId) {
        UserDTO userDTO = adminsService.getUserById(userId);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/user/student")
    public ResponseEntity<?> createStudent(@RequestBody UserDTO userRequest) {
        adminsService.createStudent(userRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/user/teacher")
    public ResponseEntity<?> createTeacher(@RequestBody UserDTO userRequest) {
        adminsService.createTeacher(userRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // @PutMapping("/user/{userId}")
    // public ResponseEntity<UserDTO> updateUser(@PathVariable("userId") String userId, @RequestBody UserDTO userRequest) {
    //     UserDTO userDTO = adminsService.updateUser(userId, userRequest);
    //     return ResponseEntity.ok(userDTO);
    // }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId) {
        adminsService.deleteUser(userId);
        return ResponseEntity.ok("User with ID " + userId + " has been deleted successfully.");
    }
}
