package CNTTK18.JobBE.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CNTTK18.JobBE.DTO.Api.UserDetailsDTO;
import CNTTK18.JobBE.DTO.User.PasswordDTO;
import CNTTK18.JobBE.Models.GiangVien;
import CNTTK18.JobBE.Models.SinhVien;
import CNTTK18.JobBE.Models.Users;
import CNTTK18.JobBE.Services.UsersService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UsersController {

    private final UsersService service;

    public UsersController(UsersService service) {
        this.service = service;
    }

    record MessageResponse(String message) {
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable String id) {
        try {
            UserDetailsDTO userobj = new UserDetailsDTO();
            Users user = service.getUserByID(id);
            if (user != null) {
                userobj.setEmail(user.getEmail());
                userobj.setName(user.getHoten());
                userobj.setDateOfBirth(user.getNgaysinh());
                userobj.setRole(user.getRole().getRoleName());
                userobj.setSex(user.getGioitinh());
                if (user instanceof SinhVien) {
                    SinhVien sv = (SinhVien) user;
                    userobj.setMs(sv.getMssv());
                } else if (user instanceof GiangVien) {
                    GiangVien gv = (GiangVien) user;
                    userobj.setMs(gv.getMsgv());
                }
                return new ResponseEntity<>(userobj, HttpStatus.OK);
            } else
                return new ResponseEntity<>(new MessageResponse("Not found user"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("Fail to get user"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/newpassword")
    public ResponseEntity<?> setNewPassword(@Valid @RequestBody PasswordDTO pass) {
        try {
            service.updatePassword(pass);
            return new ResponseEntity<>(new MessageResponse("Mật khẩu được cập nhật thành công."), HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(new MessageResponse("Không tìm thấy user với email đó"),
                    HttpStatus.UNAUTHORIZED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new MessageResponse("Mật khẩu cũ và mật khẩu hiện tại không trùng nhau"),
                    HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}