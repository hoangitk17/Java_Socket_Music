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
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;
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
    ObjectOutputStream obOut;

    public Worker(Socket s, String name) throws IOException {
        this.socket = s;
        this.myName = name;
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        this.obOut = new ObjectOutputStream(s.getOutputStream());
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
                break;
            }
            System.out.println("Server received: " + input + " from " + " #Client " + myName);

            if (input.substring(6).equals("bye")) {
                Server.workers.remove(this);
                break;
            } else {
                switch (input.substring(0, 5)) {
                    case "key:S":
//                        Singer outputSinger = CreateSinger(input.substring(6));
//                        result = outputSinger.getName() + "\n" + outputSinger.getInfo() + "\n" + outputSinger.getListAlbums().toString()
//                                + "\n" + outputSinger.getListIDMvs().toString() + "\n" + outputSinger.getListIDSongs().toString();
//                        System.out.println(result);
                        break;
                    case "key:M":
                        int result = FindMusic(input.substring(6));
                        try {
                            out.write(result);
                            out.flush();
                            System.out.println("int");
                            switch (result) {
                                case -2:
                                    obOut.writeUTF("Error Server");
                                    System.out.println("string");
                                    break;
                                case -1:
                                    obOut.writeObject((ArrayList<Song>) Server.listSongs);
                                    System.out.println("Array");
                                    break;
                                default:
                                    Song ob = Server.listSongs.get(result);
                                    obOut.writeObject(ob);
                                    obOut.flush();
                                    System.out.println("Song");
                            }

                        } catch (IOException ex) {
                            System.out.println("Error write object.");
                        }
                        break;
                }
            }
        }
        System.out.println("Closed socket for Client " + myName);
        try {
            in.close();
            out.close();
            obOut.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("Error closing connection.");
        }

    }

    public int FindMusic(String keySearch) {
        StringTokenizer st = new StringTokenizer(keySearch.trim(), "-");
        String nameSearch = st.nextToken();
        String singerSearch = st.hasMoreTokens() ? st.nextToken() : null;
        Handle handle = new Handle();
        Server.listSongs.clear();
        String addSearch = "https://www.nhaccuatui.com/tim-kiem/bai-hat";
        try {
            Document docSearch = Jsoup.connect(addSearch)
                    .data("q", keySearch.replace(" ", "+"))
                    .data("b", "title")
                    .data("l", "tat-ca")
                    .data("s", "default")
                    .get();

            Element frameSearch = docSearch.getElementsByClass("sn_search_returns_frame").first();
            //lấy danh sách mp3
            Elements songSearch = frameSearch.getElementsByClass("sn_search_single_song");
            int result = singerSearch == null ? handle.FindSong(songSearch, nameSearch) : handle.FindSongAndSinger(songSearch, nameSearch, singerSearch);
            return result;
        } catch (IOException ex) {
            System.out.println("API get list song connection error.");
            return -2;
        }
    }

}
