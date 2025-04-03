package CNTTK18.JobBE.Services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import CNTTK18.JobBE.DTO.Api.UserDetailsDTO;
import CNTTK18.JobBE.Exception.ResourceNotFoundException;
import CNTTK18.JobBE.Models.Roles;
import CNTTK18.JobBE.Models.Users;
import CNTTK18.JobBE.Repositories.RoleRepo;
import CNTTK18.JobBE.Repositories.UsersRepo;
import CNTTK18.JobBE.Utils.Utils;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AdminService {
    private final UsersRepo usersRepo;
    private final RoleRepo roleRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminService(UsersRepo usersRepo, RoleRepo roleRepo, BCryptPasswordEncoder passwordEncoder) {
        this.usersRepo = usersRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDetailsDTO> getAllUsers() {
        List<Users> userList = usersRepo.findAll();

        if(userList.isEmpty()) {
            throw new EntityNotFoundException("Not Found User!");
        }

        List<UserDetailsDTO> userListDTO = Utils.mapUserListEntityToUserListDTO(userList);

        return userListDTO;
    }

    public UserDetailsDTO getUserById(String userId) {
        try {
            Users user = usersRepo.findUsersById(userId);

            if(user == null) {
                throw new EntityNotFoundException("Not Found User!" );
            }

            UserDetailsDTO userDetailsDTO = Utils.mapUserEntityToUserDetailsDTO(user);

            return userDetailsDTO;
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error getting User " + e.getMessage());
        }
    }

    public UserDetailsDTO createUser(Users userRequest, int roleId) {
        try {
            Users newUser = new Users();
            Roles role = roleRepo.findById(roleId).orElseThrow(() -> new EntityNotFoundException("Role with id" + roleId + "Not Found!"));
            
            newUser.setId(userRequest.getId());
            newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            newUser.setHoten(userRequest.getHoten());
            newUser.setEmail(userRequest.getEmail());
            newUser.setGioitinh(userRequest.getGioitinh());
            newUser.setNgaysinh(userRequest.getNgaysinh());
            newUser.setRole(role);
            
            Users userCreated = usersRepo.save(newUser);
            UserDetailsDTO userDTO = Utils.mapUserEntityToUserDetailsDTO(userCreated);
            
            return userDTO;
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Roles", "id", roleId);
        } catch (Exception e) {
            throw new RuntimeException("Error creating user " + e.getMessage());
        }
    }

    public UserDetailsDTO updateUser(String userId, Users userRequest) {
        try {
            Users user = usersRepo.findById(userId).orElseThrow(() -> new EntityNotFoundException("Not Found User!")); 
    
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            user.setEmail(userRequest.getEmail());
            user.setHoten(userRequest.getHoten());
            user.setNgaysinh(userRequest.getNgaysinh());
            user.setGioitinh(userRequest.getGioitinh());


            Users userUpdated = usersRepo.save(user);

            UserDetailsDTO userDetailsDTO = Utils.mapUserEntityToUserDetailsDTO(userUpdated);

            return userDetailsDTO;
            
        } catch (EntityNotFoundException e) { // trả về 404
            throw new ResourceNotFoundException("Users", "id", userId);
        } catch (Exception e) { // trả về 500
            throw new RuntimeException("Error updating user " + e.getMessage());
        }
    }

    public void deleteUser(String userId) {
        Users user = usersRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Users", "id", userId));
        usersRepo.delete(user);
    }
}
