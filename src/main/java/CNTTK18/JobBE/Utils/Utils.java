package CNTTK18.JobBE.Utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import CNTTK18.JobBE.DTO.Api.UserDetailsDTO;
import CNTTK18.JobBE.Models.Admin;
import CNTTK18.JobBE.Models.Users;

public class Utils {
    public static UserDetailsDTO mapUserEntityToUserDetailsDTO(Users user) {
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();

        userDetailsDTO.setId(user.getId());
        userDetailsDTO.setEmail(user.getEmail());
        userDetailsDTO.setDateOfBirth(user.getNgaysinh());
        userDetailsDTO.setName(user.getHoten());
        userDetailsDTO.setSex(user.getGioitinh());
        userDetailsDTO.setRole(user.getRole().getRoleName());

        return userDetailsDTO;
    }

    public static List<UserDetailsDTO> mapUserListEntityToUserListDTO(List<Users> usersList) {
        return usersList.stream()
                        .map(Utils::mapUserEntityToUserDetailsDTO)
                        .collect(Collectors.toList());
    }
}
