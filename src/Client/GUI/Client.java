package Client.GUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import mahoa.MaHoaAES;
import mahoa.MaHoaRSA;

class SendMessage implements Runnable {

    private BufferedWriter out;
    private Socket socket;
    public boolean flag = false;
    public String message = "";

    public SendMessage(Socket s, BufferedWriter o) {
        this.socket = s;
        this.out = o;
    }

    public void run() {
        try {
            //-------------Ma Hoa------
            String chuoi = Client.randomchuoi();
            String mahoa = "";
            try {
                mahoa = MaHoaRSA.maHoaRSA(chuoi);
            } catch (Exception ex) {
            }
            System.out.println("Client gửi ma hoa: " + mahoa + '\n');
            out.write(mahoa + '\n');
            out.flush();
            //-----------------------
            while (true) {
                System.out.print("");
                if (flag) {
                    System.out.println(message);
                    System.out.println(flag);
                    System.out.print("2 --" + message);
                    //-------------------MaHoa----------

                    try {
                        message = MaHoaAES.maHoaAES(message, chuoi.getBytes());
                    } catch (Exception ex) {

                    }
                    //----------------------------------
                    out.write(message + "\n");
                    out.flush();
                    flag = false;
                    if (message.equals("bye")) {
                        break;
                    }
                } else {

                }
            }
            System.out.println("Client closed connection");
            out.close();
            socket.close();
            Client.executor.shutdown();
        } catch (IOException e) {
        }
    }

}

class ReceiveMessage implements Runnable {

    private BufferedReader in;
    private Socket socket;

    public ReceiveMessage(Socket s, BufferedReader i) {
        this.socket = s;
        this.in = i;
    }

    public void run() {
        try {

            while (true) {
                String data = in.readLine();
                System.out.print("");
                if (!data.equals("")) {
                    Client.message = data;
                    System.out.println("\n1--" + data);
                    data = "";
                }

            }
        } catch (IOException e) {
        }
    }
}

public class Client {

    public final String NAME = "#default";
    private static String host = "localhost";
    private static int port = 1234;
    private static Socket socket;
    public static String clientName = "";
    public static String message = "";
    private static BufferedWriter out;
    private static BufferedReader in;
    public static ExecutorService executor;
    public SendMessage send;
    public ReceiveMessage recv;

    public Client() {
        try {
            socket = new Socket(host, port);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            executor = Executors.newFixedThreadPool(2);
            send = new SendMessage(socket, out);
            recv = new ReceiveMessage(socket, in);
            executor.execute(send);
            executor.execute(recv);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String randomchuoi() {
        Random rd = new Random();
        String data = "";
        for (int i = 0; i < 16; i++) {
            char c = (char) (rd.nextInt(127));
            //char c = ktrd.charAt(rd.nextInt(ktrd.length()));
            data = data + c;
        }
        return data;
    }

}
