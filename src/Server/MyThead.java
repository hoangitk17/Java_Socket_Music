/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author Thuan Lam
 */
public class MyThead {

}

//class GetSongFormFind extends Thread {
//
//    String addSong;
//    String nameSong;
//    String singer;
//
//    public GetSongFormFind(String addSong, String nameSong, String singer) {
//        this.addSong = addSong;
//        this.nameSong = nameSong;
//        this.singer = singer;
//    }
//
//    @Override
//    public void run() {
//        Handle handle = new Handle();
//        String lyrics = null, urlFile = null;
//        try {
//            Document docUSong = Jsoup.connect(addSong).get();
//            int startkey = docUSong.body().html().indexOf("key1=") + 5;
//            int startKeySong = docUSong.body().html().indexOf("key=\"") + 5;
//            if (startkey != 4 && startKeySong != 4) {
//                lyrics = handle.GetLyric(docUSong);
//                String key = docUSong.body().html().substring(startkey, startkey + 32);
//                String keySong = docUSong.body().html().substring(startKeySong, startKeySong + 12);
//                urlFile = handle.GetUrlFile("key1", key, keySong);
//            }
//        } catch (IOException ex) {
//            System.out.println("API get song connection error.");
//        }
//        Song result = new Song(nameSong, singer, lyrics, urlFile);
//        Server.listSongs.add(result);
//        System.out.println("end thread song " + result.getID());
//    }
//}
//
//class FindListMv extends Thread {
//
//    Elements mvSearch;
//
//    public FindListMv(Elements mvSearch) {
//        this.mvSearch = mvSearch;
//    }
//
//    @Override
//    public void run() {
//        Handle handle = new Handle();
//        for (Element element : mvSearch) {
//            String nameMV = element.getElementsByTag("h3").text();
//            String addMv = element.getElementsByTag("a").attr("href");
//            String singer = element.getElementsByTag("h4").text();
//            Song tempMv = handle.GetMv(addMv, nameMV, singer);
//            Server.listSongs.add(tempMv);
//        }
//        System.out.println("end thread mv");
//    }
//}
