package school21.spring.service.models;

public class User {

    private Integer  identifier;
    private String email;
    private String password;
    public User(){
        identifier = 0;
        email = "email";
        password = "111";
    }

    public User(Integer identifier, String email, String password ){
        this.identifier = identifier;
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdentifier() {
        return identifier;
    }

    public String getEmail() {
        return email;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User:{identifier = " + identifier + ", email = " + email + ", password =" + password + "}";
    }
}
