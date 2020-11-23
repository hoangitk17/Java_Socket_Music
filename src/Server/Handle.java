/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author Thuan Lam
 */
public class Handle {

    public Handle() {
    }

    public Song GetSong(String addSong, String nameSong, String singer) {
        String lyrics = null, urlFile = null;
        try {
            Document docUSong = Jsoup.connect(addSong).get();
            int startkey = docUSong.body().html().indexOf("key1=") + 5;
            int startKeySong = docUSong.body().html().indexOf("key=\"") + 5;
            if (startkey != 4 && startKeySong != 4) {
                lyrics = GetLyric(docUSong);
                String key = docUSong.body().html().substring(startkey, startkey + 32);
                String keySong = docUSong.body().html().substring(startKeySong, startKeySong + 12);
                urlFile = GetUrlFile("key1", key, keySong);
            }
        } catch (IOException ex) {
            System.out.println("API get song connection error.");
            return null;
        }
        Song result = new Song(nameSong, singer, lyrics, urlFile);
        return result;
    }

    public Song GetMv(String addMv, String nameMv, String singer) {
        String urlFile = null;
        try {
            Document docUSong = Jsoup.connect(addMv).get();
            int startkey = docUSong.body().html().indexOf("key3=") + 5;
            int startKeyMV = docUSong.body().html().indexOf("key=\"") + 5;
            if (startkey != 4 && startKeyMV != 4 && docUSong.body().html().contains("html5=true")) {
                String key = docUSong.body().html().substring(startkey, startkey + 32);
                String keyMv = docUSong.body().html().substring(startKeyMV, startKeyMV + 13);
                urlFile = GetUrlFile("key3", key, keyMv);
            } else {
                int videoId = docUSong.body().html().indexOf("videoId: '") + 10;
                String ketVideoId = docUSong.body().html().substring(videoId, videoId + 11);
                urlFile = "https://www.youtube.com/watch?v=" + ketVideoId;
                System.out.println(urlFile);
            }

        } catch (IOException ex) {
            System.out.println("API get Mv connection error.");
            return null;
        }
        Song mv = new Song(nameMv, singer, null, urlFile);
        return mv;
    }

    public String GetUrlFile(String typeKey, String key, String keySong) {//mp4 typekey="key3", mp3 typekey="key1"
        try {
            Document docUrl = Jsoup.connect("https://www.nhaccuatui.com/flash/xml")
                    .data(typeKey, key)
                    .data("html5", "true")
                    .data("listKey", keySong)
                    .get();
            String Location = docUrl.getElementsByTag("location").first().html();
            String urlFile = Location.substring(Location.indexOf("https"), Location.length() - 3);
            System.out.println(urlFile);
            return urlFile;
        } catch (IOException ex) {
            System.out.println("API get link file connection error.");
        }
        return null;
    }

    public String GetLyric(Document docLyric) {
        if (docLyric != null) {
            Element lyric = docLyric.getElementById("divLyric");
            return lyric.html().replace("<br>", "\n");
        } else {
            System.out.println("Can't find the lyrics.");
        }
        return null;
    }
}
