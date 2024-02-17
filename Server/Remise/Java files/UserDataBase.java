import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserDataBase {
    private static final String Data_Base_File = "UserBD.json";
    private List<User> users;
    private Gson gson = new Gson();

    public UserDataBase() {users = loadUsers();}

    private List<User> loadUsers() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Data_Base_File));
            Type userlistType = new TypeToken<List<User>>(){}.getType();
            return gson.fromJson(reader, userlistType);
        } catch (IOException e) {
            System.out.println("User database not created.");
            return new ArrayList<>();
        }
    }

    public void addUser(User user){
        users.add(user);
        saveUserToDataBase();
    }

    public User getUser(String username){
        for(User user:users){
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
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
        return users.stream().anyMatch(user -> user.getUsername().equals(username));
    }
}
