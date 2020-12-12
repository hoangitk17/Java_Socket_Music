package Client.GUI;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import Server.Singer;
public class Server {

    public static int port = 1234;
    public static int numThread = 10;
    private static ServerSocket server = null;
    public static Vector<Worker> workers = new Vector<>();

    public static void main(String[] args) throws IOException {
        Singer s = new Singer("Wowy");
        System.out.println(s.toString());
        System.out.println("--------");
        System.out.println(s.getName());
        System.out.println(s.getSuNghiep());
        System.out.println(s.getTieuSu());
    }
}
