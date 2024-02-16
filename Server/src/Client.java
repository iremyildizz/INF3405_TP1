import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

// Application client
public class Client {
    static String serverAddress;
    static int port;
    static Scanner scanner = new Scanner(System.in);;
    private static Socket socket;

    public static void main(String[] args) throws Exception {
        // Adresse et port du serveur
        askInformation();

        // Création d'une nouvelle connexion aves le serveur
        socket = new Socket(serverAddress, port);
        System.out.format("Serveur lancé sur [%s:%d]", serverAddress, port);

        // Céatien d'un canal entrant pour recevoir les messages envoyés, par le serveur
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        // Attente de la réception d'un message envoyé par le, server sur le canal
        connectionToServer(in, out);
        if(socket.isClosed()) {
            return;
        }

        loadOldMessages(in,out);

        new Thread(() -> {
            try{
                String convoMessage;
                while((convoMessage = in.readUTF()) != null){
                    System.out.println(convoMessage);
                }
            } catch (IOException e) {
                System.out.println("Server connection lost.");
            }
        }).start();

        try{
            while(sendMessage(out)){}
        } finally {
            socket.close();
        }
    }

    public static void askInformation(){
        do {
            System.out.println("What is the server IP?");
            serverAddress = scanner.nextLine();
        } while(!Validation.isIP(serverAddress));
        do {
            System.out.println("What is the port?");
            try {
                port= Integer.parseInt(scanner.nextLine());
            }
            catch (Exception e){
                port = -1;
            }
        } while(!Validation.isValidPort(port));
    }

    private static void connectionToServer(DataInputStream in, DataOutputStream out) throws IOException {
        String helloMessageFromServer = in.readUTF();
        System.out.println(helloMessageFromServer);

        String usernameQuestion = in.readUTF();
        System.out.println(usernameQuestion);

        String username = scanner.nextLine();
        out.writeUTF(username);

        String passwordQuestion = in.readUTF();
        System.out.println(passwordQuestion);

        String password = scanner.nextLine();
        out.writeUTF(password);

        String systemMessage = in.readUTF();
        System.out.println(systemMessage);
        if(!isClientConnected(systemMessage))
            socket.close();
    }

    private static void loadOldMessages(DataInputStream in, DataOutputStream out) throws IOException{
        String oldMessages = in.readUTF();
        System.out.println(oldMessages);
    }

    private static boolean sendMessage(DataOutputStream out) throws IOException{
        String newMessage = scanner.nextLine();
        out.writeUTF(newMessage);
        return !newMessage.equals("#");
    }

    private static boolean isClientConnected(String serverMessage){
        if(serverMessage.equals("Wrong password, connection denied"))
            return false;
        return true;
    }
}
