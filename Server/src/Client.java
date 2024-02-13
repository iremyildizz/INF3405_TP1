import java.io.DataInputStream;
import java.net.Socket;
import java.util.Scanner;

// Application client
public class Client {
    static String serverAddress;
    static int port;
    private static Socket socket;
    public static void main(String[] args) throws Exception {
        // Adresse et port du serveur
        askInformation();
//        String serverAddress = "127.0.0.1";
//        int port = 5000;

        // Création d'une nouvelle connexion aves le serveur
        socket = new Socket(serverAddress, port);
        System.out.format("Serveur lancé sur [%s:%d]", serverAddress, port);

        // Céatien d'un canal entrant pour recevoir les messages envoyés, par le serveur
        DataInputStream in = new DataInputStream(socket.getInputStream());

        // Attente de la réception d'un message envoyé par le, server sur le canal
        String helloMessageFromServer = in.readUTF();
        System.out.println(helloMessageFromServer);
        // fermeture de La connexion avec le serveur
        socket.close();
    }

    public static void askInformation(){
        Scanner scanner = new Scanner(System.in);

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
}
