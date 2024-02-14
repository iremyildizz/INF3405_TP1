import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MessageDataBase {
    private static final String Data_Base_File = "messageBD.json";
    private List<Message> messages;
    private Gson gson = new Gson();

    public MessageDataBase(){messages = loadMessages();}

    private List<Message> loadMessages() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Data_Base_File));
            Type userlistType = new TypeToken<List<Message>>(){}.getType();
            return gson.fromJson(reader, userlistType);
        } catch (IOException e) {
            System.out.println("User database not created.");
            return new ArrayList<>();
        }
    }

    public void addMessage(Message message){
        messages.add(message);
        saveMessagesToDataBase();
    }

    private void saveMessagesToDataBase(){
        try(FileWriter writer = new FileWriter(Data_Base_File)) {
            gson.toJson(messages, writer);
        } catch(IOException e){
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
