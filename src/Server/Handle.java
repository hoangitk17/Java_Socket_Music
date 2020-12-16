/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import static Server.Worker.listSongs;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.select.Elements;

/**
 *
 * @author Thuan Lam
 */
public class Handle {

    public Handle() {
    }

    public void GetSongFromNCT(Elements eleSong, String nameSearch) {
        List<Element> listSongNCT = eleSong.size() > 20 ? eleSong.subList(0, 20) : eleSong;
        for (Element element : listSongNCT) {
            String addSong = element.getElementsByTag("a").attr("href");
            String nameSong = element.getElementsByTag("a").attr("title");
            String singer = element.getElementsByTag("h4").text();
            String img = element.getElementsByTag("img").attr("data-src");
            if (img.equals("")) { //nếu bài hát không có ảnh thì sẽ lấy mặc định của NCT
                img = "https://stc-id.nixcdn.com/v11/images/avatar_default.jpg"; //link ảnh mặc định của NCT
            }
            Song temp = new Song(addSong, false, nameSong, singer, img);
            Worker.listSongs.add(temp);
            //Worker.listSongs.get(Worker.listSongs.size() - 1).ToString();
        }
        System.out.println("Get Song NCT end<<");
    }

    public void GetSongFormApiShazam(String nameSearch) {
        ArrayList<Song> array = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect("https://shazam.p.rapidapi.com/search")
                    .data("term", nameSearch)
                    .data("offset", "0")
                    .data("limit", "10")
                    .header("x-rapidapi-host", "shazam.p.rapidapi.com")
                    .header("x-rapidapi-key", "7dad103eb0mshfcc38b63c58db04p151074jsnf095567b964d").ignoreContentType(true)
                    .get();
        } catch (IOException ex) {
            System.out.println("Error connection API Shazam!!!");
        }
        JsonObject json = (JsonObject) JsonParser.parseString(doc.body().text());
        if (json.toString().equals("{}") || json.toString().equals("400 - Bad Request")) { //kiểm tra kết quả của API có rỗng thì thoát hàm
            System.out.println("API Shazam empty!!!");
            return;
        }

