package Client.GUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SendMessage implements Runnable {

    private BufferedWriter out;
    private Socket socket;
    // flag = true send data to server ; flag = false no send
    public boolean flag = false;
    // message is data when send
    public String message = "";

    public SendMessage(Socket s, BufferedWriter o) {
        this.socket = s;
        this.out = o;
    }

    public void run() {
        try {
            while (true) {
                System.out.print("");// flag is always update
                if (flag) {
                    System.out.println(message);
                    System.out.println(flag);
                    System.out.print(message);
                    out.write(message + "\n");
                    out.flush();
                    flag = false;

                    //close when send bye
                    if (message.equals("bye")) {
                        break;
                    }
                } else {

                }
            }
            System.out.println("Client closed connection");
            out.close();
            socket.close();
            //shutdown 2 threads send and receive at Client
            Client.executor.shutdown();
        } catch (IOException e) {
        }
    }
}

class ReceiveMessage implements Runnable {

    private BufferedReader in;
    private Socket socket;
    private static ObjectInputStream obInput;

    public ReceiveMessage(Socket s, BufferedReader i, ObjectInputStream obInput) {
        this.socket = s;
        this.in = i;
        this.obInput = obInput;
    }

    public void HandleLogin(String res) {
        try {
            StringTokenizer stringToken = new StringTokenizer(res, ":");
            String key = stringToken.nextToken();
            String keyWord = stringToken.nextToken();
            String status = stringToken.nextToken();
            if(status.equals("1")) {
                // xu ly success
            } else {
                // xu ly fail
                String infoError = stringToken.nextToken();
                
            }
        } catch (Exception e) {
            System.out.println("Exception at Handle Login with message is " + e.getMessage());
        }
    }
    
    public void HandleSignUp(String res) {
        try {
            StringTokenizer stringToken = new StringTokenizer(res, ":");
            String key = stringToken.nextToken();
            String keyWord = stringToken.nextToken();
            String status = stringToken.nextToken();
            if(status.equals("1")) {
                // xu ly success
            } else {
                // xu ly fail
                String infoError = stringToken.nextToken();
                
            }
        } catch (Exception e) {
            System.out.println("Exception at Handle Sign Up with message is " + e.getMessage());
        }
    }
    
    public void HandleSearchSong(String res) {
        try {
            StringTokenizer stringToken = new StringTokenizer(res, ":");
            String key = stringToken.nextToken();
            String keyWord = stringToken.nextToken();
            String status = stringToken.nextToken();
            if(status.equals("1")) {
                // xu ly success
            } else {
                // xu ly fail
                String infoError = stringToken.nextToken();
                
            }
        } catch (Exception e) {
            System.out.println("Exception at Handle Search Song with message is " + e.getMessage());
        }
    }
    
    public void HandleSearchSinger(String res) {
        try {
            StringTokenizer stringToken = new StringTokenizer(res, ":");
            String key = stringToken.nextToken();
            String keyWord = stringToken.nextToken();
            String status = stringToken.nextToken();
            if(status.equals("1")) {
                // xu ly success
            } else {
                // xu ly fail
                String infoError = stringToken.nextToken();
                
            }
        } catch (Exception e) {
            System.out.println("Exception at Handle Search Singer with message is " + e.getMessage());
        }
    }

    public void run() {
        try {
            while (true) {
                String data = in.readLine();
                System.out.print(""); // data is always get data from stream
                if (!data.equals("")) {
                    Client.message = data;
                    if (data.length() >= 3 && data.substring(0, 3).toLowerCase().equals("key")) {
                        StringTokenizer stringToken = new StringTokenizer(data, ":");
                        String key = stringToken.nextToken();
                        String keyWord = stringToken.nextToken();
                        switch (keyWord) {
                            case "login":
                                // xu ly login
                                break;
                            case "signup":
                                // xu ly sign up
                                break;
                            case "music":
                                // xu ly music
                                break;
                            case "singer":
                                // xu ly singer
                                break;
                            default:

                                System.out.println("Default");
                        }

                    }
                    System.out.println("\nClient receive data: " + data);
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
    private static boolean hasData = false;
    public static String message = "";
    private static BufferedWriter out;
    private static BufferedReader in;
    private static ObjectInputStream obInput;
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
            recv = new ReceiveMessage(socket, in, obInput);
            executor.execute(send);
            executor.execute(recv);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
