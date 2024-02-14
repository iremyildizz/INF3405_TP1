import java.util.List;

public class MessageDataBase {
    private List<Message> messages;

    public MessageDataBase(){messages = loadMessages();}
}
