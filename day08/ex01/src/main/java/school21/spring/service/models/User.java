package school21.spring.service.models;

public class User {

    private int  identifier;
    private String email;

    public User(){
        identifier = 0;
        email = "email";
    }

    public User(int identifier, String email ){
        this.identifier = identifier;
        this.email = email;
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
        return "User:{identifier = " + identifier + ", email = " + email + "}";
    }
}
