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
//        String j = "[\"One Direction\",\"Dan + Shay\",\"an Trn\",\"Dan Gibson";
//        String i = "1uUs+PwL3o3Mtd7b7l4eHDOOYG3LhVbSK/0nxb0Dej1aAaSNaIwu6qQ6iiYJr3pKH/yUGt4J0AFGmVxaoKk/QQ==";
//        String maHoa = MaHoaAES.maHoaAES(j, keyValue);
//
//        String giaiMa = MaHoaAES.giaiMaAES(i, keyValue);
//        //System.out.println("Chuỗi nhập vào : " + chuoiBanDau);
//        //System.out.println("Mã hoá : " + maHoa);
//        System.out.println("Giải mã : " + maHoa.replace(oldChar, newChar));
    }
}
