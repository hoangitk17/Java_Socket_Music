package mahoa;

import java.io.File;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

public class MaHoaRSA {

    public static PrivateKey getPrivateKey() throws Exception {
        byte[] keyBytes = Files.readAllBytes(new File(TaoKhoaRSA.PRIVATE_KEY_FILE).toPath());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        //KeyPairGenerator  kg = KeyPairGenerator.getInstance(algorithm)
        return kf.generatePrivate(spec);
    }

    public static PublicKey getPublicKey() throws Exception {
        byte[] keyBytes = Files.readAllBytes(new File(TaoKhoaRSA.PUBLIC_KEY_FILE).toPath());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    public static String maHoaRSA(String dulieu) throws Exception {
        PublicKey publicKey = getPublicKey();
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] byteEncrypted = cipher.doFinal(dulieu.getBytes());
        String encrypted = Base64.getEncoder().encodeToString(byteEncrypted);
        // String decrypted = new String(byteDecrypted);
        return encrypted;
    }

    public static String giaiMaRSA(String dulieumahoa) throws Exception {
        PrivateKey privateKey = getPrivateKey();
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] dlmh = Base64.getDecoder().decode(dulieumahoa);
        byte[] byteEncrypted = cipher.doFinal(dlmh);
        String decrypted = new String(byteEncrypted, "UTF-8");
        //System.out.println("giai ma " + byteEncrypted);
        return decrypted;

    }

}
