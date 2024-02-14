import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserDataBase {
    private static final String Data_Base_File = "UserBD.json";
    private List<User> users;
    private Gson gson = new Gson();

    public UserDataBase() {
        users = loadUsers();
    }

    private List<User> loadUsers() {
        try(FileReader reader = new FileReader(Data_Base_File)) {
            Type userlistType = new TypeToken<ArrayList<User>>(){}.getType();
            return gson.fromJson(reader, userlistType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void addUser(User user){
        users.add(user);
    }

    private void saveUserToDataBase(){
        try(FileWriter writer = new FileWriter(Data_Base_File)) {
            gson.toJson(users, writer);
        } catch(IOException e){
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public boolean validateUser(String username, String password){
        return users.stream().anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
    }

    public boolean validateUsername(String username){
        System.out.println(username);
        System.out.println(users);
        return users.stream().anyMatch(user -> user.getUsername().equals(username));
    }
}
