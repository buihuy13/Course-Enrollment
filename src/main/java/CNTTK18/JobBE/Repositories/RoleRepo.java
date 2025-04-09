package CNTTK18.JobBE.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CNTTK18.JobBE.Models.Roles;

@Repository
public interface RoleRepo extends JpaRepository<Roles, Integer>{

    Optional<Roles> findByRoleName(String roleName);

}
