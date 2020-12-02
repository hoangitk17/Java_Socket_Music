/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import mahoa.MaHoaRSA;
import mahoa.MaHoaAES;

/**
 *
 * @author Thuan Lam
 */
public class Worker implements Runnable {

    private String myName;
    private Socket socket;
    BufferedReader in;
    BufferedWriter out;
    ObjectOutputStream obOut;

    public Worker(Socket s, String name) throws IOException {
        this.socket = s;
        this.myName = name;
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        this.obOut = new ObjectOutputStream(s.getOutputStream());
    }

    @Override
    public void run() {
        System.out.println("Client " + myName + " " + socket.toString() + " accepted");

        String input = "";
        String[] RSA = new String[20];
        String[] keyAES = new String[20];
        int tt = Integer.parseInt(myName);
        try {
            RSA[tt] = in.readLine();

        } catch (IOException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Server nhận key: " + RSA[tt] + " from " + socket.toString() + " #Client " + myName);
        try {
            keyAES[tt] = MaHoaRSA.giaiMaRSA(RSA[tt]);
            System.out.println("key nhan dc tu client:" + keyAES);
            String mhaes = MaHoaAES.maHoaAES("test mahoa", keyAES[tt].getBytes());
            System.out.println("mahoa" + mhaes);
            System.out.println("giai ma" + MaHoaAES.giaiMaAES(mhaes, keyAES[tt].getBytes()));
        } catch (Exception ex) {
            System.err.println("Key loi ");
        }
        while (true) {
            try {
                input = in.readLine();
            } catch (IOException ex) {
                System.out.println("Error read data.");
                break;
            }
            //-------------------MaHoa--------------
            if (!input.equals("")) {
                try {
                    input = MaHoaAES.giaiMaAES(input, keyAES[tt].getBytes());
                } catch (Exception ex) {
                    System.out.println("Loi giai ma " + " from " + socket.toString() + " #Client " + myName);;
                }

                System.out.println("Server nhận: " + input + " from " + socket.toString() + " #Client " + myName);
                //------------------------------------
                System.out.println("Server received: " + input + " from " + " #Client " + myName);

                System.out.println("Server received: " + input + " from " + " #Client " + myName);
            }

            if (input.equals("bye")) {
                Server.workers.remove(this);
                break;
            } else if (!input.equals("")) {
                StringTokenizer stringToken = new StringTokenizer(input, ":");
                String key = stringToken.nextToken();
                String keyWord = stringToken.nextToken();
                String value = stringToken.nextToken();
                switch (keyWord) {
                    case "singer":
//                        Singer outputSinger = CreateSinger(input.substring(6));
//                        result = outputSinger.getName() + "\n" + outputSinger.getInfo() + "\n" + outputSinger.getListAlbums().toString()
//                                + "\n" + outputSinger.getListIDMvs().toString() + "\n" + outputSinger.getListIDSongs().toString();
//                        System.out.println(result);
                        break;
                    case "login":
                        checkLogin(value);
                        break;
                    case "signup":
                        checkSignUp(value);
                        break;
                    case "music":
                        int result = FindMusic(value);
                        try {

                            switch (result) {
                                case -2:
                                    out.write("key:music:0:Error Server");
                                    out.newLine();
                                    out.flush();
                                    System.out.println("string");
                                    break;
                                case -1:
                                    out.write("key:music:2");
                                    out.newLine();
                                    out.flush();

                                    obOut.writeObject((ArrayList<Song>) Server.listSongs);
                                    for (Song s : Server.listSongs) {
                                        s.ToString();
                                    }
                                    obOut.flush();
                                    System.out.println("Array");
                                    break;
                                default:
                                    out.write("key:music:1");
                                    out.newLine();
                                    out.flush();

                                    obOut.writeObject(Server.listSongs.get(result));
                                    Server.listSongs.get(result).ToString();
                                    obOut.flush();
                                    System.out.println("Song");
                            }

                        } catch (IOException ex) {
                            System.out.println("Error write object.");
                        }
                        break;
                }
                input = "";
            }
        }
        System.out.println("Closed socket for Client " + myName);
        try {
            in.close();
            out.close();
            obOut.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("Error closing connection.");
        }

    }

    public int FindMusic(String keySearch) {
        StringTokenizer st = new StringTokenizer(keySearch.trim(), "-");
        String nameSearch = st.nextToken();
        String singerSearch = st.hasMoreTokens() ? st.nextToken() : null;
        Handle handle = new Handle();
        Server.listSongs.clear();
        String addSearch = "https://www.nhaccuatui.com/tim-kiem/bai-hat";
        try {
            Document docSearch = Jsoup.connect(addSearch)
                    .data("q", keySearch.replace(" ", "+"))
                    .data("b", "title")
                    .data("l", "tat-ca")
                    .data("s", "default")
                    .get();

            Element frameSearch = docSearch.getElementsByClass("sn_search_returns_frame").first();
            //lấy danh sách mp3
            Elements songSearch = frameSearch.getElementsByClass("sn_search_single_song");
            int result = singerSearch == null ? handle.FindSong(songSearch, nameSearch) : handle.FindSongAndSinger(songSearch, nameSearch, singerSearch);
            return result;
        } catch (IOException ex) {
            System.out.println("API get list song connection error.");
            return -2;
        }
    }

    public void checkLogin(String message) {
        StringTokenizer str = new StringTokenizer(message, " ");
        String user = str.nextToken();
        String password = str.nextToken();

        try {
            if (user.equals("admin") && password.equals("admin")) {
                out.write("key:login:1");
                out.newLine();
                out.flush();
                System.out.println("OK");
            } else {
                out.write("key:login:0:lỗi gì đó rồi" + '\n');
                out.newLine();
                out.flush();
                System.out.println("FAIL");
            }

        } catch (IOException ex) {
            Logger.getLogger(Client.GUI.Worker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void checkSignUp(String message) {
        StringTokenizer str = new StringTokenizer(message, " ");
        String user = str.nextToken();
        String password = str.nextToken();
        System.out.println("Check sign up");
        try {
            if (user.equals("admin") && password.equals("admin")) {
                out.write("key:signup:1");  
                out.newLine();
                out.flush();
                System.out.println("OK");
            } else {
                out.write("key:signup:0:Lỗi something");
                out.newLine();
                out.flush();
                System.out.println("FAIL");
            }

        } catch (IOException ex) {
            Logger.getLogger(Client.GUI.Worker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
