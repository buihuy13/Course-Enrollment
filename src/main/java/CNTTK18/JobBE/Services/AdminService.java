package CNTTK18.JobBE.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import CNTTK18.JobBE.DTO.StudentDTO;
import CNTTK18.JobBE.DTO.TeacherDTO;
import CNTTK18.JobBE.DTO.UserDTO;
import CNTTK18.JobBE.Exception.ResourceNotFoundException;
import CNTTK18.JobBE.Models.ChuyenNganh;
import CNTTK18.JobBE.Models.GiangVien;
import CNTTK18.JobBE.Models.Khoa;
import CNTTK18.JobBE.Models.PhieuDangKy;
import CNTTK18.JobBE.Models.Roles;
import CNTTK18.JobBE.Models.SinhVien;
import CNTTK18.JobBE.Models.Users;
import CNTTK18.JobBE.Repositories.ChuyenNganhRepo;
import CNTTK18.JobBE.Repositories.GiangVienRepo;
import CNTTK18.JobBE.Repositories.KhoaRepo;
import CNTTK18.JobBE.Repositories.PhieuDangKyRepo;
import CNTTK18.JobBE.Repositories.RoleRepo;
import CNTTK18.JobBE.Repositories.SinhVienRepo;
import CNTTK18.JobBE.Repositories.UsersRepo;
import CNTTK18.JobBE.Utils.Utils;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AdminService {
    private final UsersRepo usersRepo;
    private final RoleRepo roleRepo;
    private final GiangVienRepo giangVienRepo;
    private final SinhVienRepo sinhVienRepo;
    private final ChuyenNganhRepo chuyenNganhRepo;
    private final PhieuDangKyRepo phieuDangKyRepo;
    private final KhoaRepo khoaRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminService(ChuyenNganhRepo chuyenNganhRepo, GiangVienRepo giangVienRepo, KhoaRepo khoaRepo, BCryptPasswordEncoder passwordEncoder, PhieuDangKyRepo phieuDangKyRepo, RoleRepo roleRepo, SinhVienRepo sinhVienRepo, UsersRepo usersRepo) {
        this.chuyenNganhRepo = chuyenNganhRepo;
        this.giangVienRepo = giangVienRepo;
        this.khoaRepo = khoaRepo;
        this.passwordEncoder = passwordEncoder;
        this.phieuDangKyRepo = phieuDangKyRepo;
        this.roleRepo = roleRepo;
        this.sinhVienRepo = sinhVienRepo;
        this.usersRepo = usersRepo;
    }

    public List<UserDTO> getAllUsers() {
        List<Users> userList = usersRepo.findAll();

        if(userList.isEmpty()) {
            throw new EntityNotFoundException("Not Found User!");
        }

        List<UserDTO> userListDTO = Utils.mapUserListEntityToUserListDTO(userList);

        return userListDTO;
    }

    public UserDTO getUserById(String userId) {
        Users user = usersRepo.findUsersById(userId);

        if(user == null) {
            throw new EntityNotFoundException("Not Found User!" );
        }

        UserDTO userDTO = Utils.mapUserEntityToUserDTO(user);

        return userDTO;
    }

    @Transactional
    public StudentDTO createStudent(UserDTO userRequest) {
        SinhVien student = new SinhVien();

        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        student.setId(UUID.randomUUID().toString().replace("-", "").substring(0, 30));
        student.setEmail(userRequest.getEmail());
        student.setHoten(userRequest.getName());
        student.setNgaysinh(userRequest.getDateOfBirth());
        student.setGioitinh(userRequest.getSex());
        student.setPassword(userRequest.getPassword());

        Roles role = roleRepo.findById(userRequest.getRole()).orElseThrow(() -> new EntityNotFoundException("Not Found role with RoleId" + userRequest.getRole()));
        student.setRole(role);

        student.setMssv(userRequest.getMs());

        ChuyenNganh chuyenNganh = chuyenNganhRepo.findById(userRequest.getManganh()).orElseThrow(() -> new EntityNotFoundException("Not Found Chuyen Nganh"));
        student.setChuyenNganh(chuyenNganh);

        // if (userRequest.getMapdk() != null) {
        //     phieuDangKyRepo.findById(userRequest.getMapdk())
        //             .ifPresent(student::setPhieuDangKy);
        // }  else {
        //     student.setPhieuDangKy(null);
        // }

        sinhVienRepo.save(student);

        StudentDTO userDTO = Utils.mapStudentEntityToStudentDTO(student);
        return userDTO;
    }

    public TeacherDTO createTeacher(UserDTO userRequest) {
        TeacherDTO userDTO = new TeacherDTO();

        Users user = new Users();

        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setId(UUID.randomUUID().toString().replace("-", "").substring(0, 30));
        user.setEmail(userRequest.getEmail());
        user.setHoten(userRequest.getName());
        user.setNgaysinh(userRequest.getDateOfBirth());
        user.setGioitinh(userRequest.getSex());

        if(user instanceof GiangVien) {
            GiangVien giangVien = (GiangVien) user;
            giangVien.setMsgv(userRequest.getMs());

            Khoa khoa = khoaRepo.findById(userRequest.getMakhoa()).orElseThrow(() -> new EntityNotFoundException("Not found Khoa with Id" + userRequest.getMakhoa()));
            giangVien.setKhoa(khoa);

            giangVienRepo.save(giangVien);
        }

        usersRepo.save(user);

        return userDTO;
    }

    // public UserDTO updateUser(String userId, UserDTO userRequest) {
    //     int roleId = userRequest.getRole();
    //     userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

    //     Users user = usersRepo.findById(userId).orElseThrow(() -> new EntityNotFoundException("Not Found User with Id: " + userId));
    //     UserDTO userDTO = new UserDTO();

    //     user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
    //     user.setEmail(userRequest.getEmail());
    //     user.setGioitinh(userRequest.getSex());
    //     user.setHoten(userRequest.getName());
    //     user.setNgaysinh(userRequest.getDateOfBirth());
        
    //     if (roleId == 2 && user instanceof GiangVien) { // là Giảng Viên
    //         GiangVien updateGV = (GiangVien) user;
    //         updateGV.setMsgv(userRequest.getMs());

    //         Khoa khoa = khoaRepo.findById(userRequest.getMakhoa()).orElseThrow(() -> new EntityNotFoundException("Not Found"));
    //         updateGV.setKhoa(khoa);

    //         giangVienRepo.save(updateGV);
    //         userDTO = Utils.mapTeacherEntityToTeacherDTO(updateGV);

    //     } else if (roleId == 1 && user instanceof SinhVien) { // là Sinh Vien
    //         SinhVien updateSV =(SinhVien) user;
    //         updateSV.setMssv(userRequest.getMs());

    //         ChuyenNganh chuyenNganh = chuyenNganhRepo.findById(userRequest.getManganh()).orElseThrow(() -> new EntityNotFoundException("Not Found"));
    //         updateSV.setChuyenNganh(chuyenNganh);

    //         PhieuDangKy pdk = phieuDangKyRepo.findById(userRequest.getMapdk()).orElseThrow(() -> new EntityNotFoundException("Not Found"));
    //         updateSV.setPhieuDangKy(pdk);

    //         sinhVienRepo.save(updateSV);
    //         userDTO = Utils.mapStudentEntityToStudentDTO(updateSV);
    //     } 

    //     usersRepo.save(user);
    //     return userDTO;
    // }

    public void deleteUser(String userId) {
        Users user = usersRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Users", "id", userId));
        usersRepo.delete(user);
    }
}
