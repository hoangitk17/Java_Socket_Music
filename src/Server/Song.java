/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.Serializable;

/**
 *
 * @author Thuan Lam
 */
public class Song implements Serializable {

    private static final long serialVersionUID = -6500665823330706018L;
    private String key;
    private boolean hasKey;
    private String name;
    private String singer;
    private String lyrics;
    private String IDYoutube;
    private String mp3;
    private String Image;

    public Song() {
    }

    public Song(String key, boolean hasKey, String name, String singer, String Image) {
        this.key = key;
        this.hasKey = hasKey;
        this.name = name;
        this.singer = singer;
        this.Image = Image;
    }

    public Song(String key, boolean hasKey, String name, String singer, String lyrics, String IDYoutube) {
        this.key = key;
        this.hasKey = hasKey;
        this.name = name;
        this.singer = singer;
        this.lyrics = lyrics;
        this.IDYoutube = IDYoutube;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isHasKey() {
        return hasKey;
    }

    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
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

    public String getMp3() {
        return mp3;
    }

    public void setMp3(String mp3) {
        this.mp3 = mp3;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public void ToString() {
        System.out.println(this.key + " - " + this.name + " - " + this.singer + ". Image>>" + this.Image + " +  mp3" + this.mp3);
    }

    public void ToStringExactly() {
        System.out.println(this.name + " - " + this.singer + ". IDYou>>" + this.IDYoutube + ". Mp3>>" + this.mp3);
    }
}
