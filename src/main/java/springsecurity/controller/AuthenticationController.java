package springsecurity.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import springsecurity.jwt.JwtTokenUtil;
import springsecurity.model.ApiResponse;
import springsecurity.model.AppUserDTO;
import springsecurity.model.AppUserRequest;
import springsecurity.model.jwt.JwtRequest;
import springsecurity.model.jwt.JwtResponse;
import springsecurity.service.UserService;

@RestController()
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;

    private final JwtTokenUtil jwtTokenUtil;

    private final AuthenticationManager authenticationManager;

    public AuthenticationController(UserService userService, JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        authenticate(jwtRequest.getEmail(), jwtRequest.getPassword());

        final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }
    /*
    * This method is performance on validate email and password
    * */
    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> create(@RequestBody AppUserRequest appUserRequest) {
        AppUserDTO appUserDTO = userService.insertUser(appUserRequest);
        ApiResponse<AppUserDTO> response = new ApiResponse<>(
                "SUCCESS",
                HttpStatus.OK,
                appUserDTO
        );
        return ResponseEntity.ok(response);
    }
}
