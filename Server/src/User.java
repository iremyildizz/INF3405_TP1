public class User {
    private String username_;
    private String password_;
    private String ipPort_;

    public User(String username, String password, String ipPort){
        username_ = username;
        password_ = password;
        ipPort_ = ipPort;
    }

    public String getUsername() {
        return username_;
    }

    public String getPassword() {
        return password_;
    }
    public String getIpPort(){ return ipPort_;}
}
