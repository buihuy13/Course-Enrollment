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

    @GetMapping("getAllUser")
    public ResponseEntity<List<UserDetailsDTO>> getAllUsers() {
        List<UserDetailsDTO> userList = adminsService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("getUserById/{userId}")
    public ResponseEntity<UserDetailsDTO> getUserById(@PathVariable("userId")String userId) {
        UserDetailsDTO userDTO = adminsService.getUserById(userId);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDetailsDTO> createUser(@RequestBody Users userRequest, @RequestParam(value = "roleId") Integer roleId) {
        UserDetailsDTO userDTO = adminsService.createUser(userRequest, roleId);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<UserDetailsDTO> updateUser(@PathVariable("userId") String userId, @RequestBody Users userRequest) {
        UserDetailsDTO userDTO = adminsService.updateUser(userId, userRequest);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId) {
        adminsService.deleteUser(userId);
        return ResponseEntity.ok("User with ID " + userId + " has been deleted successfully.");
    }
}
