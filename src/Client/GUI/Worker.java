package Client.GUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Worker implements Runnable {

    public String clientName;
    private Socket socket;
    BufferedReader in;
    BufferedWriter out;

    public Worker(Socket s, String name) throws IOException {
        this.socket = s;
        this.clientName = name;
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
    }

    public Worker(Socket s) throws IOException {
        this.socket = s;
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
    }

    public void checkLogin(String message) {
        StringTokenizer str = new StringTokenizer(message, ";");
        String command = str.nextToken();
        String user = str.nextToken();
        String password = str.nextToken();

        try {
            if (user.equals("admin") && password.equals("admin")) {
                out.write("login;OK" + '\n');
                out.flush();
                System.out.println("OK");
            } else {
                out.write("login;FAIL" + '\n');
                out.flush();
                System.out.println("FAIL");
            }

        } catch (IOException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void checkSignUp(String message) {
        StringTokenizer str = new StringTokenizer(message, ";");
        String command = str.nextToken();
        String user = str.nextToken();
        String password = str.nextToken();
        System.out.println("Check sign up");
        try {
            if (user.equals("admin") && password.equals("admin")) {
                out.write("signup;OK" + '\n');
                out.flush();
                System.out.println("OK");
            } else {
                out.write("signup;FAIL" + '\n');
                out.flush();
                System.out.println("FAIL");
            }

        } catch (IOException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void run() {
        System.out.println("Client " + socket.toString() + " accepted");

        try {

            String input = "";
            while (true) {

                input = in.readLine();
                System.out.println(input);
                if (input.contains("login")) {
                    checkLogin(input);
                    input = "";
                }
                if (input.contains("signup")) {
                    checkSignUp(input);
                    input = "";
                }
                System.out.println("Server received: " + input + " from " + socket.toString() + " # " + clientName);
                if (input.equals("bye")) {
                    break;
                }

            }
            System.out.println("Closed socket for client " + clientName + " " + socket.toString());
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Exception at Worker");
            System.out.println(e);
        }
    }
}
