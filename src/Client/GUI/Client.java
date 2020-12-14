package Client.GUI;

import Server.Singer;
import Server.Song;
import com.google.gson.reflect.TypeToken;
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
import com.google.gson.Gson;

class SendMessage implements Runnable {

    private BufferedWriter out;
    private Socket socket;
    // flag = true send data to server ; flag = false no send
    public boolean flag = false;
    // message is data when send
    public String message = "";
    String cmhAES = "";

    public SendMessage(Socket s, BufferedWriter o, String mhAES) {
        this.socket = s;
        this.out = o;
        this.cmhAES = mhAES;
    }

    public void run() {
        try {
            System.out.println("Run Send");
            //-------------Ma Hoa------
            //chuoiMHAES = Client.randomchuoi();
            String mahoa = "";
            try {
                mahoa = MaHoaRSA.maHoaRSA(cmhAES);
            } catch (Exception ex) {
            }
            System.out.println("Client gửi ma hoa: " + mahoa + '\n');
            out.write(mahoa + '\n');
            out.flush();
            //-----------------------
            while (true) {
                System.out.print("");// flag is always update
                if (flag) {
                    System.out.println("Message send>>" + message);

                    //-------------------MaHoa----------
                    try {
                        message = MaHoaAES.maHoaAES(message, cmhAES.getBytes());
                    } catch (Exception ex) {

                    }
                    //----------------------------------
                    out.write(message);
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
    private String cmhAES = "";

    public ReceiveMessage(Socket s, BufferedReader i, String cmhAES) {
        this.socket = s;
        this.in = i;
        this.cmhAES = cmhAES;
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
                Client.message = stringToken.nextToken();
                Client.userFlag = "fail";
            }
        } catch (Exception e) {
            System.out.println("Exception at Handle Sign Up with message is " + e.getMessage());
        }
    }

    public void HandleUpdatePassword(String res) {
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
        Gson gson = new Gson();
        try {
            System.out.println("Handle search song");
            StringTokenizer stringToken = new StringTokenizer(res, ":");
            String key = stringToken.nextToken();
            String keyWord = stringToken.nextToken();
            String status = stringToken.nextToken();
            if (status.equals("1")) {
                // xu ly success
                System.out.println("Song key 1");
                String data = res.substring("key:music:1:".length());
                System.err.println("json ArrSong>>" + data);
                Client.listsSongs = gson.fromJson(data, new TypeToken<ArrayList<Song>>() {
                }.getType());
                System.out.println("\nSize>>" + Client.listsSongs.size());
                Client.songFlag = "nearly";
            } else if (status.equals("2")) {
                // xu ly success
                System.out.println("Song key 2");
                String data = res.substring("key:music:2:".length());
                System.err.println("json Song>>" + data);
                Client.song = gson.fromJson(data, new TypeToken<Song>() {
                }.getType());
                Client.song.ToStringExactly();
                Client.songFlag = "exactly";
            } else {
                Client.songFlag = "nosong";
                // xu ly fail
                String infoError = stringToken.nextToken();
            }
        } catch (Exception e) {
            System.out.println("Exception at Handle Search Song with message is ");
            System.out.println(e);
            e.printStackTrace();
        } finally {

        }
    }

    public void HandleSearchSinger(String res) {
        Gson gson = new Gson();
        try {
            StringTokenizer stringToken = new StringTokenizer(res, ":");
            String key = stringToken.nextToken();
            String keyWord = stringToken.nextToken();
            String status = stringToken.nextToken();
            if (status.equals("1")) {
                // xu ly success
                System.out.println("Singer key 1");
                String data = res.substring("key:singer:1:".length());
                System.err.println("json Singer>>" + data);
                Client.singer = gson.fromJson(data, new TypeToken<Singer>() {
                }.getType());
                System.out.println(Client.singer.getName());
                Client.singerFlag = "exactly";
            } else if (status.equals("2")) {
                //xử lý gần đúng
                System.out.println("Singer key 2");
                String data = res.substring("key:singer:2:".length());
                System.err.println("json ArrSinger>>" + data);
                ArrayList<String> listNameSinger = gson.fromJson(data, new TypeToken<ArrayList<String>>() {
                }.getType());
                System.out.println(listNameSinger);
                Client.singerFlag = "nearly";
            } else {
                // xu ly fail
                String infoError = stringToken.nextToken();
            }
        } catch (Exception e) {
            System.out.println("Exception at Handle Search Singer with message is " + e.getMessage());
        }
    }

    public void run() {
        System.out.println("run receive>>");
        while (true) {
            String data = null;
            try {
                String input = in.readLine();
                data = MaHoaAES.giaiMaAES(input, cmhAES.getBytes());
            } catch (IOException ex) {
                Logger.getLogger(ReceiveMessage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ReceiveMessage.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("ReceiveMessage>>" + data); // data is always get data from stream
            if (data != null && !data.equals("")) {
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
                        case "password":
                            System.out.println("Xử lý passowrd");
                            HandleUpdatePassword(data);
                            // xu ly sign up
                            break;
                        case "music":
                            // xu ly music
                            HandleSearchSong(data);
                            break;
                        case "singer":
                            // xu ly singer
                            HandleSearchSinger(data);
                            break;
                        default:

                            System.out.println("Default");
                    }

                } else {
//                        StringTokenizer stringToken = new StringTokenizer(data, ":");
//                        String key = stringToken.nextToken();
//                        String keyWord = stringToken.nextToken();
//                        System.out.println(key + ">>" + keyWord);
                }
//                    System.out.println("\nClient receive data: " + data);
                data = "";
            }
        }
    }
}

public class Client {

    byte[] keyValue = new byte[]{'5', '2', '3', '4', '5', '6', '7', '8', 'h', '1', '2', '3', '4', '5', '6', '8'};
    private String chuoiMHAES = "52345678h1234568";
    public final String NAME = "#default";
    private static String host = "localhost";
    private static int port = 1234;
    private static Socket socket;
    public static ArrayList<Song> listsSongs;
    public static ArrayList<String> listsSinger;
    public static Song song;
    public static Singer singer;
    public static String clientName = "";
    public static String userFlag = "";
    public static String songFlag = "";
    public static String singerFlag = "";
    private static boolean hasData = false;
    public static String message = "";
    private BufferedWriter out;
    private BufferedReader in;
    private ObjectInputStream obInput;
    public static ExecutorService executor;
    public SendMessage send;
    public ReceiveMessage recv;
    public int isConnectRefuse = 0;

    public Client() {
        try {
            socket = new Socket(host, port);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            obInput = new ObjectInputStream(socket.getInputStream());
            executor = Executors.newFixedThreadPool(2);
            send = new SendMessage(socket, out, chuoiMHAES);
            recv = new ReceiveMessage(socket, in, chuoiMHAES);
            executor.execute(send);
            executor.execute(recv);
        } catch (Exception e) {
            if (e.getMessage().equals("Connection refused: connect")) {
                isConnectRefuse = 1;
                System.out.println("Connect Refused");
            }
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
