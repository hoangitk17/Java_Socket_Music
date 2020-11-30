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

    private String name;
    private String singer;
    private String lyrics;
    private String IDYoutube;

    public Song() {
    }

    public Song(String name, String singer) {
        this.name = name;
        this.singer = singer;
    }

    public Song(String name, String singer, String lyrics, String IDYoutube) {
        this.name = name;
        this.singer = singer;
        this.lyrics = lyrics;
        this.IDYoutube = IDYoutube;
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

    public String getIDYoutube() {
        return IDYoutube;
    }

    public void setIDYoutube(String IDYoutube) {
        this.IDYoutube = IDYoutube;
    }

    public void ToString() {
        System.out.println(this.name + " - " + this.singer + ". Id>>" + this.IDYoutube);
    }
}
