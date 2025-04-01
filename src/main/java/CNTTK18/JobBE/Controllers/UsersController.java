package CNTTK18.JobBE.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CNTTK18.JobBE.DTO.Password;
import CNTTK18.JobBE.DTO.Api.UserDetailsDTO;
import CNTTK18.JobBE.Models.GiangVien;
import CNTTK18.JobBE.Models.SinhVien;
import CNTTK18.JobBE.Models.Users;
import CNTTK18.JobBE.Services.UsersService;

@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UsersService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String id) {
        UserDetailsDTO userobj = new UserDetailsDTO();
        System.out.println("Hello");
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
            return new ResponseEntity<>("Not found user", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/newPassword")
    public ResponseEntity<?> setNewPassword(@RequestBody Password pass) {
        if (pass == null || pass.getEmail() == null) {
            return new ResponseEntity<>("Dữ liệu không hợp lệ!", HttpStatus.BAD_REQUEST);
        }

        Users user = service.findByEmail(pass.getEmail());
        if (user == null || !service.correctPass(pass)) {
            return new ResponseEntity<>("Mật khẩu cũ không đúng hoặc email không tồn tại!", HttpStatus.BAD_REQUEST);
        }

        service.updatePassword(user, pass.getNewpassword());
        return new ResponseEntity<>("Mật khẩu được cập nhật thành công.", HttpStatus.OK);
    }

}