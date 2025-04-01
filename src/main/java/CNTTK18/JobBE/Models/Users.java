package CNTTK18.JobBE.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private int id;

    @NotNull 
    @Size(min = 6, max = 50)
    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @Email
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true)
    private String phone;
    private String address;
    @Size(min = 6, max = 50)
    @Column(length = 50)
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles role;
}
