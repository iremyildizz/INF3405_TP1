import java.io.DataInputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.Scanner;
public class Server {
    private static ServerSocket Listener; // Application Serveur
    static String serverAddress;
    static int serverPort;

    public static void main(String[] args) throws Exception {
        // Compteur incrémenté à chaque connexion d'un client au serveur
        int clientNumber = 0;

        // Adresse et port du serveur
        askConnection();

        // Création de la connexien pour communiquer ave les, clients
        Listener = new ServerSocket();
        Listener.setReuseAddress(true);
        InetAddress serverIP = InetAddress.getByName(serverAddress);

        // Association de l'adresse et du port à la connexien
        Listener.bind(new InetSocketAddress(serverIP, serverPort));
        System.out.format("The server is running on %s:%d%n", serverAddress, serverPort);

        try {
            // À chaque fois qu'un nouveau client se, connecte, on exécute la fonstion
            // run() de l'objet ClientHandler
            while (true) {
                // Important : la fonction accept() est bloquante: attend qu'un prochain client se connecte
                // Une nouvetle connection : on incémente le compteur clientNumber
                new ClientHandler(Listener.accept(), clientNumber++).start();
            }
        }
        finally {
            // Fermeture de la connexion
            Listener.close();
        }
    }

    public static void askConnection(){
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("What is the server IP?");
            serverAddress = scanner.nextLine();
        } while(!Validation.isIP(serverAddress));

        do {
            System.out.println("What is the port?");
            try {
                serverPort= Integer.parseInt(scanner.nextLine());
            }
            catch (Exception e){
                serverPort = -1;
            }
        } while(!Validation.isValidPort(serverPort));
    }
}
