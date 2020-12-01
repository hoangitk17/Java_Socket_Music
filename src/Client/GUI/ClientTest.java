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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thuan Lam
 */
public class ClientTest {

    private static String host = "localhost";
    private static int port = 1234;
    private static Socket socket;

    private static BufferedWriter out;
    private static ObjectInputStream obInput;
    private static BufferedReader in;

    public static void main(String[] args) throws IOException, InterruptedException {
        socket = new Socket(host, port);
        System.out.println("Client connected");
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        obInput = new ObjectInputStream(socket.getInputStream());
        while (true) {
            Scanner sc = new Scanner(System.in);

            String input = "key:M;";
            input += sc.nextLine();
            System.out.println("Input>> " + input);
            out.write(input);
            out.newLine();
            out.flush();
            if (input.equals("bye")) {
                break;
            }
            String data = obInput.readUTF();
            String[] st = data.split(":");
            try {
                switch (st[2]) {
                    case "0":
                        System.out.println(st[3]);
                        break;
                    case "2":
                        Object resultArray = obInput.readObject();
                        ArrayList<Song> listSongs = (ArrayList<Song>) Object.class.cast(resultArray);
                        System.out.println("không tìm thấy.\nSize>>" + listSongs.size());
                        break;
                    default:
                        Object resultSong = obInput.readObject();
                        Song song = (Song) Object.class.cast(resultSong);
                        song.ToString();
                        break;
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientTest.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        System.out.println("Client closed connection");
        out.close();
        socket.close();
    }
}
