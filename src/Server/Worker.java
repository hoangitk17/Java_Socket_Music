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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
        Handle handle = new Handle();
        String input = "";
//        //-------------------MaHoa------------
//        String[] RSA = new String[20];
//        String[] keyAES = new String[20];
//        int tt = Integer.parseInt(myName);
//        try {
//            RSA[tt] = in.readLine();
//
//        } catch (IOException ex) {
//            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println("Server nhận key: " + RSA[tt] + " from " + socket.toString() + " #Client " + myName);
//        try {
//            keyAES[tt] = MaHoaRSA.giaiMaRSA(RSA[tt]);
//            System.out.println("key nhan dc tu client:" + keyAES);
//            String mhaes = MaHoaAES.maHoaAES("test mahoa", keyAES[tt].getBytes());
//            System.out.println("mahoa" + mhaes);
//            System.out.println("giai ma" + MaHoaAES.giaiMaAES(mhaes, keyAES[tt].getBytes()));
//        } catch (Exception ex) {
//            System.err.println("Key loi ");
//        }
//        //-----------------------------------------------
        while (true) {
            try {
                input = in.readLine();
            } catch (IOException ex) {
                System.out.println("Error read data client.");
                break;
            }
            //-------------------MaHoa--------------
            try {
                //input = MaHoaAES.giaiMaAES(input, keyAES[tt].getBytes());
            } catch (Exception ex) {
                System.out.println("Loi giai ma " + " from " + socket.toString() + " #Client " + myName);;
            }

            System.out.println("Server nhận: " + input + " from " + socket.toString() + " #Client " + myName);
            //------------------------------------
            System.out.println("Server received: " + input + " from " + " #Client " + myName);

            String[] st = input.split(":");
            String data = st[2];
            try {
                switch (st[1]) {
                    case "music":
                        int result = FindMusic(data);
                        switch (result) {
                            case -2:
                                obOut.writeUTF("key:music:0:Error Server");
                                System.out.println("string");
                                break;
                            case -1:
                                obOut.writeUTF("key:music:2");
                                obOut.writeObject((ArrayList<Song>) Server.listSongs);
                                System.out.println("Array");
                                break;
                            default:
                                obOut.writeUTF("key:music:1");
                                obOut.writeObject(Server.listSongs.get(result));
                                System.out.println("Song");
                        }
                        obOut.flush();
                        break;
                    case "singer":
                        break;
                    case "login":
                        StringTokenizer stLogin = new StringTokenizer(data, " ");
                        String username = stLogin.nextToken();
                        String pass = stLogin.hasMoreTokens() ? stLogin.nextToken() : "";
                        if (handle.checkLogin(username, pass)) {
                            obOut.writeUTF("key:login:1");
                        } else {
                            obOut.writeUTF("key:login:0:Tài khoản hoặc mật khẩu không đúng.");
                        }
                        obOut.flush();
                        break;
                    case "signup":
                        StringTokenizer stSignup = new StringTokenizer(data, " ");
                        String usernameS = stSignup.nextToken();
                        String passS = stSignup.hasMoreTokens() ? stSignup.nextToken() : "";
                        int rs = handle.checkSingup(usernameS, passS);
                        switch (rs) {
                            case 1:
                                obOut.writeUTF("key:signup:1");
                                break;
                            case 0:
                                obOut.writeUTF("key:signup:0:Tài khoản đã tồn tại.");
                                break;
                            case -1:
                                obOut.writeUTF("key:signup:0:Mật khẩu không thể bỏ trống.");
                                break;
                        }
                        obOut.flush();
                        break;
                }
            } catch (IOException ex) {
                System.out.println("Error write object.");
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

}
