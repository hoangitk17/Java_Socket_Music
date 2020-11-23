/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.GUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Thuan Lam
 */
public class ClientTest {

    private static String host = "localhost";
    private static int port = 1234;
    private static Socket socket;

    private static BufferedWriter out;
    private static BufferedReader in;

    public static void main(String[] args) throws IOException, InterruptedException {
        socket = new Socket(host, port);
        System.out.println("Client connected");
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while (true) {
            Scanner sc = new Scanner(System.in);

            String input = "key:M;";
            input += sc.nextLine();
            System.out.println("Input: " + input);
            out.write(input);
            out.newLine();
            out.flush();
            if (input.equals("bye")) {
                break;
            }
            String data = in.readLine();
            System.out.println(data);
        }
        System.out.println("Client closed connection");
        out.close();
        socket.close();
    }
}
