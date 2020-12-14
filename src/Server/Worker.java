/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.Future;

import java.util.logging.Level;
import java.util.logging.Logger;
import mahoa.MaHoaAES;
import mahoa.MaHoaRSA;
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
    public static ArrayList<Song> listSongs = new ArrayList<>();

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
        Gson gson = new Gson();
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
            System.out.println("Key nhận được từ client: " + keyAES);
//            String mhaes = MaHoaAES.maHoaAES("test mã hóa>>", keyAES[tt].getBytes());
//            System.out.println("mã hóa>>" + mhaes);
//            System.out.println("giải mã>>" + MaHoaAES.giaiMaAES(mhaes, keyAES[tt].getBytes()));
        } catch (Exception ex) {
            System.err.println("Key loi!!");
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
                    System.out.println("Error decode" + " from " + socket.toString() + " #Client " + myName);;
                }

                System.out.println("\nServer received: \"" + input + "\" from " + " #Client " + myName);
            }

            if (input.equals("bye")) {
                Server.workers.remove(this);
                break;
            } else if (!input.equals("") && input.contains(":")) {
                StringTokenizer stringToken = new StringTokenizer(input, ":");
                String key = stringToken.nextToken();
                String keyWord = stringToken.nextToken();
                String value = stringToken.nextToken();
                try {
                    String msgSend = "";
                    switch (keyWord) {
                        case "singer":
                            Singer singer = new Singer(value);
                            System.out.println(singer.getName());
                            if (singer.getName().equals("")) {
                                msgSend = FindSingerByShazam(value);
                            } else {
                                String data = gson.toJson(singer); // chuyển đôi đối tượng singer thành json
                                msgSend = "key:singer:1:" + data;
//                                obOut.reset();
//                                obOut.writeObject((Singer) singer);                             obOut.flush();
                            }
                            break;
                        case "login":
                            msgSend = checkLogin(value);
                            break;
                        case "signup":
                            msgSend = checkSignUp(value);
                            break;
                        case "password":
                            msgSend = setPassword(value);
                            break;
                        case "music":
                            int result = FindMusic(value);
                            switch (result) {
                                case 0:
                                    msgSend = "key:music:0:Error Server";
                                    break;
                                case 1:
                                    String data = gson.toJson(listSongs);
                                    msgSend = "key:music:1:" + data;
//                                    obOut.reset();
//                                    obOut.writeObject((ArrayList<Song>) listSongs);
                                    for (Song s : listSongs) {
                                        s.ToString();
                                    }
                            }
                            break;
                        case "musicE":
                            int index = Integer.parseInt(value);
                            Song s = listSongs.get(index);
                            if (s.getIDYoutube() == null) { // kiểm tra bài hát đã từng chạy
                                if (s.isHasKey()) {
                                    handle.GetDetailSongApi(s.getKey(), s);
                                } else {
                                    handle.GetDetailSongNCT(s.getKey(), s);
                                }
                                listSongs.set(index, s);
                            }
                            String data = gson.toJson(s);
                            msgSend = "key:music:2:" + data;

//                            obOut.reset();
//                            obOut.writeObject((Song) listSongs.get(index));obOut.flush();
                            listSongs.get(index).ToStringExactly();
                            break;
                    }
                    System.out.println("msg>>" + msgSend);

                    String ne = MaHoaAES.maHoaAES(msgSend, keyAES[tt].getBytes());
                    System.out.println("choimh =------" + ne.replace("\n", "*") + "chuoi ma hoa --------");
                    String giaima = MaHoaAES.giaiMaAES(ne, keyAES[tt].getBytes());
                    System.out.println("giai ma ==-----" + giaima + "-----giai ma ====");
                    out.write(ne);
                    out.newLine();
                    out.flush();
                    System.out.println("send msg client succes.");
                } catch (IOException ex) {
                    System.out.println("Error write object.");
                    ex.printStackTrace();
                } catch (Exception ex) {
                    Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("server nhận chuỗi rỗng!!!");
            }
        }
        System.out.println("Server is waiting for request.....");

        try {
            in.close();
            out.close();
            obOut.close();
            socket.close();
            Server.workers.remove(this);
            Future future = Server.executor.submit(this);
            future.cancel(true);
        } catch (IOException ex) {
            System.out.println("Error closing connection.");
            Server.workers.remove(this);
            Future future = Server.executor.submit(this);
            future.cancel(true);
        }
        System.out.println("Closed socket for Client " + myName);
    }

    public int FindMusic(String keySearch) {
        Handle handle = new Handle();
        listSongs.clear();
        handle.GetSongFormApiShazam(keySearch);

        try {
            Document docSearch = Jsoup.connect("https://www.nhaccuatui.com/tim-kiem/bai-hat")
                    .data("q", keySearch.replace(" ", "+"))
                    .data("b", "title")
                    .data("l", "tat-ca")
                    .data("s", "default")
                    .get();
            Element frameSearch = docSearch.getElementsByClass("sn_search_returns_frame").first();
            if (frameSearch.hasText()) {
                Elements eleSong = frameSearch.getElementsByClass("sn_search_single_song");
                handle.GetSongFromNCT(eleSong, keySearch);
            } else {
                System.out.println("Không tìm thấy bài hát nào!!!");
            }
        } catch (IOException ex) {
            System.out.println("API get list song connection error.");
            return 0;
        }

        ArrayList<Song> sTemp = new ArrayList<>();
        for (Song s : listSongs) { //xóa phần tử trùng
            if (!sTemp.contains(s) && handle.checkName(s.getName(), keySearch)) {
                sTemp.add(s);
            }
        }
        listSongs = sTemp.isEmpty() ? listSongs : sTemp;
        System.out.println("find song end<<");
        return 1;
    }

    public String checkLogin(String message) {
        StringTokenizer str = new StringTokenizer(message, " ");
        String user = str.nextToken();
        String password = str.nextToken();
        password = new Handle().md5(password); //băm md5
        System.out.println("Check Login.");

        for (String string : Server.listUsers) {
            StringTokenizer st = new StringTokenizer(string, " ");
            String u = st.nextToken();
            String pass = st.nextToken();
            if (user.equals(u) && password.equals(pass)) {
                System.out.println("Login OK");
                return "key:login:1";
            }
        }
        System.out.println("Login FAIL");
        return "key:login:0:Tài khoản hoặc mật khẩu không đúng.";
    }

    public String checkSignUp(String message) {
        StringTokenizer str = new StringTokenizer(message, " ");
        String user = str.nextToken();
        String password = str.nextToken();
        password = new Handle().md5(password);
        System.out.println("Check sign up");

        for (String string : Server.listUsers) {
            StringTokenizer st = new StringTokenizer(string, " ");
            String u = st.nextToken();
            String pass = st.nextToken();
            if (user.equals(u)) { //kiểm tra tài khoản đã tồn tại
                System.out.println("FAIL");
                return "key:signup:0:Tài khoản \"" + user + "\" đã tồn tại.";
            }
        }
        try {   //write vào file
            BufferedWriter bf = new BufferedWriter(new FileWriter("user.txt", true));
            bf.write("\n" + user + " " + password);
            bf.flush();
            Server.listUsers.add(user + " " + password);
            System.out.println("OKE");
            bf.close();
        } catch (IOException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "key:signup:1";
    }

    public String setPassword(String msg) {
        Handle handle = new Handle();
        StringTokenizer value = new StringTokenizer(msg, " ");
        String userName = value.nextToken();
        String passNew = value.nextToken();
        passNew = handle.md5(passNew);

        for (int i = 0; i < Server.listUsers.size(); i++) {
            StringTokenizer valueStr = new StringTokenizer(Server.listUsers.get(i), " ");
            String user = valueStr.nextToken();
            String pass = valueStr.nextToken();
            if (userName.equals(user)) {
                if (!passNew.equals(pass)) {//nếu giống pass cũ thì bỏ qua ghi file
                    Server.listUsers.set(i, userName + " " + passNew);
                    handle.writeFileLogin();
                }
                System.out.println("Set password>> succes.");
                return "key:password:1";
            }
        }
        System.out.println("Set password>> fails!!!");
        return "key:password:0:User " + userName + " không tồn tại.";
    }

    public String FindSingerByShazam(String key) {
        Document doc = null;
        Gson gson = new Gson();
        try {
            doc = Jsoup.connect("https://shazam.p.rapidapi.com/search")
                    .data("term", key)
                    .data("offset", "0")
                    .data("limit", "10")
                    .data("locale", "vi-VN")
                    .header("x-rapidapi-host", "shazam.p.rapidapi.com")
                    .header("x-rapidapi-key", "7dad103eb0mshfcc38b63c58db04p151074jsnf095567b964d").ignoreContentType(true)
                    .get();
        } catch (IOException ex) {
            System.out.println("Error API Shazam!!!");
        }
        if (doc.body().text().equals("{}")) {
            System.out.println("Không tìm thấy ca sĩ nào!!!");
            return "key:singer:0:Không tìm thấy ca sĩ nào!!!";
        } else {
            ArrayList<String> arrayList = new ArrayList<>();
            JsonObject json = (JsonObject) JsonParser.parseString(doc.body().text());
            JsonArray jsonArray = json.getAsJsonObject("artists").getAsJsonArray("hits");
            for (JsonElement jsonE : jsonArray) {
                String nameSinger = jsonE.getAsJsonObject().getAsJsonObject("artist").get("name").getAsString();
                arrayList.add(nameSinger);
            }
            String data = gson.toJson(arrayList);
            System.out.println("ca sĩ gần đúng>>" + data);
            return "key:singer:2:" + data;
        }
    }
}
