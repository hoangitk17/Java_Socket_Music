/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.GUI;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import Server.Song;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
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
            int choose = in.read();
            try {
                switch (choose) {
                    case -2:
                        System.out.println((String) obInput.readObject());
                        break;
                    case -1:
                        ArrayList<Song> listSongs = (ArrayList<Song>) obInput.readObject();
                        System.out.println("không tìm thấy.\nSize>>" + listSongs.size());
                        break;
                    default:
                        Song resultSong = (Song) obInput.readObject();
                        resultSong.ToString();
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
