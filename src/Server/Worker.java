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
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
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

    public Worker(Socket s, String name) throws IOException {
        this.socket = s;
        this.myName = name;
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
    }

    @Override
    public void run() {
        System.out.println("Client " + myName + " " + socket.toString() + " accepted");

        String input = "";
        while (true) {
            try {
                input = in.readLine();
            } catch (IOException ex) {
                System.out.println("Error read data.");
            }
            System.out.println("\nServer received: " + input + " from " + " #Client " + myName);

            if (input.equals("bye")) {
                Server.workers.remove(this);
                break;
            } else {
                String result = null;
                switch (input.substring(0, 5)) {
                    case "key:S":
                        Singer outputSinger = CreateSinger(input.substring(6));
                        result = outputSinger.getName() + "\n" + outputSinger.getInfo() + "\n" + outputSinger.getListAlbums().toString()
                                + "\n" + outputSinger.getListIDMvs().toString() + "\n" + outputSinger.getListIDSongs().toString();
                        System.out.println(result);
                        break;
                    case "key:M":
                        ArrayList outputArray = FindSong(input.substring(6));
                        result = outputArray.toString();
                        break;
                }
                try {
                    out.write(result);
                    out.newLine();
                    out.flush();
                } catch (IOException ex) {
                    System.out.println("Lỗi ghi vào stream");
                }

            }
        }
        System.out.println("Closed socket for Client " + myName);
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("Error closing connection.");
        }

    }

    public Singer CreateSinger(String nameSinger) {
        String info = "";
        String add = "https://vi.wikipedia.org/w/api.php";
        try {
            Document docHTML = Jsoup.connect(add)
                    .data("action", "query")
                    .data("format", "xml")
                    .data("prop", "extracts")
                    .data("titles", "Karik")
                    .get();
            Elements eleP = docHTML.getElementsByTag("extract").first().getElementsByTag("p");

            for (Element element : eleP) {
                info += element.text() + "\n";
            }

        } catch (IOException ex) {
            System.out.println("API connection error.");
        }
        Singer singer = new Singer(nameSinger, info);
        GetAlbumMvSongList(nameSinger, singer);
        singer.ToString();
        return singer;
    }

    //------------------------------------------------------------------------------------
    public void GetAlbumMvSongList(String nameSinger, Singer singer) {
        String add = "https://www.nhaccuatui.com/nghe-si-karik.html";
        Server.listSongs.clear();
        try {
            Document docHTML = Jsoup.connect(add).get();
            Elements fram_select = docHTML.getElementsByClass("fram_select");

            singer.setListAlbums(GetAlbum(fram_select.get(1)));
            singer.setListIDMvs(GetSingerMv(fram_select.get(2)));
            singer.setListIDSongs(GetSingerSong(fram_select.get(3)));

        } catch (IOException ex) {
            System.out.println("API get list song connection error.");
        }
    }

    public ArrayList GetAlbum(Element fram_select) {
        ArrayList<Album> listAlbums = new ArrayList<>();
        Elements albums = fram_select.getElementsByClass("info_album");
        for (Element album : albums) {
            String nameAlbum = album.getElementsByTag("h3").text();
            String urlAlbum = album.getElementsByTag("a").attr("href");
            Album tempAlbum = new Album(nameAlbum, urlAlbum);
            listAlbums.add(tempAlbum);
        }
        return listAlbums;
    }

    public ArrayList GetSingerMv(Element fram_select) {
        ArrayList<Integer> listMvs = new ArrayList<>();
        Handle handle = new Handle();
        Elements MVs = fram_select.getElementsByTag("li");
        for (Element mv : MVs) {
            String nameMv = mv.getElementsByTag("h3").text();
            String addMv = mv.getElementsByTag("a").attr("href");
            String singer = mv.getElementsByTag("p").text();
            Song tempMv = handle.GetMv(addMv, nameMv, singer);
            if (tempMv != null) {
                Server.listSongs.add(tempMv);
                listMvs.add(tempMv.getID());
            }
        }
        return listMvs;
    }

    public ArrayList GetSingerSong(Element fram_select) {
        ArrayList<Integer> listSongs = new ArrayList<>();
        Handle handle = new Handle();
        Elements songs = fram_select.getElementsByClass("info_song");
        for (Element song : songs) {
            String nameSong = song.getElementsByTag("h3").text();
            String addSong = song.getElementsByTag("a").attr("href");
            String singer = song.getElementsByTag("div").text();
            Song temp = handle.GetSong(addSong, nameSong, singer);
            if (temp != null) {
                Server.listSongs.add(temp);
                listSongs.add(temp.getID());
            }
        }
        return listSongs;
    }

    public void GetSongFormAlbum(String addAlbum) {
        Handle handle = new Handle();
        try {
            Document doc = Jsoup.connect(addAlbum).get();
            Element ListSongInAlbum = doc.getElementById("idScrllSongInAlbum");
            Elements listSong = ListSongInAlbum.getElementsByTag("li");
            for (Element song : listSong) {
                String addSong = song.getElementsByTag("a").attr("href");
                String title = song.getElementsByTag("div").attr("titleplay");
                String[] titles = title.split(" - ");
                Document docUSong = Jsoup.connect(addSong).get();
                int startkey = docUSong.body().html().indexOf("key1=") + 5;
                int startKeySong = docUSong.body().html().indexOf("key=\"") + 5;
                if (startkey != 4 && startKeySong != 4) {
                    String lyrics = handle.GetLyric(docUSong);
                    String key = docUSong.body().html().substring(startkey, startkey + 32);
                    String keySong = docUSong.body().html().substring(startKeySong, startKeySong + 12);
                    String urlFile = handle.GetUrlFile("key1", key, keySong);
                    Song temp = new Song(titles[0], titles[1], lyrics, urlFile);
                }
            }
        } catch (IOException ex) {
            System.out.println("Lỗi hàm GetSongFormAlbum");
        }

    }

    public ArrayList FindSong(String nameSearch) {
        ArrayList<Integer> result = new ArrayList<>();
        Server.listSongs.clear();
        String addSearch = "https://www.nhaccuatui.com/tim-kiem";
        try {
            Document docSearch = Jsoup.connect(addSearch)
                    .data("q", nameSearch.replace(" ", "+"))
                    .data("b", "title")
                    .data("l", "tat-ca")
                    .data("s", "default")
                    .get();
            Element frameSearch = docSearch.getElementsByClass("sn_search_returns_frame").first();

            //lấy danh sách mp3
            Elements songSearch = frameSearch.getElementsByClass("sn_search_single_song");
            FindListSong findListSong = new FindListSong(songSearch);
            findListSong.start();

            //Lấy danh sách mp4
            Elements mvSearch = frameSearch.getElementsByClass("sn_search_returns_list_video").first().getElementsByClass("box_info");
            FindListMv findListMv = new FindListMv(mvSearch);
            findListMv.start();

            while (true) {
                if (!findListSong.isAlive() && !findListMv.isAlive()) {
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println("API get list song connection error.");
        }

        for (Song listSong : Server.listSongs) {
            if (listSong.getName().toLowerCase().equals(nameSearch.toLowerCase())) {
                result.add(listSong.getID());
            }
        }
        return result;
    }

}

class FindListSong extends Thread {

    Elements songSearch;

    public FindListSong(Elements songSearch) {
        this.songSearch = songSearch;
    }

    @Override
    public void run() {
        Handle handle = new Handle();
        for (Element element : songSearch) {
            String addSong = element.getElementsByTag("a").attr("href");
            String nameSong = element.getElementsByTag("a").attr("title");
            String singer = element.getElementsByClass("singer_song").text();
            Song temp = handle.GetSong(addSong, nameSong, singer);
            Server.listSongs.add(temp);
        }
        System.out.println("end thread song");
    }
}

class FindListMv extends Thread {

    Elements mvSearch;

    public FindListMv(Elements mvSearch) {
        this.mvSearch = mvSearch;
    }

    @Override
    public void run() {
        Handle handle = new Handle();
        for (Element element : mvSearch) {
            String nameMV = element.getElementsByTag("h3").text();
            String addMv = element.getElementsByTag("a").attr("href");
            String singer = element.getElementsByTag("h4").text();
            Song tempMv = handle.GetMv(addMv, nameMV, singer);
            Server.listSongs.add(tempMv);
        }
        System.out.println("end thread mv");
    }
}
