public class User {
    private String username_;
    private String password_;
    private String ip_;
    private int port_;

    public User(String username, String password, String ip, int port){
        username_ = username;
        password_ = password;
        ip_ = ip;
        port_ = port;
    }

    public String getUsername() {
        return username_;
    }

    public String getPassword() {
        return password_;
    }

    public String getIp() {return ip_;}

    public int getPort() {return port_;}
}
