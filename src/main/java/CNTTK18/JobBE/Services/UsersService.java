package CNTTK18.JobBE.Services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import CNTTK18.JobBE.DTO.User.PasswordDTO;
import CNTTK18.JobBE.Models.Users;
import CNTTK18.JobBE.Repositories.GiangVienRepo;
import CNTTK18.JobBE.Repositories.SinhVienRepo;
import CNTTK18.JobBE.Repositories.UsersRepo;

@Service
public class UsersService {

    private final UsersRepo userrepo;
    private final SinhVienRepo svrepo;
    private final GiangVienRepo gvrepo;
    private final BCryptPasswordEncoder encoder;

    public UsersService(UsersRepo userrepo, SinhVienRepo svrepo, GiangVienRepo gvrepo, BCryptPasswordEncoder encoder)
    {
        this.userrepo = userrepo;
        this.svrepo = svrepo;
        this.gvrepo = gvrepo;
        this.encoder = encoder;
    }

    public Users getUserByID(String id) {
        Users user = userrepo.findUsersById(id);
        if (user.getRole().getRoleName().equals("SINHVIEN")) {
            return svrepo.findSinhVienById(id);
        } else if (user.getRole().getRoleName().equals("GIANGVIEN")) {
            return gvrepo.findGiangVienById(id);
        } else
            return user;
    }

    public void updatePassword(PasswordDTO pass) {
        Users user = userrepo.findByEmail(pass.getEmail());
        if (user == null) {
            throw new UsernameNotFoundException("Không tìm thấy user"); // Người dùng không tồn tại
        }
        if (encoder.matches(pass.getOldpassword(), user.getPassword()) == false)
        {
            throw new IllegalArgumentException("Mật khẩu hiện tại và mật khẩu kiểm tra không trùng nhau");
        }
        user.setPassword(encoder.encode(pass.getNewpassword())); // Mã hóa mật khẩu mới
        userrepo.save(user);
    }
}