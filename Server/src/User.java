public class User {
    private String username_;
    private String password_;
    private String ip_;

    public User(String username, String password, String ip){
        username_ = username;
        password_ = password;
        ip_ = ip;
    }

    public String getUsername() {
        return username_;
    }

    public String getPassword() {
        return password_;
    }

    public String getIp_() {return ip_;}
}
