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
public class Album {

    private String nameAlbum;
    private String urlAlbum;

    public Album(String nameAlbum, String urlAlbum) {
        this.nameAlbum = nameAlbum;
        this.urlAlbum = urlAlbum;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public String getUrlAlbum() {
        return urlAlbum;
    }

    public void setUrlAlbum(String urlAlbum) {
        this.urlAlbum = urlAlbum;
    }

}
