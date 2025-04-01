package CNTTK18.JobBE.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import CNTTK18.JobBE.DTO.Password;
import CNTTK18.JobBE.Models.Users;
import CNTTK18.JobBE.Repositories.GiangVienRepo;
import CNTTK18.JobBE.Repositories.SinhVienRepo;
import CNTTK18.JobBE.Repositories.UsersRepo;

@Service
public class UsersService {
    @Autowired
    private UsersRepo userrepo;
    @Autowired
    private SinhVienRepo svrepo;
    @Autowired
    private GiangVienRepo gvrepo;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Users getUserByID(String id) {
        Users user = userrepo.findUsersById(id);
        if (user.getRole().getRoleName().equals("SINHVIEN")) {
            return svrepo.findSinhVienById(id);
        } else if (user.getRole().getRoleName().equals("GIANGVIEN")) {
            return gvrepo.findGiangVienById(id);
        } else
            return user;
    }

    public boolean correctPass(Password pass) {
        if (pass.getEmail() == null || pass.getOldpassword() == null) {
            return false;
        }
        Users user = userrepo.findByEmail(pass.getEmail());
        if (user == null) {
            return false; // Người dùng không tồn tại
        }
        return encoder.matches(pass.getOldpassword(), user.getPassword());
    }

    public void updatePassword(Users user, String newPassword) {
        user.setPassword(encoder.encode(newPassword)); // Mã hóa mật khẩu mới
        userrepo.save(user);
    }

    public Users findByEmail(String email) {
        return userrepo.findByEmail(email);
    }
}