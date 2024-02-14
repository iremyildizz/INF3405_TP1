public class Message {
    private String username_;
    private String ip_;
    private int port_;
    private String date_;
    private String time_;
    private String message_;

    public Message(User user, String message){
        username_ = user.getUsername();
        ip_ = user.getIp_();
    }
}
