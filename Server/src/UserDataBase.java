import com.google.gson.Gson;

import java.util.List;

public class UserDataBase {
    private static final String User_DB_File = "UserBD.gson";
    private List<User> users;
    private Gson gson = new Gson();

    public UserDataBase() {
        users = loadUsers();
    }

    private List<User> loadUsers(){}
}
