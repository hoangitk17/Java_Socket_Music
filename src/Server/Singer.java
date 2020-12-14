/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.io.Serializable;
import java.util.StringTokenizer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

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
        String kq = "";
        String add = "https://vi.wikipedia.org/w/api.php";
        Document docHTML = Jsoup.connect(add).data("action", "query")
                .data("format", "xml").data("prop", "extracts")
                .data("titles", nameSinger).get();
        kq = docHTML.toString();
        return kq;
    }

    public static String tieuSu(String nameSinger) {
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
    }

    public static String sunghiep(String nameSinger) {
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
                index_delete_sne = chuoi[i].indexOf("span id=\"", index_delete_sn + 300);
                //System.out.println("cuoi" + index_delete_sne);
                if (index_delete_sn == -1) {
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
    }

    public static String inforSinger(String nameSinger) {
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
    }

    public static String nameSinger(String nameSinger) {
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
                result = result.replaceAll("p&gt;", "");
                result = result.replaceAll("&lt;", "");
                result = result.replaceAll("/b&gt;", "");
                result = result.replaceAll("b&gt;", "");

            }
        }
        index_delete_name = result.indexOf("(");
        if (index_delete_name > 0) {
            name = result.substring(0, index_delete_name);
        }
        return name;
    }

    public static String listSong(String nameSinger) {
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
                index_delete_first = chuoi[i].indexOf("span id=\"Đĩa");
                index_delete_second = chuoi[i].indexOf("span id=\"", index_delete_first + 4);
                if (chuoi[i].indexOf("span id=\"Đĩa") == -1) {
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
    }

    public static String listMV(String nameSinger) {
        String kq = nameSinger;
        String listMV = "";
        int index_delete_first = 0;
        int index_delete_second = 0;
        String[] output;
        String[] chuoibh;
        String catchuoi = "";
        String[] chuoi = kq.split(">");
        for (int i = 0; i < chuoi.length; i++) {

            while (true) {
                index_delete_first = chuoi[i].indexOf("span id=\"MV");
                index_delete_second = chuoi[i].indexOf("span id=\"", index_delete_first + 4);
                if (chuoi[i].indexOf("span id=\"MV") == -1) {
                    break;
                } else {
                    listMV = chuoi[i].substring(index_delete_first, index_delete_second);
                }
                break;
            }
        }
        if (listMV == "") {
            listMV = "Không có MV";
            return listMV;
        }
        listMV = listMV.replaceAll("/li&gt;", "");
        listMV = listMV.replaceAll("li&gt;", "");
        listMV = listMV.replaceAll("/i&gt;", "");
        listMV = listMV.replaceAll("i&gt;", "");
        listMV = listMV.replaceAll("/p&gt;", "");
        listMV = listMV.replaceAll("p&gt;", "");
        listMV = listMV.replaceAll("&lt;", "");
        listMV = listMV.replaceAll("/b&gt;", "");
        listMV = listMV.replaceAll("b&gt;", "");
        listMV = listMV.replaceAll("/span&gt;", "");
        listMV = listMV.replaceAll("&gt;", "");
        listMV = listMV.replaceAll("/span", "");
        listMV = listMV.replaceAll("span", "");
        listMV = listMV.replaceAll("/li", "");
        listMV = listMV.replaceAll("/h2", "");
        listMV = listMV.replaceAll("h2", "");
        listMV = listMV.replaceAll("/h3", "");
        listMV = listMV.replaceAll("h3", "");
        listMV = listMV.replaceAll("/h4", "");
        listMV = listMV.replaceAll("h4", "");
        listMV = listMV.replaceAll("/ul", "");
        listMV = listMV.replaceAll("ul", "");
        listMV = listMV.replaceAll("</extract", "");
        listMV = listMV.replaceAll("&amp;", "");
        index_delete_first = listMV.indexOf("id=");
        index_delete_second = listMV.indexOf('"', index_delete_first + 4);
        if (index_delete_first > 0) {
            catchuoi = listMV.substring(index_delete_first - 1, index_delete_second - 3);
        }

        listMV = listMV.replaceAll(catchuoi, "");
        listMV = listMV.replaceAll("\"MV\"", "");
        return listMV;
    }

    public static String dateBirth(String nameSinger) throws IOException {
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
        int start = dateBirth.indexOf("ngày") + 1;
        if (start > 0) {
            int end = dateBirth.indexOf("năm", start);
            dateBirth = dateBirth.substring(start, end + 8);
            dateBirth = dateBirth.replaceAll("gày", "");
            return dateBirth;
        } else {
            return "rỗng";
        }
    }

    public Singer(String search) throws IOException {
        //Pattern pattern = Pattern.compile("<extract xml:space=\"preserve\">" );
        String nameSinger = "";
        String add = "https://vi.wikipedia.org/w/index.php";
        org.jsoup.nodes.Document docHTML = Jsoup.connect(add)
                .data("search", search)
                .data("title", "%C4%90%E1%BA%B7c_bi%E1%BB%87t%3AT%C3%ACm_ki%E1%BA%BFm")
                .data("go", "Xem")
                .data("ns0", "1").ignoreContentType(true)
                .get();
        String st = docHTML.toString();

        int start = st.indexOf("<link rel=\"canonical\" href=\"https://vi.wikipedia.org/wiki/");
        if (start != -1) {
            start += "<link rel=\"canonical\" href=\"https://vi.wikipedia.org/wiki/".length();
            int end = st.indexOf("\">", start);
            nameSinger = st.substring(start, end);
            System.out.println("name search>>" + nameSinger);
        }
        if (nameSinger.contains("%")) {
            String temp = "";
            search = search.replaceAll("\\s\\s+", " ").trim();
            if (search.indexOf(" ") == -1) {
                temp = search.substring(0, 1).toUpperCase() + search.substring(1);
            } else {
                String[] arr = search.split(" ");
                for (String x : arr) {
                    temp += (x.substring(0, 1).toUpperCase() + x.substring(1)) + " ";
                }
            }
            temp = temp.trim().replace(" ", "_");
            if (nameSinger.contains("_(%C4%91%E1%BB%8Bnh_h%C6%B0%E1%BB%9Bng)")) {
                nameSinger = temp + "_(ca_sĩ_Việt_Nam)";
            } else {
                nameSinger = temp;
            }
        }
        System.out.println(nameSinger);

        String ttcs = callAPI(nameSinger);
        this.dateBirth = dateBirth(ttcs);
        this.tieuSu = tieuSu(ttcs);
        if (this.tieuSu.equals("")) {
            this.tieuSu = inforSinger(ttcs);
        }
        this.name = nameSinger(ttcs);
        this.listMV = listMV(ttcs);
        this.listSong = listSong(ttcs);
        this.suNghiep = sunghiep(ttcs);
    }

    public Singer(String name, String dateBirth, String listMV, String listSong, String tieuSu, String suNghiep) {
        this.name = name;
        this.dateBirth = dateBirth;
        this.listMV = listMV;
        this.listSong = listSong;
        this.tieuSu = tieuSu;
        this.suNghiep = suNghiep;
    }

}