        JsonArray jsonArray = json.getAsJsonObject("tracks").getAsJsonArray("hits");//lấy json chứa array song
        for (JsonElement jsonA : jsonArray) {
            JsonObject jb = jsonA.getAsJsonObject().getAsJsonObject("track");
            String img = "https://stc-id.nixcdn.com/v11/images/avatar_default.jpg"; //gắn tạm ảnh mặc định
            try {
                img = jb.getAsJsonObject("images").get("background").getAsString();
            } catch (Exception e) {
                System.out.println(e);
            }
            Song temp = new Song(jb.get("key").getAsString(), true, jb.get("title").getAsString(), jb.get("subtitle").getAsString(), img);
            array.add(temp);
        }
        //boolean isFirst = true;
        for (Song s : array) { //lọc ra những bài hát trùng tên với keySearch
            if (checkName(s.getName(), nameSearch)) {
                listSongs.add(s);
            }
        }
        System.out.println("Get API End<<");
    }

    public void GetDetailSongApi(String key, Song song) {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://shazam.p.rapidapi.com/songs/get-details")
                    .data("key", key)
                    .header("x-rapidapi-host", "shazam.p.rapidapi.com")
                    .header("x-rapidapi-key", "7dad103eb0mshfcc38b63c58db04p151074jsnf095567b964d").ignoreContentType(true)
                    .get();
        } catch (IOException ex) {
            System.out.println("Error API Shazam!!!");
        }
        JsonObject json = (JsonObject) JsonParser.parseString(doc.body().text());

        JsonArray jsonArray = json.getAsJsonArray("sections"); //lấy json chứa lyrics và ID youtube
        for (JsonElement jsonA : jsonArray) {
            JsonObject jb = jsonA.getAsJsonObject();
            switch (jb.get("type").getAsString()) {
                case "LYRICS":
                    String lyrics = jb.get("text").toString().replace("\",", "\n").replace("\"", "");
                    song.setLyrics(lyrics);
                    break;
                case "VIDEO":
                    String urlYoutube = jb.getAsJsonObject("youtubeurl").getAsJsonArray("actions").get(0).getAsJsonObject().get("uri").getAsString();
                    song.setIDYoutube(urlYoutube.substring(17, 28));
                    break;
            }
        }
        System.out.println("Get detail API End<<");
    }

    public void GetDetailSongNCT(String addSong, Song song) {
        Document docDetailSong = null;
        try {
            docDetailSong = Jsoup.connect(addSong).get();
        } catch (IOException ex) {
            Logger.getLogger(Handle.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (docDetailSong != null) {
            Element eleLyrics = docDetailSong.getElementById("divLyric");
            String lyrics = GetLyricNCT(eleLyrics);
            if (lyrics.contains("id=\"btnShowEditLyric\"")) {
                lyrics = "Hiện chưa có lời bài hát nào cho " + song.getName() + " do ca sĩ " + song.getSinger() + " trình bày.";
            }
            song.setLyrics(lyrics);

            int startkey = docDetailSong.body().html().indexOf("key1=") + 5;
            int startKeySong = docDetailSong.body().html().indexOf("key: '") + 6;
            if (startkey != 4 && startKeySong != 4) {
                String key = docDetailSong.body().html().substring(startkey, startkey + 32);
                String keySong = docDetailSong.body().html().substring(startKeySong, startKeySong + 12);
                song.setMp3(GetUrlMP3(key, keySong));
            }
        }
        song.setIDYoutube(GetIdYoutubeNCT(song.getName() + " " + song.getSinger()));
        System.out.println("Get detail NCT End<<");
    }

    public String GetLyricNCT(Element eleLyrics) {
        String lyrics = eleLyrics.html().replace("<br>", "\n");
        System.out.println("Get lyrics succes.");
        return lyrics;
    }

    public String GetUrlMP3(String key, String keySong) {
        try {
            Document docUrl = Jsoup.connect("https://www.nhaccuatui.com/flash/xml")
                    .data("key1", key)
                    .data("html5", "true")
                    .data("listKey", keySong)
                    .get();
            String Location = docUrl.getElementsByTag("location").first().html();
            String urlFile = Location.substring(Location.indexOf("https"), Location.length() - 3);
            System.out.println(urlFile);
            return urlFile;
        } catch (IOException ex) {
            System.out.println("API get link file connection error.");
            return null;
        }
    }

    public String GetIdYoutubeNCT(String nameMusic) {
        try {
            Document doc = Jsoup.connect("https://www.youtube.com/results")
                    .data("search_query", nameMusic.replace(" ", "+"))
                    .get();
            String docST = doc.body().html();
            int start = docST.indexOf("videoId\":\"") + 10;
            return docST.substring(start, start + 11);
        } catch (IOException ex) {
            System.out.println("Error get ID Youtube.");
            return null;
        }
    }

    public void readFileLogin() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("user.txt"));
            String text;
            while ((text = br.readLine()) != null) {
                Server.listUsers.add(text);
            }
            br.close();
            System.out.println("Read file Login>> succes.");
        } catch (FileNotFoundException ex) {
            System.out.println("Read file Login>> fails.");
        } catch (IOException ex) {
            System.out.println("Read file Login>> fails.");
        }
    }

    public void writeFileLogin() {
        try {
            FileOutputStream fo = new FileOutputStream("user.txt");
            PrintWriter pw = new PrintWriter(fo);
            for (String user : Server.listUsers) {
                pw.println(user);
            }
            pw.close();
            System.out.println("Write file Login>> succes.");
        } catch (IOException ex) {
            System.out.println("Write file Login>> fails.");
        }
    }

    public String md5(String str) {
        String result = "";
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            BigInteger bigInteger = new BigInteger(1, digest.digest());
            result = bigInteger.toString(16);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error hash md5.");
        }
        return result;
    }

    public boolean checkName(String name, String nameSearch) {
        String match = "^" + nameSearch.toLowerCase() + ".{0,}";
        return name.toLowerCase().matches(match);
    }

}

class GetTop20 extends Thread {

    public GetTop20() {
    }

    @Override
    public void run() {
        Worker.Top20.clear();
        Handle handle = new Handle();
        try {
            Document docSearch = Jsoup.connect("https://www.nhaccuatui.com/bai-hat/top-20.nhac-viet.html").get();
            Element list_show_chart = docSearch.getElementsByClass("list_show_chart").first();
            Elements box_info_field = list_show_chart.getElementsByClass("box_info_field");
            for (Element box : box_info_field) {
                String addSong = box.getElementsByTag("a").attr("href");
                String name = box.getElementsByTag("h3").text();
                String singer = box.getElementsByTag("h4").text();
                String img = box.getElementsByTag("img").attr("src");
                Song temp = new Song(addSong, true, name, singer, img);
                handle.GetDetailSongNCT(addSong, temp);
                Worker.Top20.add(temp);
            }
            System.out.println("size arr top 20 >>" + Worker.Top20.size());
        } catch (IOException ex) {
            System.out.println("Error top 20 music.");
        }
    }
}
