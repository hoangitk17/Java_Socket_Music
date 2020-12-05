/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Thuan Lam
 */
public class MyThread {

}

class GetSongFormApiShazam extends Thread {

    private final String nameSearch;

    public GetSongFormApiShazam(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    @Override
    public void run() {
        ArrayList<Song> array = new ArrayList<>();
        Document doc = null;

        try {
            doc = Jsoup.connect("https://shazam.p.rapidapi.com/search")
                    .data("term", nameSearch)
                    .data("offset", "0")
                    .data("limit", "10")
                    .header("x-rapidapi-host", "shazam.p.rapidapi.com")
                    .header("x-rapidapi-key", "ce4b7a0cecmsh85ab0eed6ebe521p12543djsneb4fc4e4a4b7").ignoreContentType(true)
                    .get();
        } catch (IOException ex) {
            System.out.println("Error connection API Shazam!!!");
        }
        JsonObject json = (JsonObject) JsonParser.parseString(doc.body().text());
        if (json.toString().equals("{}")) {
            System.out.println("API Shazam empty.");
            return;
        }

        JsonArray jsonArray = json.getAsJsonObject("tracks").getAsJsonArray("hits");

        for (JsonElement jsonA : jsonArray) {
            JsonObject jb = jsonA.getAsJsonObject().getAsJsonObject("track");
            Song temp = new Song(jb.get("key").getAsString(), true, jb.get("title").getAsString(), jb.get("subtitle").getAsString());
            array.add(temp);
        }
        boolean isFirst = true;
        Handle handle = new Handle();
        for (Song s : array) {
            if (handle.checkName(s.getName(), nameSearch)) {
                if (isFirst) {
                    handle.GetDetailSongApi(s.getKey(), s);
                    isFirst = false;
                }
                Server.listSongs.add(s);
            }
        }
        System.out.println("Get API End<<");
    }
}
