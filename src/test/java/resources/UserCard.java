package resources;

public class UserCard {
    private String email;
    private String password;
    private String name;

    public UserCard(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public UserCard(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserCard() {
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
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
