/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.io.Serializable;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author Thuan Lam
 */
public class Singer implements Serializable {

    private static final long serialVersionUID = -634324234706018L;
    private String name;
    private String dateBirth;
    private String listSong;
    private String listMV;
    private String suNghiep;
    private String tieuSu;
    private String mp3;
    private String youtube;

    public String getMp3() {
        return mp3;
    }

    public void setMp3(String mp3) {
        this.mp3 = mp3;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public Singer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getListSong() {
        return listSong;
    }

    public void setListSong(String listSong) {
        this.listSong = listSong;
    }

    public String getListMV() {
        return listMV;
    }

    public void setListMV(String listMV) {
        this.listMV = listMV;
    }

    public String getSuNghiep() {
        return suNghiep;
    }

    public void setSuNghiep(String suNghiep) {
        this.suNghiep = suNghiep;
    }

    public String getTieuSu() {
        return tieuSu;
    }

    public void setTieuSu(String tieuSu) {
        this.tieuSu = tieuSu;
    }

    public static String callAPI(String nameSinger) throws IOException {
        try {
            String kq = "";
            String add = "https://vi.wikipedia.org/w/api.php";
            Document docHTML = Jsoup.connect(add).data("action", "query")
                    .data("format", "xml").data("prop", "extracts")
                    .data("titles", nameSinger).get();
            kq = docHTML.toString();
            return kq;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }

    public static String tieuSu(String nameSinger) {
        try {
            String kq = nameSinger;
            String result = "";
            int index_delete_first = 0;
            int index_delete_second = 0;
            String[] output;
            String catchuoi = "";
            int ttbh = 0;
            String[] chuoi = kq.split(">");
            for (int i = 0; i < chuoi.length; i++) {

                while (true) {
                    index_delete_first = chuoi[i].indexOf("span id=\"Tiểu");
                    index_delete_second = chuoi[i].indexOf("span id=\"", index_delete_first + 4);
                    if (chuoi[i].indexOf("span id=\"Tiểu") == -1) {
                        break;
                    } else {
                        result = chuoi[i].substring(index_delete_first, index_delete_second);
                    }
                    chuoi[i] = chuoi[i].replaceAll(result, "");
                    break;
                }
            }
            if (result == "") {
                result = "";
                return result;
            }
            result = result.replaceAll("/li&gt;", "");
            result = result.replaceAll("li&gt;", "");
            result = result.replaceAll("/i&gt;", "");
            result = result.replaceAll("i&gt;", "");
            result = result.replaceAll("/p&gt;", "");
            result = result.replaceAll("p&gt;", "");
            result = result.replaceAll("&lt;", "");
            result = result.replaceAll("/b&gt;", "");
            result = result.replaceAll("b&gt;", "");
            result = result.replaceAll("/span&gt;", "");
            result = result.replaceAll("&gt;", "");
            result = result.replaceAll("/span", "");
            result = result.replaceAll("span", "");
            result = result.replaceAll("/li", "");
            result = result.replaceAll("/h2", "");
            result = result.replaceAll("h2", "");
            result = result.replaceAll("/h3", "");
            result = result.replaceAll("h3", "");
            result = result.replaceAll("/h4", "");
            result = result.replaceAll("h4", "");
            result = result.replaceAll("/ul", "");
            result = result.replaceAll("ul", "");
            result = result.replaceAll("</extract", "");
            result = result.replaceAll("&amp;", "");
            index_delete_first = result.indexOf("id=");
            index_delete_second = result.indexOf('"', index_delete_first + 4);
            catchuoi = result.substring(index_delete_first, index_delete_second + 1);
            result = result.replaceAll(catchuoi, "");
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }

    public static String sunghiep(String nameSinger) {
        try {
            String kq = nameSinger;
            String result = "";
            int index_delete_first = 0;
            int index_delete_second = 0;
            int index_delete_sn = 0;
            int index_delete_sne = 0;
            String[] output;
            String catchuoi = "";
            int ttbh = 0;
            String[] chuoi = kq.split(">");
            for (int i = 0; i < chuoi.length; i++) {

                while (true) {
                    index_delete_sn = chuoi[i].indexOf("span id=\"Sự");
                    //System.out.println("dau " + index_delete_sn);
                    if (index_delete_sn < 0) {
                        index_delete_sn = chuoi[i].indexOf("span id=\"Cuộc");
                        //System.out.println("dau " + index_delete_sn);
                    }
                    if (index_delete_sn < 0) {
                        index_delete_sn = chuoi[i].indexOf("span id=\"Hoạt");
                        //System.out.println("dau " + index_delete_sn);
                    }
                    if (index_delete_sn < 0) {
                        index_delete_sn = chuoi[i].indexOf("span id=\"Thành");
                        //System.out.println("dau " + index_delete_sn);
                    }
                    index_delete_sne = chuoi[i].indexOf("span id=\"", index_delete_sn + 300);
                    //System.out.println("cuoi" + index_delete_sne);
                    if (index_delete_sn < 0) {
                        break;
                    } else if (index_delete_sn > 0) {
                        result = chuoi[i].substring(index_delete_sn, index_delete_sne);
                        //System.out.println(result);
                    }
                    break;
                }
            }
            if (result == "") {
                result = "";
                return result;
            }
            result = result.replaceAll("/li&gt;", "");
            result = result.replaceAll("li&gt;", "");
            result = result.replaceAll("/i&gt;", "");
            result = result.replaceAll("i&gt;", "");
            result = result.replaceAll("/p&gt;", "");
            result = result.replaceAll("p&gt;", "");
            result = result.replaceAll("&lt;", "");
            result = result.replaceAll("/b&gt;", "");
            result = result.replaceAll("b&gt;", "");
            result = result.replaceAll("/span&gt;", "");
            result = result.replaceAll("&gt;", "");
            result = result.replaceAll("/span", "");
            result = result.replaceAll("span", "");
            result = result.replaceAll("/li", "");
            result = result.replaceAll("/h2", "");
            result = result.replaceAll("h2", "");
            result = result.replaceAll("/h3", "");
            result = result.replaceAll("h3", "");
            result = result.replaceAll("/h4", "");
            result = result.replaceAll("h4", "");
            result = result.replaceAll("/ul", "");
            result = result.replaceAll("ul", "");
            result = result.replaceAll("</extract", "");
            result = result.replaceAll("&amp;", "");
            while (true) {
                index_delete_first = result.indexOf("id=");
                index_delete_second = result.indexOf('"', index_delete_first + 4);
                if (result.indexOf("id=") == -1) {
                    break;
                } else if (index_delete_first > 0 && index_delete_second > 0) {

                    catchuoi = result.substring(index_delete_first, index_delete_second + 1);
                }
                result = result.replace(catchuoi, "");
            }
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }

    public static String inforSinger(String nameSinger) {
        try {
            String kq = nameSinger;
            String subString = "";
            int index_delete_first = 0;
            int index_delete_second = 0;
            int index_delete_tt = 0;
            String[] output;
            String result = "";
            String[] chuoi = kq.split(">");
            for (int i = 0; i < chuoi.length; i++) {
                if (chuoi[i].contains("&lt;/p&gt;&lt;p&gt;")) {
                    output = chuoi[i].split("&lt;/p&gt;&lt;p&gt;");
                    for (int j = 0; j < output.length; j++) {
                        index_delete_tt = output[j].indexOf("span id=\"Sự");
                        System.out.println(index_delete_tt);
                        if (index_delete_tt < 0) {
                            index_delete_tt = output[j].indexOf("span id=", 1000);
                        }
                        if (index_delete_tt > 0) {
                            output[j] = output[j].substring(0, index_delete_tt);
                        }
                        output[j] = output[j].replaceAll("/li&gt;", " ");
                        output[j] = output[j].replaceAll("li&gt;", " ");
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
                        output[j] = output[j].replaceAll("/li", " ");
                        output[j] = output[j].replaceAll("/h2", "");
                        output[j] = output[j].replaceAll("h2", "");
                        output[j] = output[j].replaceAll("/h3", "");
                        output[j] = output[j].replaceAll("h3", "");
                        output[j] = output[j].replaceAll("/h4", "");
                        output[j] = output[j].replaceAll("h4", "");
                        output[j] = output[j].replaceAll("/ul", "");
                        output[j] = output[j].replaceAll("ul", "");
                        output[j] = output[j].replaceAll("</extract", "");
                        output[j] = output[j].replaceAll("&amp;", "");
                        output[j] = output[j].replace("&nbsp;", " ");

                        while (true) {
                            index_delete_first = output[j].indexOf("id=");
                            index_delete_second = output[j].indexOf('"', index_delete_first + 4);
                            if (output[j].indexOf("id=") == -1) {
                                break;
                            } else if (index_delete_first > 0 && index_delete_second > 0) {

                                subString = output[j].substring(index_delete_first, index_delete_second + 1);
                            }
                            output[j] = output[j].replace(subString, "");
                        }

                        result += output[j];
                        if (index_delete_tt > 0) {
                            break;
                        }
                        //System.out.println(output[j]);
                    }
                    break;
                }
            }
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }

    public static String nameSinger(String nameSinger) {
        try {
            String kq = nameSinger;
            int index_delete_name = 0;
            String name = "";
            String[] output;
            String result = "";
            String[] chuoi = kq.split(">");
            for (int i = 0; i < chuoi.length; i++) {
                if (chuoi[i].contains("&lt;/p&gt;&lt;p&gt;")) {
                    output = chuoi[i].split("&lt;/p&gt;&lt;p&gt;");
                    result = output[0];
                    result = result.replaceAll("/p&gt;", "");
                    result = result.replaceAll("p&gt;", "");
                    result = result.replaceAll("&lt;", "");
                    result = result.replaceAll("/b&gt;", "");
                    result = result.replaceAll("b&gt;", "");
                    result = result.replaceAll("&gt;", "");

                }
            }
            index_delete_name = result.indexOf("(");
            if (index_delete_name > 0) {
                name = result.substring(0, index_delete_name);
            }
            name = name.replaceAll("p class=\"mw-empty-elt\"", "");
            return name;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }

    public static String listSong(String nameSinger) {
        try {
            String kq = nameSinger;
            String result = "";
            String song = "";
            String lttbh = "";
            int index_delete_first = 0;
            int index_delete_second = 0;
            int space = 0;
            String[] output;
            String catchuoi = "";
            int ttbh = 0;
            String[] chuoi = kq.split(">");
            for (int i = 0; i < chuoi.length; i++) {

                while (true) {
                    index_delete_first = chuoi[i].indexOf("span id=\"Đĩa");
                    if (index_delete_first < 0) {
                        index_delete_first = chuoi[i].indexOf("span id=\"Album");
                        //System.out.println(index_delete_first);
                    }
                    index_delete_second = chuoi[i].indexOf("span id=\"", index_delete_first + 100);
                    //System.out.println(index_delete_second);
                    if (index_delete_first < 0) {
                        break;
                    } else {
                        result = chuoi[i].substring(index_delete_first, index_delete_second);
                        //System.out.println(result);
                    }
                    chuoi[i] = chuoi[i].replaceAll(result, "");
                    break;
                }
            }
            if (result == "") {
                result = "";
                return result;
            }
            result = result.replaceAll("/li&gt;", "");
            result = result.replaceAll("li&gt;", "");
            result = result.replaceAll("/i&gt;", "");
            result = result.replaceAll("i&gt;", "");
            result = result.replaceAll("/p&gt;", "");
            result = result.replaceAll("p&gt;", "");
            result = result.replaceAll("&lt;", "");
            result = result.replaceAll("/b&gt;", "");
            result = result.replaceAll("b&gt;", "");
            result = result.replaceAll("/span&gt;", "");
            result = result.replaceAll("&gt;", "");
            result = result.replaceAll("/span", "");
            result = result.replaceAll("span", "");
            result = result.replaceAll("/li", "");
            result = result.replaceAll("/h2", "");
            result = result.replaceAll("h2", "");
            result = result.replaceAll("/h3", "");
            result = result.replaceAll("h3", "");
            result = result.replaceAll("/h4", "");
            result = result.replaceAll("h4", "");
            result = result.replaceAll("/ul", "");
            result = result.replaceAll("ul", "");
            result = result.replaceAll("</extract", "");
            result = result.replaceAll("&amp;", "");

            //System.out.println("in lan 2||||" + result);
            while (true) {
                index_delete_first = result.indexOf("id=");
                index_delete_second = result.indexOf('"', index_delete_first + 4);
                if (result.indexOf("id=") == -1) {
                    break;
                } else if (index_delete_first > 0 && index_delete_second > 0) {

                    catchuoi = result.substring(index_delete_first, index_delete_second + 1);
                }
                result = result.replace(catchuoi, "");
            }
            while (true) {
                space = result.indexOf(")", song.length() + 1);
                if (space == -1) {
                    break;
                } else {
                    lttbh = result.substring(song.length(), space + 1);
                    song += lttbh + "\n";
                    space = -1;
                }

            }
            song = song.replaceAll("Đĩa đơn hát chính", "");
            song = song.replaceAll("Đĩa đơn", "");
            song = song.substring(2, song.length());
            return song;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static String listMV(String nameSinger) {
        try {
            String kq = nameSinger;
            String result = "";
            int space = 0;
            String listMV = "";
            String lttbh = "";
            int index_delete_first = 0;
            int index_delete_second = 0;
            String[] output;
            String[] chuoibh;
            String catchuoi = "";
            String[] chuoi = kq.split(">");
            for (int i = 0; i < chuoi.length; i++) {

                while (true) {
                    index_delete_first = chuoi[i].indexOf("span id=\"MV");
                    index_delete_second = chuoi[i].indexOf("span id=\"", index_delete_first + 200);
                    if (chuoi[i].indexOf("span id=\"MV") == -1) {
                        break;
                    } else {
                        result = chuoi[i].substring(index_delete_first, index_delete_second);
                    }
                    break;
                }
            }

            result = result.replaceAll("/li&gt;", "");
            result = result.replaceAll("li&gt;", "");
            result = result.replaceAll("/i&gt;", "");
            result = result.replaceAll("i&gt;", "");
            result = result.replaceAll("/p&gt;", "");
            result = result.replaceAll("p&gt;", "");
            result = result.replaceAll("&lt;", "");
            result = result.replaceAll("/b&gt;", "");
            result = result.replaceAll("b&gt;", "");
            result = result.replaceAll("/span&gt;", "");
            result = result.replaceAll("&gt;", "");
            result = result.replaceAll("/span", "");
            result = result.replaceAll("span", "");
            result = result.replaceAll("/li", "");
            result = result.replaceAll("/h2", "");
            result = result.replaceAll("h2", "");
            result = result.replaceAll("/h3", "");
            result = result.replaceAll("h3", "");
            result = result.replaceAll("/h4", "");
            result = result.replaceAll("h4", "");
            result = result.replaceAll("/ul", "");
            result = result.replaceAll("ul", "");
            result = result.replaceAll("</extract", "");
            result = result.replaceAll("&amp;", "");
            while (true) {
                index_delete_first = result.indexOf("id=");
                index_delete_second = result.indexOf('"', index_delete_first + 4);
                if (result.indexOf("id=") == -1) {
                    break;
                } else if (index_delete_first > 0 && index_delete_second > 0) {

                    catchuoi = result.substring(index_delete_first, index_delete_second + 1);
                }
                result = result.replace(catchuoi, "");
            }
            while (true) {
                space = result.indexOf(")", listMV.length() + 1);
                if (space == -1) {
                    break;
                } else {
                    lttbh = result.substring(listMV.length(), space + 1);
                    listMV += lttbh + "\n";
                    space = -1;
                }

            }
            if (listMV.length() > 1) {
                listMV = listMV.substring(1, listMV.length());
            } else {
                listMV = "Không có MV";
            }
            return listMV;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static String dateBirth(String nameSinger) throws IOException {
        try {
            String kq = nameSinger;
            String name = "";
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
            if (index_delete_first > 0) {
                dateBirth = chuoins.substring(index_delete_first + 6, index_delete_second);
            }
//
            int start = dateBirth.indexOf("ngày") + 1;
            if (start > 0) {
                int end = dateBirth.indexOf("năm", start);
                dateBirth = dateBirth.substring(start, end + 8);
                dateBirth = dateBirth.replaceAll("gày", "");
                return dateBirth;
            } else {
                start = 0;

                int end = dateBirth.indexOf("năm", start);
                dateBirth = dateBirth.substring(start, end + 8);
                return dateBirth;

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }

    public Singer(String search) {
        try {
            //Pattern pattern = Pattern.compile("<extract xml:space=\"preserve\">" );
//        try {
//            String nameSinger = "";
//            String add = "https://vi.wikipedia.org/w/index.php";
//            org.jsoup.nodes.Document docHTML = Jsoup.connect(add)
//                    .data("search", search)
//                    .data("title", "%C4%90%E1%BA%B7c_bi%E1%BB%87t%3AT%C3%ACm_ki%E1%BA%BFm")
//                    .data("go", "Xem")
//                    .data("ns0", "1").ignoreContentType(true)
//                    .get();
//
//            String st = docHTML.toString();
//
//            int start = st.indexOf("<link rel=\"canonical\" href=\"https://vi.wikipedia.org/wiki/");
//            if (start != -1) {
//                start += "<link rel=\"canonical\" href=\"https://vi.wikipedia.org/wiki/".length();
//                int end = st.indexOf("\">", start);
//                nameSinger = st.substring(start, end);
//                System.out.println("name search>>" + nameSinger);
//            }
//            if (nameSinger.contains("%")) {
//                String temp = "";
//                search = search.replaceAll("\\s\\s+", " ").trim();
//                if (search.indexOf(" ") == -1) {
//                    temp = search.substring(0, 1).toUpperCase() + search.substring(1);
//                } else {
//                    String[] arr = search.split(" ");
//                    for (String x : arr) {
//                        temp += (x.substring(0, 1).toUpperCase() + x.substring(1)) + " ";
//                    }
//                }
//                temp = temp.trim().replace(" ", "_");
//                if (nameSinger.contains("_(%C4%91%E1%BB%8Bnh_h%C6%B0%E1%BB%9Bng)")) {
//                    nameSinger = temp + "_(ca_sĩ_Việt_Nam)";
//                } else if (nameSinger.contains("_(ca_s%C4%A9)")) {
//                    nameSinger = temp + "_(ca_sĩ)";
//                } else {
//                    nameSinger = temp;
//                }
//            }
//            System.out.println(nameSinger);

            String ttcs = callAPI(search);
            this.dateBirth = dateBirth(ttcs);
            this.tieuSu = tieuSu(ttcs);
            if (this.tieuSu.equals("")) {
                this.tieuSu = inforSinger(ttcs);
            }
            this.name = nameSinger(ttcs);
            this.listMV = listMV(ttcs);
            this.listSong = listSong(ttcs);
            this.suNghiep = sunghiep(ttcs);
            String nameSearch = search.toLowerCase();
            getFirstSong gf = new getFirstSong();
            Element elesong = gf.findMusicFirst(nameSearch);
            String name = gf.GetNameFromNCT(elesong);
            String idmp3 = gf.GetIDFromNCT(elesong);
            this.mp3 = gf.GetUrlMP3First(idmp3);
            this.youtube = gf.GetIdYoutubeNCT(name);
        } catch (IOException ex) {
            Logger.getLogger(Singer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Singer(String name, String dateBirth, String listMV, String listSong, String tieuSu, String suNghiep, String mp3, String youtube) {
        this.name = name;
        this.dateBirth = dateBirth;
        this.listMV = listMV;
        this.listSong = listSong;
        this.tieuSu = tieuSu;
        this.suNghiep = suNghiep;
        this.mp3 = mp3;
        this.youtube = youtube;
    }
}
