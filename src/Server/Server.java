package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Thuan Lam
 */
public class Server {

    public static int port = 1234;
    public static int numThread = 10;
    private static ServerSocket server = null;
    public static Vector<Worker> workers = new Vector<>();
    public static ArrayList<String> listUsers = new ArrayList<>();
    public static ExecutorService executor = null;
    public static void main(String[] args) throws IOException {
        int i = 0;
        Handle handle = new Handle();
       executor = Executors.newFixedThreadPool(numThread);
        try {
            server = new ServerSocket(port);
            System.out.println("Server binding at port " + port);
            handle.readFileLogin();
            while (true) {
                i++;
                Socket socket = server.accept();
                Worker client = new Worker(socket, Integer.toString(i));
                workers.add(client);
                executor.execute(client);
            }

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (server != null) {
                System.out.println("Server is close");
                server.close();
            }
        }
    }
}
