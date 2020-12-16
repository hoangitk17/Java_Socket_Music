package Server;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class getFirstSong {

    public String GetIdYoutubeNCT(String nameMusic) {
        try {
            String result = "";
            try {
                Document doc = Jsoup.connect("https://www.youtube.com/results")
                        .data("search_query", nameMusic.replace(" ", "+"))
                        .get();
                String docST = doc.body().html();
                int start = docST.indexOf("videoId\":\"") + 10;
                result = docST.substring(start, start + 11);
            } catch (IOException ex) {
                System.out.println("Error get ID Youtube.");
            }
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }

    public String GetNameFromNCT(Element eleSong) {
        try {
            Element element = eleSong;
            String result = "";
            String addSong = element.getElementsByTag("a").attr("href");
            String nameSong = element.getElementsByTag("a").attr("title");
            String singer = element.getElementsByTag("h4").text();
            result = nameSong + " " + singer;
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }

    public String GetIDFromNCT(Element eleSong) {
        try {
            Element element = eleSong;
            String result = "";
            String addSong = element.getElementsByTag("a").attr("href");
            String nameSong = element.getElementsByTag("a").attr("title");
            String singer = element.getElementsByTag("h4").text();
            return addSong;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }

    public String GetUrlMP3First(String addSong) {
        try {
            Document docDetailSong = null;
            String key = "";
            String keySong = "";
            try {
                docDetailSong = Jsoup.connect(addSong).get();
            } catch (IOException ex) {
            }
            if (docDetailSong != null) {
                int startkey = docDetailSong.body().html().indexOf("key1=") + 5;
                int startKeySong = docDetailSong.body().html().indexOf("key: '") + 6;
                if (startkey != 4 && startKeySong != 4) {
                    key = docDetailSong.body().html().substring(startkey, startkey + 32);
                    keySong = docDetailSong.body().html().substring(startKeySong, startKeySong + 12);
                }
            }
            try {
                Document docUrl = Jsoup.connect("https://www.nhaccuatui.com/flash/xml")
                        .data("key1", key)
                        .data("html5", "true")
                        .data("listKey", keySong)
                        .get();
                String Location = docUrl.getElementsByTag("location").first().html();
                String urlFile = Location.substring(Location.indexOf("https"), Location.length() - 3);
                //System.out.println(urlFile);
                return urlFile;
            } catch (IOException ex) {
                System.out.println("API get link file connection error.");
                return null;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }

    public Element findMusicFirst(String keySearch) {

        try {
            Document docSearch = Jsoup.connect("https://www.nhaccuatui.com/tim-kiem/bai-hat")
                    .data("q", keySearch.replace(" ", "+"))
                    .data("b", "singer")
                    .data("l", "tat-ca")
                    .data("s", "default")
                    .get();
            Element frameSearch = docSearch.getElementsByClass("sn_search_returns_frame").first();
            if (frameSearch.hasText()) {
                Element eleSong = frameSearch.getElementsByClass("sn_search_single_song").first();
                return eleSong;
            } else {
                System.out.println("Không tìm thấy bài hát nào!!!");
            }
        } catch (IOException ex) {
            System.out.println("API get list song connection error.");
            return null;
        }
        return null;
    }

//    public static void main(String[] args) throws Exception {
//        getFirstSong ln = new getFirstSong();
//        Element elesong = ln.findMusicFirst("đan trường");
//        String nameSinger = ln.GetNameFromNCT(elesong);
//        String idmp3 = ln.GetIDFromNCT(elesong);
//        String idyt = ln.GetIdYoutubeNCT(nameSinger);
//        String urlmp3 = ln.GetUrlMP3First(idmp3);
//        System.out.println("name |||" + nameSinger);
//        System.out.println("url |||" + urlmp3);
//        System.out.println("idyt |||" + idyt);
//    }
}
