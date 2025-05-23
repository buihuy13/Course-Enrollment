package CNTTK18.JobBE.Services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import CNTTK18.JobBE.Models.UserPrincipal;
import CNTTK18.JobBE.Models.Users;
import CNTTK18.JobBE.Repositories.UsersRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UsersRepo usersRepo;

    public MyUserDetailsService(UsersRepo usersRepo)
    {
        this.usersRepo = usersRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //When login, Spring will call this method to get user by username
        Users user = usersRepo.findByEmail(email);

        if (user == null)
        {
            throw new UsernameNotFoundException("User not found");
        }
        //Return UserPrincipal object
        return new UserPrincipal(user);
    }

}
