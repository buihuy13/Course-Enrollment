package CNTTK18.JobBE.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CNTTK18.JobBE.Models.Users;


@Repository
public interface UsersRepo extends JpaRepository<Users, String> {
    Users findByEmail(String email);
}
