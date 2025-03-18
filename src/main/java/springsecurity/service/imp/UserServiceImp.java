package springsecurity.service.imp;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springsecurity.model.AppUser;
import springsecurity.model.AppUserDTO;
import springsecurity.model.AppUserRequest;
import springsecurity.repository.UserRepository;
import springsecurity.service.UserService;

@Service
public class UserServiceImp implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = userRepository.findByEmail(email);
        System.out.println(user);
        if (user == null){
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return user;
    }

    @Override
    public AppUserDTO insertUser(AppUserRequest appUserRequest) {
        AppUser appUser = new AppUser();
        appUser.setUserName(appUserRequest.getUserName());
        appUser.setEmail(appUserRequest.getEmail());
        appUser.setPassword(passwordEncoder.encode(appUserRequest.getPassword()));
        appUser.setRole(appUserRequest.getRole());

        userRepository.save(appUser);

        return new AppUserDTO(appUser.getId(), appUser.getUsername(), appUser.getEmail(), appUser.getRole());
    }
}
