import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String username_;
    private String ip_;
    private int port_;
    private String time_;
    private String message_;

    public Message(User user, String message){
        username_ = user.getUsername();
        ip_ = user.getIp();
        port_ = user.getPort();
        time_ = currentTime();
        message_ = message;
    }

    private String currentTime(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }

    public void printMessage(){
        System.out.println("[" + username_ + " - " + ip_ + ":" + port_ +
                " - " + time_ +"]: " + message_);
    }

    public String getUsername(){return username_;}
    public String getIp(){return ip_;}
    public int getPort(){return port_;}
    public String getTime(){return time_;}
    public String getMessage_(){return message_;}
}
