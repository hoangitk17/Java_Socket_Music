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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import Server.Server;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
        //Handle handle = new Handle();
        for (Element element : eleSong) {
            String addSong = element.getElementsByTag("a").attr("href");
            String nameSong = element.getElementsByTag("a").attr("title");
            String singer = element.getElementsByTag("h4").text();
            String lyrics = GetLyricNCT(addSong);
            if (lyrics != null) {
                Song temp = new Song(null, false, nameSong, singer);
                Server.listSongs.add(temp);
            }
            //Server.listSongs.get(Server.listSongs.size() - 1).ToString();
        }
    }

    public void GetDetailSongApi(String key, Song song) {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://shazam.p.rapidapi.com/songs/get-details")
                    .data("key", key)
                    .header("x-rapidapi-host", "shazam.p.rapidapi.com")
                    .header("x-rapidapi-key", "ce4b7a0cecmsh85ab0eed6ebe521p12543djsneb4fc4e4a4b7").ignoreContentType(true)
                    .get();
        } catch (IOException ex) {
            System.out.println("Error API Shazam!!!");
        }
        JsonObject json = (JsonObject) JsonParser.parseString(doc.body().text());
        JsonArray jsonArray = json.getAsJsonArray("sections");
        for (JsonElement jsonA : jsonArray) {
            JsonObject jb = jsonA.getAsJsonObject();
            switch (jb.get("type").getAsString()) {
                case "LYRICS":
                    song.setLyrics(jb.get("text").toString());
                    break;
                case "VIDEO":
                    song.setIDYoutube(jb.getAsJsonObject("youtubeurl").getAsJsonArray("actions").get(0).getAsJsonObject().get("uri").getAsString());
                    break;
            }
        }
    }

    public String GetLyricNCT(String addSong) {
        try {
            Document docLyric = Jsoup.connect(addSong).get();
            if (docLyric != null) {
                Element eleLyrics = docLyric.getElementById("divLyric");
                String lyrics = eleLyrics.html().replace("<br>", "\n");
                System.out.println("Get lyrics succes.");
                return lyrics;
            }
        } catch (IOException ex) {
            Logger.getLogger(Handle.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Can't find the lyrics.");
        return null;
    }

//    public String deAccent(String StrToLower) {
//        String nfdNormalizedString = Normalizer.normalize(StrToLower, Normalizer.Form.NFD);
//        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
//        String result = pattern.matcher(nfdNormalizedString).replaceAll("").replace("đ", "d");
//        return result.replaceAll("\\s\\s+", " ").trim();
//    }
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
            System.out.println("Read file Login succes.>>");
        } catch (FileNotFoundException ex) {
            System.out.println("Read file Login fails.>>");
        } catch (IOException ex) {
            System.out.println("Read file Login fails.>>");
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
}
