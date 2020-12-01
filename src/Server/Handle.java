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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Thuan Lam
 */
public class Handle {

    public Handle() {
    }

//    public String GetKeySearch() throws IOException {
//        Document doc = Jsoup.connect("https://coccoc.com/search").data("query", "Đan%20Trường").get();
//        String docC = doc.text();
//        System.out.println(docC);
//        return null;
//    }
    public int FindSong(Elements songSearch, String nameSearch) {
        Handle handle = new Handle();
        for (Element element : songSearch) {
            String addSong = element.getElementsByTag("a").attr("href");
            String nameSong = element.getElementsByTag("a").attr("title");
            String singer = element.getElementsByTag("h4").text();
            Song temp = new Song(nameSong, singer);
            if (nameSong.toLowerCase().equals(nameSearch.toLowerCase())) {
                try {
                    Document docUSong = Jsoup.connect(addSong).get();
                    temp.setLyrics(GetLyric(docUSong));
                } catch (IOException ex) {
                    System.out.println("Error get Lyrics.");
                    return -2;
                }
                temp.setIDYoutube(GetIdYoutube(nameSong + " " + singer));
                Server.listSongs.add(temp);
                return Server.listSongs.size() - 1;
            }
            Server.listSongs.add(temp);
        }
        return -1;
    }

    public int FindSongAndSinger(Elements songSearch, String nameSearch, String singerSearch) {
        Handle handle = new Handle();
        for (Element element : songSearch) {
            String addSong = element.getElementsByTag("a").attr("href");
            String nameSong = element.getElementsByTag("a").attr("title");
            String singer = element.getElementsByTag("h4").text();

            Song temp = new Song(nameSong, singer, null, addSong);
            if (nameSong.toLowerCase().equals(nameSearch.toLowerCase()) && singer.toLowerCase().contains(singerSearch.toLowerCase())) {
                try {
                    Document docUSong = Jsoup.connect(addSong).get();
                    temp.setLyrics(GetLyric(docUSong));
                } catch (IOException ex) {
                    System.out.println("Error get Lyrics.");
                    return -2;
                }
                temp.setIDYoutube(GetIdYoutube(nameSong + " " + singer));
                Server.listSongs.add(temp);
                return Server.listSongs.size() - 1;
            }
            Server.listSongs.add(temp);
        }
        return -1;
    }

//    public Song GetMv(String addMv, String nameMv, String singer) {
//        String urlFile = null;
//        try {
//            Document docUSong = Jsoup.connect(addMv).get();
//            int startkey = docUSong.body().html().indexOf("key3=") + 5;
//            int startKeyMV = docUSong.body().html().indexOf("key=\"") + 5;
//            if (startkey != 4 && startKeyMV != 4 && docUSong.body().html().contains("html5=true")) {
//                String key = docUSong.body().html().substring(startkey, startkey + 32);
//                String keyMv = docUSong.body().html().substring(startKeyMV, startKeyMV + 13);
//                urlFile = GetUrlFile("key3", key, keyMv);
//            } else {
//                int videoId = docUSong.body().html().indexOf("videoId: '") + 10;
//                String ketVideoId = docUSong.body().html().substring(videoId, videoId + 11);
//                urlFile = "https://www.youtube.com/watch?v=" + ketVideoId;
//                System.out.println(urlFile);
//            }
//
//        } catch (IOException ex) {
//            System.out.println("API get Mv connection error.");
//            return null;
//        }
//        Song mv = new Song(nameMv, singer, null, urlFile);
//        return mv;
//    }
//    public String GetUrlFile(String typeKey, String key, String keySong) {//mp4 typekey="key3", mp3 typekey="key1"
//        try {
//            Document docUrl = Jsoup.connect("https://www.nhaccuatui.com/flash/xml")
//                    .data(typeKey, key)
//                    .data("html5", "true")
//                    .data("listKey", keySong)
//                    .get();
//            String Location = docUrl.getElementsByTag("location").first().html();
//            String urlFile = Location.substring(Location.indexOf("https"), Location.length() - 3);
//            System.out.println(urlFile);
//            return urlFile;
//        } catch (IOException ex) {
//            System.out.println("API get link file connection error.");
//        }
//        return null;
//    }
    public String GetLyric(Document docLyric) {
        if (docLyric != null) {
            Element lyric = docLyric.getElementById("divLyric");
            return lyric.html().replace("<br>", "\n");
        } else {
            System.out.println("Can't find the lyrics.");
        }
        return null;
    }

//    public String deAccent(String StrToLower) {
//        String nfdNormalizedString = Normalizer.normalize(StrToLower, Normalizer.Form.NFD);
//        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
//        String result = pattern.matcher(nfdNormalizedString).replaceAll("").replace("đ", "d");
//        return result.replaceAll("\\s\\s+", " ").trim();
//    }
    public String GetIdYoutube(String nameMusic) {
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
                System.out.println(text);
                Server.listUsers.add(text);
            }
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
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
