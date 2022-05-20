package edu.school21.sockets.models;

public class User {

    private Integer  identifier;
    private String name;
    private String password;
    public User(){
        identifier = 0;
        name = "";
        password = "111";
    }

    public User(Integer identifier, String email, String password ){
        this.identifier = identifier;
        this.name = email;
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


    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User:{identifier = " + identifier + ", name = " + name + ", password =" + password + "}";
    }
}
