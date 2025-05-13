package CNTTK18.JobBE.Utils;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import CNTTK18.JobBE.DTO.PhieuDangKy.PhieuDangKyDTO;
import CNTTK18.JobBE.DTO.PhieuDangKy.PhieuDangKyLopHocDTO;
import CNTTK18.JobBE.DTO.Request.UserRequest;
import CNTTK18.JobBE.DTO.User.StudentDTO;
import CNTTK18.JobBE.DTO.User.TeacherDTO;
import CNTTK18.JobBE.Models.Admin;
import CNTTK18.JobBE.Models.ChuyenNganh;
import CNTTK18.JobBE.Models.GiangVien;
import CNTTK18.JobBE.Models.Khoa;
import CNTTK18.JobBE.Models.PhieuDangKy;
import CNTTK18.JobBE.Models.Roles;
import CNTTK18.JobBE.Models.SinhVien;
import CNTTK18.JobBE.Models.Users;

@Component
public class Utils {
    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";

    private static String generateRandomId(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHAR_POOL.length());
            sb.append(CHAR_POOL.charAt(index));
        }

        return sb.toString();
    }

    public static UserRequest mapUserEntityToUserDTO(Users user) {
        UserRequest userDTO = new UserRequest();
        userDTO.setRole(user.getRole().getId());

        if (user.getRole().getId() == 1 && user instanceof SinhVien) {
            SinhVien sv = (SinhVien) user;

            userDTO.setMs(sv.getMssv());
            userDTO.setManganh(sv.getChuyenNganh().getMaNganh());
        } else if (user.getRole().getId() == 2 && user instanceof GiangVien) {
            GiangVien gv = (GiangVien) user;

            userDTO.setMs(gv.getMsgv());
            userDTO.setMakhoa(gv.getKhoa().getMaKhoa());
        } else if(user.getRole().getId() == 3 && user instanceof Admin) {
            Admin ad = (Admin) user;
        }

        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setDateOfBirth(user.getNgaysinh());
        userDTO.setSex(user.getGioitinh());
        userDTO.setName(user.getHoten());

        return userDTO;
    }

    public static StudentDTO mapStudentEntityToStudentDTO(SinhVien sv) {
        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setEmail(sv.getEmail());
        studentDTO.setName(sv.getHoten());
        studentDTO.setDateOfBirth(sv.getNgaysinh());
        studentDTO.setSex(sv.getGioitinh());
        studentDTO.setMssv(sv.getMssv());
        studentDTO.setManganh(sv.getChuyenNganh().getMaNganh());
        studentDTO.setRole(sv.getRole().getId());
        
        return studentDTO;
    }

    public static TeacherDTO mapTeacherEntityToTeacherDTO(GiangVien teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();

        teacherDTO.setEmail(teacher.getEmail());
        teacherDTO.setName(teacher.getHoten());
        teacherDTO.setDateOfBirth(teacher.getNgaysinh());
        teacherDTO.setSex(teacher.getGioitinh());
        teacherDTO.setMsgv(teacher.getMsgv());
        teacherDTO.setMakhoa(teacher.getKhoa().getMaKhoa());
        teacherDTO.setRole(teacher.getRole().getId());

        return teacherDTO;
    }

    // public static StudentDTO mapUserEntityToStudentDTO(Users user, String mssv) {
    // //     StudentDTO studentDTO = new StudentDTO();

    // //     studentDTO.setId(user.getId());
    // //     studentDTO.setName(user.getHoten());
    // //     studentDTO.setEmail(user.getEmail());
    // //     studentDTO.setSex(user.getGioitinh());
    // //     studentDTO.setDateOfBirth(user.getNgaysinh());
    // //     studentDTO.setRole(user.getRole().getRoleName());

    // //     return studentDTO;
    // // }

    public static SinhVien mapUserEntityToSinhVien(UserRequest user, ChuyenNganh nganh, Roles role) {
        SinhVien newSV = new SinhVien();

        // newSV.setId(UUID.randomUUID().toString().replace("-", "").substring(0, 30));
        newSV.setId(user.getId());
        newSV.setMssv(user.getMs());
        newSV.setEmail(user.getEmail());
        newSV.setPassword(user.getPassword());
        newSV.setHoten(user.getName());
        newSV.setRole(role);
        newSV.setNgaysinh(user.getDateOfBirth());
        newSV.setGioitinh(user.getSex());
        newSV.setChuyenNganh(nganh);

        return newSV;
    }

    public static GiangVien mapUserEntityToGiangVien(UserRequest user, Khoa khoa, Roles role) {
        GiangVien newGV = new GiangVien();

        // newGV.setId(UUID.randomUUID().toString().replace("-", "").substring(0, 30));
        newGV.setId(user.getId());
        newGV.setMsgv(user.getMs());
        newGV.setEmail(user.getEmail());
        newGV.setPassword(user.getPassword());
        newGV.setHoten(user.getName());
        newGV.setRole(role);
        newGV.setNgaysinh(user.getDateOfBirth());
        newGV.setGioitinh(user.getSex());
        newGV.setKhoa(khoa);

        return newGV;
    }

    public static PhieuDangKyDTO mapPhieuDangKyToPhieuDangKyDTO(PhieuDangKy entity) {
        SinhVien sv = entity.getSinhVien();

        StudentDTO studentDTO = Utils.mapStudentEntityToStudentDTO(sv);

        List<PhieuDangKyLopHocDTO> lopHocDTOs = entity.getPhieuDangKyLopHocList().stream().map(lh -> 
            new PhieuDangKyLopHocDTO(
                lh.getLopHoc().getMaLH(),
                lh.getLopHoc().getMonHoc().getTenMH(),
                lh.getLopHoc().getNgayBatDau(),
                lh.getLopHoc().getNgayKetThuc(),
                lh.getLopHoc().getHocKi(),
                lh.getLopHoc().getNamHoc(),
                lh.getLopHoc().getSoLuongSinhVien(),
                lh.getLopHoc().getTietBatDau(),
                lh.getLopHoc().getTietKetThuc(),
                lh.getLopHoc().getThu()
            )
        ).collect(Collectors.toList());

        return new PhieuDangKyDTO(
            entity.getMaPDK(),
            entity.getHocKi(),
            entity.getNamHoc(),
            entity.getTongTinChi(),
            lopHocDTOs,
            studentDTO
        );
    }

    public static List<UserRequest> mapUserListEntityToUserListDTO(List<Users> usersList) {
        return usersList.stream()
                        .map(Utils::mapUserEntityToUserDTO)
                        .collect(Collectors.toList());
    }

    public static List<PhieuDangKyDTO> mapPDKListEntityToPDKListDTO(List<PhieuDangKy> PDKList) {
        return PDKList.stream()
                        .map(Utils::mapPhieuDangKyToPhieuDangKyDTO)
                        .collect(Collectors.toList());
    }
}
