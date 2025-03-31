package CNTTK18.JobBE.Services;

import org.springframework.stereotype.Service;

import CNTTK18.JobBE.DTO.Api.UserDetailsDTO;
import CNTTK18.JobBE.Models.GiangVien;
import CNTTK18.JobBE.Models.SinhVien;
import CNTTK18.JobBE.Models.Users;
import CNTTK18.JobBE.Repositories.GiangVienRepo;
import CNTTK18.JobBE.Repositories.SinhVienRepo;
import CNTTK18.JobBE.Repositories.UsersRepo;

@Service
public class ApiService {
    private final JwtService jwtService;
    private final UsersRepo repo;
    private final SinhVienRepo sinhVienRepo;
    private final GiangVienRepo giangVienRepo;

    public ApiService(JwtService jwtService, UsersRepo repo, SinhVienRepo sinhVienRepo, GiangVienRepo giangVienRepo) {
        this.jwtService = jwtService;
        this.repo = repo;
        this.sinhVienRepo = sinhVienRepo;
        this.giangVienRepo = giangVienRepo;
    }

    public String refreshAccessToken(String refToken) throws Exception {
        String accessToken = jwtService.refreshAccessToken(refToken);
        return accessToken;
    }

    public UserDetailsDTO getUserByAccessToken(String accessToken) throws Exception {
        String email = jwtService.extractUserName(accessToken);
        Users user = repo.findByEmail(email);
        if (user == null) {
            return null;
        }

        var response = new UserDetailsDTO();
        response.setEmail(user.getEmail());
        response.setName(user.getHoten());
        response.setDateOfBirth(user.getNgaysinh());
        response.setSex(user.getGioitinh());
        response.setRole(user.getRole().getRoleName());
        if (user.getRole().getRoleName() == "SINHVIEN") 
        {
            SinhVien sinhVien = sinhVienRepo.findByEmail(user.getEmail());
            response.setMs(sinhVien.getMssv());
        } 
        else if (user.getRole().getRoleName() == "GIANGVIEN") 
        {
            GiangVien giangVien = giangVienRepo.findByEmail(user.getEmail());
            response.setMs(giangVien.getMsgv());
        }
        return response;
    }
}
