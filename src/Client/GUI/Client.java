package Client.GUI;

import Server.Song;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import mahoa.MaHoaAES;
import mahoa.MaHoaRSA;

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
            System.out.println("Run Send");
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
                System.out.print("");// flag is always update
                if (flag) {
                    System.out.print("Message send" + message);

                    //-------------------MaHoa----------
                    try {
                        message = MaHoaAES.maHoaAES(message, chuoi.getBytes());
                    } catch (Exception ex) {

                    }
                    //----------------------------------
                    out.write(message + "\n");
                    out.newLine();
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
            Client.executor.shutdownNow();
        } catch (IOException e) {
            try {
                out.close();
                socket.close();
                //shutdown 2 threads send and receive at Client
                Client.executor.shutdownNow();
            } catch (IOException ex) {
                Logger.getLogger(SendMessage.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            System.out.println("Xử lý login");
            if (status.equals("1")) {
                // xu ly success
                System.out.println("Success Login");
                Client.userFlag = "success";
            } else if (status.equals("0")) {
                // xu ly fail
                System.out.println("Fail Login");
                String infoError = stringToken.nextToken();
                Client.userFlag = "fail";
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
            if (status.equals("1")) {
                // xu ly success
                System.out.println("Success");
                Client.userFlag = "success";
            } else if (status.equals("0")) {
                // xu ly fail
                System.out.println("Fail");
                String infoError = stringToken.nextToken();
                Client.userFlag = "fail";
            }
        } catch (Exception e) {
            System.out.println("Exception at Handle Sign Up with message is " + e.getMessage());
        }
    }

    public void HandleSearchSong(String res) {
        try {
            System.out.println("Handle search song");
            StringTokenizer stringToken = new StringTokenizer(res, ":");
            String key = stringToken.nextToken();
            String keyWord = stringToken.nextToken();
            String status = stringToken.nextToken();
            if (status.equals("1")) {
                // xu ly success
                System.out.println("Song key 1");
                Object resultArray = obInput.readObject();
                Client.listsSongs = (ArrayList<Song>) (resultArray);
                System.out.println("\nSize>>" + Client.listsSongs.size());
                Client.songFlag = "nearly";
            } else if (status.equals("2")) {
                // xu ly success
                System.out.println("Song key 2");
                Client.song = (Song) (obInput.readObject());
                Client.songFlag = "exactly";
                Client.song.ToString();
            } else {
                Client.songFlag = "nosong";
                // xu ly fail
                String infoError = stringToken.nextToken();
            }
        } catch (Exception e) {
            System.out.println("Exception at Handle Search Song with message is " );
            System.out.println(e);
            e.printStackTrace(); 
        } finally {
           
        }
    }

    public void HandleSearchSinger(String res) {
        try {
            StringTokenizer stringToken = new StringTokenizer(res, ":");
            String key = stringToken.nextToken();
            String keyWord = stringToken.nextToken();
            String status = stringToken.nextToken();
            if (status.equals("1")) {
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
            System.out.println("run receive>>");
            while (true) {
                String data = in.readLine();
                System.out.print(""); // data is always get data from stream
                if (data != null && !data.equals("")) {
                    System.out.println(data);
                    if (data.contains("key")) {
                        StringTokenizer stringToken = new StringTokenizer(data, ":");
                        String key = stringToken.nextToken();
                        String keyWord = stringToken.nextToken();
                        switch (keyWord) {
                            case "login":
                                System.out.println("xu ly login");
                                HandleLogin(data);
                                // xu ly login
                                break;
                            case "signup":
                                System.out.println("Xử lý sign up");
                                HandleSignUp(data);
                                // xu ly sign up
                                break;
                            case "music":
                                // xu ly music
                                HandleSearchSong(data);
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
    public static ArrayList<Song> listsSongs;
    public static Song song;
    public static String clientName = "";
    public static String userFlag = "";
    public static String songFlag = "";
    private static boolean hasData = false;
    public static String message = "";
    private BufferedWriter out;
    private BufferedReader in;
    private ObjectInputStream obInput;
    public static ExecutorService executor;
    public SendMessage send;
    public ReceiveMessage recv;

    public Client() {
        try {
            socket = new Socket(host, port);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            obInput = new ObjectInputStream(socket.getInputStream());
            executor = Executors.newFixedThreadPool(2);
            send = new SendMessage(socket, out);
            recv = new ReceiveMessage(socket, in, obInput);
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
