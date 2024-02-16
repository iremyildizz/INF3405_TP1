public class User {
    private String username_;
    private String password_;

    public User(String username, String password){
        username_ = username;
        password_ = password;
    }

    public String getUsername() {
        return username_;
    }

    public String getPassword() {
        return password_;
    }
}
