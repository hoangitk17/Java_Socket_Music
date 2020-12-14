package mahoa;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class MaHoaAES {

    public MaHoaAES() {
    }

    private static String algorithm = "AES";
    private static byte[] keyValue = new byte[]{'5', '2', '3', '4', '5', '6', '7', '8', 'h', '1', '2', '3', '4', '5', '6', '8'};// your key

    public static String maHoaAES(String plainText, byte[] mahoa) throws Exception {
        Key key = new SecretKeySpec(mahoa, algorithm);
        Cipher chiper = Cipher.getInstance(algorithm);
        chiper.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = chiper.doFinal(plainText.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    public static String giaiMaAES(String encryptedText, byte[] mahoa) throws Exception {
        // generate key
        Key key = new SecretKeySpec(mahoa, algorithm);
        Cipher chiper = Cipher.getInstance(algorithm);

        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedText);
        chiper.init(Cipher.DECRYPT_MODE, key);
        byte[] decValue = chiper.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    public static void main(String[] args) throws Exception {

//        Song s = new Song("abc", true, "abc", "abc", "abc");
//        Gson gson = new Gson();
        String j = " https://kodlogs.c om/36063  /input-length-must -be-mu ltip e-of-16-when-dec ryptin g-with- padded-cipher gdg gfdg dfg dsg d gfd g re re trẻ t  d gfdfg dfg";
        String i = "0XtiN4eokDp/pJluQdLRIv3yy3s36//EKGw1rz1iSd06tRXMfiMV4h2lIPR5IE12rKnFgnGjMi0S" + "KndZAjrQD6kKpynsY76sj1YTZfFvg8i72niAuVhQ5qv8xxVtfhDi";
        String maHoa = MaHoaAES.maHoaAES(j, keyValue);

        //String giaiMa = MaHoaAES.giaiMaAES(i, keyValue);
        //System.out.println("Chuỗi nhập vào : " + chuoiBanDau);
        //System.out.println("Mã hoá : " + maHoa);
//        String result = "";
//        if (maHoa.length() > 76) {
//            int index = 0;
//            while (index + 76 < maHoa.length() - 1) {
//                result += maHoa.index += 76;
//            }
//        }
//        System.out.println("|" + result + "|");
//        System.out.println("Giải mã : " + maHoa + "|");
//        System.out.println("LcNIwaG7yeIVlUCCDXz/xQWG5f2xQEpAyPGWOONvmPzt6JiZdECOuKA9NL00VoTE5lsh02hWoo1a".length());
    }
}
