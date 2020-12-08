/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Thuan Lam
 */
public class Singer {

    private String name;
    private String info;
    private ArrayList<Album> listAlbums;
    private ArrayList<Integer> listIDSongs;
    private ArrayList<Integer> listIDMvs;

    public Singer() {
    }

    public Singer(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public ArrayList<Album> getListAlbums() {
        return listAlbums;
    }

    public void setListAlbums(ArrayList<Album> listAlbums) {
        this.listAlbums = listAlbums;
    }

    public ArrayList<Integer> getListIDSongs() {
        return listIDSongs;
    }

    public void setListIDSongs(ArrayList<Integer> listIDSongs) {
        this.listIDSongs = listIDSongs;
    }

    public ArrayList<Integer> getListIDMvs() {
        return listIDMvs;
    }

    public void setListIDMvs(ArrayList<Integer> listIDMvs) {
        this.listIDMvs = listIDMvs;
    }

//    public void ToString() {
//        System.out.println(name);
//        System.out.println(info);
//        System.out.println("---------Danh sách các album-------");
//        for (Album album : listAlbums) {
//            System.out.println(album.getNameAlbum() + " | " + album.getUrlAlbum());
//        }
//        System.out.println("---------Một số bài hát-----");
//        for (Integer i : listIDSongs) {
//            System.out.println(Server.listSongs.get(i).getName() + " | " + Server.listSongs.get(i).getSinger() + " | " + Server.listSongs.get(i).getUrlFile());
//        }
//        System.out.println("---------Một số mv-----");
//        for (Integer i : listIDMvs) {
//            System.out.println(Server.listSongs.get(i).getName() + " | " + Server.listSongs.get(i).getSinger() + " | " + Server.listSongs.get(i).getUrlFile());
//        }
//    }
    public static String inforSinger(String nameSinger) throws IOException {
        String add = "https://vi.wikipedia.org/w/api.php";
        Document docHTML = Jsoup.connect(add).data("action", "query")
                .data("format", "xml").data("prop", "extracts")
                .data("titles", nameSinger).get();
        String kq = docHTML.toString();
        String subString = "";
        int index_delete_first = 0;
        int index_delete_second = 0;
        String[] output;
        StringTokenizer token2;
        StringTokenizer token = new StringTokenizer(kq, ">");
        String result = "";
        String chuoins = "";
        String[] chuoi = kq.split(">");
        for (int i = 0; i < chuoi.length; i++) {
            if (chuoi[i].contains("&lt;/p&gt;&lt;p&gt;")) {
                output = chuoi[i].split("&lt;/p&gt;&lt;p&gt;");
                for (int j = 0; j < output.length; j++) {
                    output[j] = output[j].replaceAll("/li&gt;", "");
                    output[j] = output[j].replaceAll("li&gt;", "");
                    output[j] = output[j].replaceAll("/i&gt;", "");
                    output[j] = output[j].replaceAll("i&gt;", "");
                    output[j] = output[j].replaceAll("/p&gt;", "");
                    output[j] = output[j].replaceAll("p&gt;", "");
                    output[j] = output[j].replaceAll("&lt;", "");
                    output[j] = output[j].replaceAll("/b&gt;", "");
                    output[j] = output[j].replaceAll("b&gt;", "");
                    output[j] = output[j].replaceAll("/span&gt;", "");
                    output[j] = output[j].replaceAll("&gt;", "");
                    output[j] = output[j].replaceAll("/span", "");
                    output[j] = output[j].replaceAll("span", "");
                    output[j] = output[j].replaceAll("/li", "");
                    output[j] = output[j].replaceAll("/h2", "");
                    output[j] = output[j].replaceAll("h2", "");
                    output[j] = output[j].replaceAll("/h3", "");
                    output[j] = output[j].replaceAll("h3", "");
                    output[j] = output[j].replaceAll("/ul", "");
                    output[j] = output[j].replaceAll("ul", "");
                    output[j] = output[j].replaceAll("</extract", "");
                    output[j] = output[j].replaceAll("&amp;", "");
                    output[j] = output[j].replace("&nbsp;", "");
                    while (true) {
                        index_delete_first = output[j].indexOf("id=");
                        index_delete_second = output[j].indexOf('"', index_delete_first + 4);
                        if (output[j].indexOf("id=") == -1) {
                            break;
                        } else {
                            subString = output[j].substring(index_delete_first, index_delete_second + 1);
                        }
                        output[j] = output[j].replace(subString, "");
                    }
                    result += output[j];
                    //System.out.println(output[j]);
                }
                break;
            }
        }
        return result;
    }

    public static String dateBirth(String nameSinger) throws IOException {
        String add = "https://vi.wikipedia.org/w/api.php";
        Document docHTML = Jsoup.connect(add).data("action", "query")
                .data("format", "xml").data("prop", "extracts")
                .data("titles", nameSinger).get();
        String kq = docHTML.toString();
        String subString = "";
        int index_delete_first = 0;
        int index_delete_second = 0;
        String[] output;
        StringTokenizer token = new StringTokenizer(kq, ">");
        String chuoins = "";
        String[] chuoi = kq.split(">");
        for (int i = 0; i < chuoi.length; i++) {
            if (chuoi[i].contains("&lt;/p&gt;&lt;p&gt;")) {
                output = chuoi[i].split("&lt;/p&gt;&lt;p&gt;");
                chuoins = output[0];
            }
        }
        String dateBirth = "";
        index_delete_first = chuoins.indexOf("(");
        index_delete_second = chuoins.indexOf(')', index_delete_first);
        dateBirth = chuoins.substring(index_delete_first + 6, index_delete_second);
        dateBirth = dateBirth.replaceAll("ngày", "");
        return dateBirth;
    }

}
