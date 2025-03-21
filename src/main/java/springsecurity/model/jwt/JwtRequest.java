package springsecurity.model.jwt;

public class JwtRequest {

    private String email;
    private String password;

    public JwtRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public JwtRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /* This class use for User Request login endpoint controller  */
}
