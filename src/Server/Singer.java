/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.ArrayList;

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

    public void ToString() {
        System.out.println(name);
        System.out.println(info);
        System.out.println("---------Danh sách các album-------");
        for (Album album : listAlbums) {
            System.out.println(album.getNameAlbum() + " | " + album.getUrlAlbum());
        }
        System.out.println("---------Một số bài hát-----");
        for (Integer i : listIDSongs) {
            System.out.println(Server.listSongs.get(i).getName() + " | " + Server.listSongs.get(i).getSinger() + " | " + Server.listSongs.get(i).getUrlFile());
        }
        System.out.println("---------Một số mv-----");
        for (Integer i : listIDMvs) {
            System.out.println(Server.listSongs.get(i).getName() + " | " + Server.listSongs.get(i).getSinger() + " | " + Server.listSongs.get(i).getUrlFile());
        }
    }

}
