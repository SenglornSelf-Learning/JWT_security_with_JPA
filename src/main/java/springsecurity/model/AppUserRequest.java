package springsecurity.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserRequest {
    @Column(name = "user_name", length = 25)
    private String userName;

    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    /* This class use for register new user */
}
