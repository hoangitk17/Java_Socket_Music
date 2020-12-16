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
import java.util.Scanner;
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
    public static ArrayList<Song> Top20 = new ArrayList<>();

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
        GetTop20 threadTop20 = new GetTop20();
        threadTop20.start();

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
        while (threadTop20.isAlive()) {
        }
        System.out.println("thread get top 20 end.");
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
                String value = stringToken.hasMoreTokens() ? stringToken.nextToken() : "";
                try {
                    String msgSend = "";
                    switch (keyWord) {
                        case "singer":
                            msgSend = SearchSinger(value);
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
                            int result = SearchMusic(value);
                            switch (result) {
                                case 0:
                                    msgSend = "key:music:0:Error Server";
                                    break;
                                case 1:
                                    String data = gson.toJson(listSongs);
                                    msgSend = "key:music:1:" + data;
                                    for (Song s : listSongs) {
                                        s.ToString();
                                    }
                                    break;
                                case -1:
                                    msgSend = "key:music:0:Không tìm thấy bài hát nào.";
                                    break;
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
                            listSongs.get(index).ToStringExactly();
                            break;
                    }
                    System.out.println("msg>>" + msgSend);
                    String decode = MaHoaAES.maHoaAES(msgSend, keyAES[tt].getBytes());
                    //xử lý lỗi xuống dòng
                    Scanner scanner = new Scanner(decode);
                    String reMaHoa = "";
                    while (scanner.hasNextLine()) {
                        reMaHoa += scanner.nextLine();
                    }
                    scanner.close();
                    System.out.println("chuỗi mã hóa>>" + reMaHoa);
                    out.write(reMaHoa);
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

    public int SearchMusic(String keySearch) {
        Handle handle = new Handle();
        listSongs.clear();

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
        handle.GetSongFormApiShazam(keySearch);

        if (listSongs.isEmpty()) {
            return -1;
        }

        ArrayList<String> strTemp = new ArrayList<>();
        ArrayList<Song> sTemp = new ArrayList<>();
        for (Song s : listSongs) { //xóa phần tử trùng
            if (!strTemp.contains(s.getName() + s.getSinger()) && handle.checkName(s.getName(), keySearch)) {
                sTemp.add(s);
                strTemp.add(s.getName() + s.getSinger());
            }
        }
        listSongs = sTemp.isEmpty() ? listSongs : sTemp;
        System.out.println("find song end<<");
        return 1;
    }

    public String checkLogin(String message) {
        try {
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
                    Gson gson = new Gson();
                    return "key:login:1:" + gson.toJson(Top20);
                }
            }
            System.out.println("Login FAIL");
            return "key:login:0:Tài khoản hoặc mật khẩu không đúng.";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "key:login:0:Tài khoản hoặc mật khẩu không đúng.";
    }

    public String checkSignUp(String message) {
        try {
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
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "key:signup:0:Lỗi xử lý tại server";
    }

    public String setPassword(String msg) {
        try {
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
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "key:password:0:Lỗi trong quá trình xử lý.";
    }

    public ArrayList FindSingerByShazam(String key) {
        try {
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
                return null;
            } else {
                ArrayList<String> arrayList = new ArrayList<>();
                JsonObject json = (JsonObject) JsonParser.parseString(doc.body().text());
                try {
                    JsonArray jsonArray = json.getAsJsonObject("artists").getAsJsonArray("hits");
                    for (JsonElement jsonE : jsonArray) {
                        String nameSinger = jsonE.getAsJsonObject().getAsJsonObject("artist").get("name").getAsString();
                        arrayList.add(nameSinger);
                    }
                } catch (Exception e) {
                    System.out.println("Lỗi!!! API không có thẻ  'artist'.");
                }
                return arrayList;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public String SearchSinger(String keySearch) throws IOException {
        try {
            Gson gson = new Gson();
            Document docWiki = Jsoup.connect("https://vi.wikipedia.org/w/index.php")
                    .data("search", keySearch)
                    .data("title", "%C4%90%E1%BA%B7c_bi%E1%BB%87t%3AT%C3%ACm_ki%E1%BA%BFm")
                    .data("go", "Xem")
                    .data("ns0", "1").get();
            String stDocWiki = docWiki.toString();
            if (stDocWiki.contains("title=%C4%90%E1%BA%B7c_bi%E1%BB%87t%3AT%C3%ACm_ki%E1%BA%BFm&amp;go=Xem&amp;ns0=1")) {
                return "key:singer:0:Không có ca sĩ " + keySearch + "!!!";
            }
            if (!stDocWiki.contains("class=\"infobox")) {//<div class=\"mw-parser-output\">\n" + "      <p><b>
                // kiểm tra kết quả trả về có danh sách gợi ý hay không
                System.out.println("Không ca sĩ theo từ khóa tìm kiếm. Danh sách gợi ý:");
                org.jsoup.nodes.Document docHTML = Jsoup.connect("https://vi.wikipedia.org/w/index.php")
                        .data("search", "ca sĩ " + keySearch)
                        .data("title", "%C4%90%E1%BA%B7c_bi%E1%BB%87t%3AT%C3%ACm_ki%E1%BA%BFm")
                        .data("go", "Xem")
                        .data("ns0", "1").ignoreContentType(true)
                        .get();
                ArrayList<String> arr = new ArrayList<>();
                Element frameUl = docHTML.getElementsByClass("mw-search-results").first();
                Elements eleLi = frameUl.getElementsByClass("mw-search-result-heading");
                for (Element li : eleLi) {
                    if (li.text().contains("(ca sĩ") && li.text().toLowerCase().contains(keySearch.toLowerCase())) {
                        arr.add(li.text());
                        System.out.println(">>" + li.text());
                    }
                }
                ArrayList<String> arrayList = FindSingerByShazam(keySearch);
                for (String st : arrayList) {
                    if (st.toLowerCase().contains(keySearch.toLowerCase())) {
                        arr.add(st);
                        System.out.println(">>" + st);
                    }
                }
                if (arr.isEmpty() || keySearch.equals(arr.get(0))) {
                    return "key:singer:0:Lỗi tìm kiếm!!!";
                }
                String data = gson.toJson(arr);
                return "key:singer:2:" + data;
            } else {
                int start = stDocWiki.indexOf("<h1 id=\"firstHeading\" class=\"firstHeading\" lang=\"vi\">") + "<h1 id=\"firstHeading\" class=\"firstHeading\" lang=\"vi\">".length();
                int end = stDocWiki.indexOf("</h1>", start);
                String nameSinger = stDocWiki.substring(start, end).replace(" ", "_"); //lấy title truyền vào api wiki
                System.out.println("name>." + nameSinger);
                Singer singer = new Singer(nameSinger);
                String data = gson.toJson(singer);
                return "key:singer:1:" + data;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "key:singer:0:Lỗi trong quá trình xử lý!!!";
    }
}
