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
public class Song {

    private int ID;
    private String name;
    private String singer;
    private String lyrics;
    private String urlFile;

    public Song() {
        this.ID = Server.listSongs.size();
    }

    public Song(String name, String singer, String lyrics, String urlFile) {
        this.ID = Server.listSongs.size();
        this.name = name;
        this.singer = singer;
        this.lyrics = lyrics;
        this.urlFile = urlFile;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getUrlFile() {
        return urlFile;
    }

    public void setUrlFile(String urlFile) {
        this.urlFile = urlFile;
    }

}
