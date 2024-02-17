import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String username_;
    private String ipPort_;
    private String time_;
    private String message_;

    public Message(User user, String ipPort, String message){
        username_ = user.getUsername();
        time_ = currentTime();
        message_ = message;
        ipPort_ = ipPort;
    }

    private String currentTime(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd@HH:mm:ss");
        return currentDateTime.format(formatter);
    }

    public String toString(){
        return ("[" + username_ + " - " + ipPort_ +
                " - " + time_ +"]: " + message_);
    }

    public String getUsername(){return username_;}
    public String getIpPort(){return ipPort_;}
    public String getTime(){return time_;}
    public String getMessage(){return message_;}
}
