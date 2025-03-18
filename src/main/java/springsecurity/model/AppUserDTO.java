package springsecurity.model;

import jakarta.persistence.Column;


public class AppUserDTO {
    private Integer id;

    @Column(name = "user_name", length = 25)
    private String userName;

    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public AppUserDTO() {
    }

    public AppUserDTO(Integer id, String userName, String email, String role) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.role = role;
    }

    /* This class use for register new user */
}
