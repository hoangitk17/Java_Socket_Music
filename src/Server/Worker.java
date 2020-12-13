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
            String mhaes = MaHoaAES.maHoaAES("test mã hóa>>", keyAES[tt].getBytes());
            System.out.println("mã hóa>>" + mhaes);
            System.out.println("giải mã>>" + MaHoaAES.giaiMaAES(mhaes, keyAES[tt].getBytes()));
        } catch (Exception ex) {
            System.err.println("Key loi!!");
        }
        System.out.println("\nWaiting for client...");

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

                System.out.println("\nServer received: " + input + " from " + " #Client " + myName);
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
                    switch (keyWord) {
                        case "singer":
                            Singer singer = new Singer(value);
                            System.out.println(singer.getName());
                            if (singer.getName().equals("")) {
                                String result = FindSingerByShazam(value);
                                out.write(result);
//                                out.write(MaHoaAES.maHoaAES(result, keyAES[tt].getBytes()));
                                out.newLine();
                                out.flush();
                            } else {
                                out.write("key:singer:1");
                                out.newLine();
                                out.flush();
                                obOut.reset();
                                obOut.writeObject((Singer) singer);
                                obOut.flush();
                                System.out.println("Send siger success");
                            }
                            break;
                        case "login":
                            checkLogin(value);
                            break;
                        case "signup":
                            checkSignUp(value);
                            break;
                        case "password":
                            setPassword(value);
                            break;
                        case "music":
                            int result = FindMusic(value);
                            switch (result) {
                                case 0:
                                    out.write("key:music:0:Error Server");
                                    out.newLine();
                                    out.flush();
                                    break;
                                case 1:
                                    out.write("key:music:1");
                                    out.newLine();
                                    out.flush();
                                    obOut.reset();
                                    obOut.writeObject((ArrayList<Song>) listSongs);
                                    for (Song s : listSongs) {
                                        s.ToString();
                                    }
                                    obOut.flush();

                            }
                            System.out.println("Send Music>> succes.");
                            break;
                        case "musicE":
                            int index = Integer.parseInt(value);
                            Song s = listSongs.get(index);
                            if (s.isHasKey()) {
                                handle.GetDetailSongApi(s.getKey(), listSongs.get(index));
                            } else {
                                handle.GetDetailSongNCT(s.getKey(), s);
                            }
                            out.write("key:music:2");
                            out.newLine();
                            out.flush();
                            obOut.reset();
                            obOut.writeObject((Song) listSongs.get(index));
                            listSongs.get(index).ToStringExactly();

                            obOut.flush();
                            System.out.println("Send Music Exactly>> succes.");
                            break;
                    }
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

    public void checkLogin(String message) {
        StringTokenizer str = new StringTokenizer(message, " ");
        String user = str.nextToken();
        String password = str.nextToken();
        password = new Handle().md5(password); //băm md5
        System.out.println("Check check Login.");

        String result = null;
        for (String string : Server.listUsers) {
            StringTokenizer st = new StringTokenizer(string, " ");
            String u = st.nextToken();
            String pass = st.nextToken();
            if (user.equals(u) && password.equals(pass)) {
                result = "key:login:1";
                System.out.println("OK");
                break;
            }
        }
        if (result == null) {
            result = "key:login:0:Tài khoản hoặc mật khẩu không đúng." + '\n';
            System.out.println("FAIL");
        }

        try {
            out.write(result);
            out.newLine();
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.GUI.Worker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void checkSignUp(String message) {
        StringTokenizer str = new StringTokenizer(message, " ");
        String user = str.nextToken();
        String password = str.nextToken();
        password = new Handle().md5(password);
        System.out.println("Check sign up");

        String result = null;
        for (String string : Server.listUsers) {
            StringTokenizer st = new StringTokenizer(string, " ");
            String u = st.nextToken();
            String pass = st.nextToken();
            if (user.equals(u)) { //kiểm tra tài khoản đã tồn tại
                result = "key:signup:0:Tài khoản \"" + user + "\" đã tồn tại.";
                System.out.println("FAIL");
                break;
            }
        }
        if (result == null) { //nếu chưa có tài khoản nào thì write vào file
            try {
                BufferedWriter bf = new BufferedWriter(new FileWriter("user.txt", true));
                bf.write("\n" + user + " " + password);
                bf.flush();
                Server.listUsers.add(user + " " + password);
                System.out.println("OKE");
                bf.close();
            } catch (IOException ex) {
                Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
            }
            result = "key:signup:1";
        }

        try {
            out.write(result);
            out.newLine();
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.GUI.Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPassword(String msg) {
        Handle handle = new Handle();
        StringTokenizer value = new StringTokenizer(msg, " ");
        String userName = value.nextToken();
        String passNew = value.nextToken();
        passNew = handle.md5(passNew);
        String result = null;

        for (int i = 0; i < Server.listUsers.size(); i++) {
            StringTokenizer valueStr = new StringTokenizer(Server.listUsers.get(i), " ");
            String user = valueStr.nextToken();
            String pass = valueStr.nextToken();
            if (userName.equals(user)) {
                if (!passNew.equals(pass)) {//nếu giống pass cũ thì bỏ qua ghi file
                    Server.listUsers.set(i, userName + " " + passNew);
                    handle.writeFileLogin();
                }
                result = "key:password:1";
                System.out.println("Set password>> succes.");
                break;
            }
        }
        if (result == null) {
            result = "key:password:0:User " + userName + " không tồn tại.";
            System.out.println("Set password>> fails!!!");
        }

        try {
            out.write(result);
            out.newLine();
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
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
