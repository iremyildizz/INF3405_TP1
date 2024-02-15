import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {
    // pour traiter la demande de chaque client sur un socket particulier
    private Socket socket;
    private int clientNumber;
    private UserDataBase userDataBase = new UserDataBase();
    private MessageDataBase chatRoom = new MessageDataBase();
    public ClientHandler(Socket socket, int clientNumber) {
        this.socket = socket;
        this.clientNumber = clientNumber; System.out.println("New connection with client#" + clientNumber + " at" + socket);
    }
    public void run() {
        DataInputStream in = null;
        DataOutputStream out = null;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Création de thread qui envoi un message à un client
        try {
            // création de canal d’envoi // envoi de message
            out.writeUTF("Hello from server - you are client#" + clientNumber);
            User newUser = askUserInfo(in, out);

            // envoi des anciens messages
            out.writeUTF(chatRoom.printLastMessages().toString());

            String newMessageText = in.readUTF();
            Message newMessage = new Message(newUser, newMessageText);
            out.writeUTF(chatRoom.addMessage(newMessage));

        } catch (IOException e) {
            System.out.println("Error handling client# " + clientNumber + ": " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Couldn't close a socket, what's going on?");
            }
            System.out.println("Connection with client # " + clientNumber+ " closed");
        }
    }

    public User askUserInfo(DataInputStream in, DataOutputStream out) throws IOException {
        out.writeUTF("What is your username");
        String username = in.readUTF();
        if((userDataBase.validateUsername(username))){
            out.writeUTF("What is your password");
            String password = in.readUTF();
                if(userDataBase.validateUser(username,password)){
                    out.writeUTF("Welcome back " + username);
                    return (userDataBase.getUser(username));
                }
                else {
                    out.writeUTF("Wrong password, connection denied");
                    return null;
                }
        }
        else{
            out.writeUTF("Hello, write your password to create an account");
            String password = in.readUTF();
            User newUser = new User(username,password,Client.serverAddress,Client.port);
            userDataBase.addUser(newUser);
            out.writeUTF("Welcome " + username);
            return newUser;
        }
    }
}