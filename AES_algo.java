import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
// AES IMPLIMENTED
public class AES_algo{
    public static SecretKeySpec SecretKey;
    private static byte[] key;
   public static void setkey(String myKey) {
    try {
        key = myKey.getBytes("UTF-8"); // Updated method name and variable name
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        SecretKey = new SecretKeySpec(key, "AES");
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
        e.printStackTrace();
    }
}

// Encryption
public static String encrypt(String strToEnc, String sec) {
    try {
        setkey(sec);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, SecretKey); // Updated variable name
        return Base64.getEncoder().encodeToString(cipher.doFinal(strToEnc.getBytes("UTF-8")));
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

// Decryption
public static String decrypt(String strToEnc, String sec) {
    try {
        setkey(sec);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, SecretKey); // Updated variable name
        return new String(cipher.doFinal(Base64.getDecoder().decode(strToEnc))); // Updated variable name
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

}
