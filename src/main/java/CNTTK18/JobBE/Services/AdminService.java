package CNTTK18.JobBE.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import CNTTK18.JobBE.DTO.Request.UserRequest;
import CNTTK18.JobBE.DTO.User.StudentDTO;
import CNTTK18.JobBE.DTO.User.TeacherDTO;
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

    public List<UserRequest> getAllUsers() {
        List<Users> userList = usersRepo.findAll();

        if(userList.isEmpty()) {
            throw new EntityNotFoundException("Not Found User!");
        }

        List<UserRequest> userListDTO = Utils.mapUserListEntityToUserListDTO(userList);

        return userListDTO;
    }

    public UserRequest getUserById(String userId) {
        Users user = usersRepo.findUsersById(userId);

        if(user == null) {
            throw new EntityNotFoundException("Not Found User!" );
        }

        UserRequest userDTO = Utils.mapUserEntityToUserDTO(user);

        return userDTO;
    }

    @Transactional
    public StudentDTO createStudent(UserRequest userRequest) {
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

        student.setPhieuDangKy(null);

        sinhVienRepo.save(student);

        StudentDTO userDTO = Utils.mapStudentEntityToStudentDTO(student);
        return userDTO;
    }

    @Transactional
    public TeacherDTO createTeacher(UserRequest userRequest) {
        GiangVien teacher = new GiangVien();

        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        teacher.setId(UUID.randomUUID().toString().replace("-", "").substring(0, 30));
        teacher.setEmail(userRequest.getEmail());
        teacher.setHoten(userRequest.getName());
        teacher.setNgaysinh(userRequest.getDateOfBirth());
        teacher.setGioitinh(userRequest.getSex());
        teacher.setPassword(userRequest.getPassword());

        Roles role = roleRepo.findById(userRequest.getRole()).orElseThrow(() -> new EntityNotFoundException("Not Found role with RoleId" + userRequest.getRole()));
        teacher.setRole(role);

        teacher.setMsgv(userRequest.getMs());

        Khoa khoa = khoaRepo.findById(userRequest.getMakhoa()).orElseThrow(() -> new EntityNotFoundException("Not Found Khoa with id" + userRequest.getMakhoa()));
        teacher.setKhoa(khoa);

        giangVienRepo.save(teacher);

        TeacherDTO userDTO = Utils.mapTeacherEntityToTeacherDTO(teacher);
        return userDTO;
    }

    @Transactional
    public StudentDTO updateStudent(String userId, UserRequest userRequest) {
        SinhVien updateSV = sinhVienRepo.findById(userId).orElseThrow(() -> new EntityNotFoundException("Not Found Student with Id " + userId));

        updateSV.setEmail(userRequest.getEmail());
        updateSV.setNgaysinh(userRequest.getDateOfBirth());
        updateSV.setHoten(userRequest.getName());
        updateSV.setGioitinh(userRequest.getSex());
        updateSV.setMssv(userRequest.getMs());

        ChuyenNganh chuyenNganh = chuyenNganhRepo.findById(userRequest.getManganh()).orElseThrow(() -> new EntityNotFoundException("Not Found Chuyen Nganh"));
        updateSV.setChuyenNganh(chuyenNganh);

        if(userRequest.getMapdk() != null) {
            PhieuDangKy pdk = phieuDangKyRepo.findById(userRequest.getMapdk()).orElseThrow(() -> new EntityNotFoundException("Not Found PDK"));
            updateSV.setPhieuDangKy(pdk);
        }

        sinhVienRepo.save(updateSV);
        StudentDTO studentDTO = Utils.mapStudentEntityToStudentDTO(updateSV);
        return studentDTO;
    }

    @Transactional
    public TeacherDTO updateTeacher(String userId, UserRequest userRequest) {
        GiangVien updateGV = giangVienRepo.findById(userId).orElseThrow(() -> new EntityNotFoundException("Not Found Teacher with Id " + userId));

        updateGV.setEmail(userRequest.getEmail());
        updateGV.setNgaysinh(userRequest.getDateOfBirth());
        updateGV.setHoten(userRequest.getName());
        updateGV.setGioitinh(userRequest.getSex());
        updateGV.setMsgv(userRequest.getMs());

        Khoa khoa = khoaRepo.findById(userRequest.getMakhoa()).orElseThrow(() -> new EntityNotFoundException("Not Found"));
        updateGV.setKhoa(khoa);

        giangVienRepo.save(updateGV);
        TeacherDTO teacherDTO = Utils.mapTeacherEntityToTeacherDTO(updateGV);
        return teacherDTO;
    }

    public void deleteUser(String userId) {
        Users user = usersRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Users", "id", userId));

        if(user.getRole().getId() == 1) {
            SinhVien sv = sinhVienRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("SinhVien", "id", userId));
            sinhVienRepo.delete(sv);
        } else if (user.getRole().getId() == 2) {
            GiangVien gv = giangVienRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("GiangVien", "id", userId));
            giangVienRepo.delete(gv);
        }

        usersRepo.delete(user);
    }
}
