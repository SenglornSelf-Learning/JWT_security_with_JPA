package springsecurity.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import springsecurity.model.AppUserDTO;
import springsecurity.model.AppUserRequest;

public interface UserService extends UserDetailsService {
    AppUserDTO insertUser(AppUserRequest appUserRequest);
}
