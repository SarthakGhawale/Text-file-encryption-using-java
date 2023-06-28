import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;

public class Encrypt_RSA {
    private PublicKey publicKey;
    
    public Encrypt_RSA(String modulus, String publicExponent) throws Exception {
        // Create RSA public key using key components
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(new java.math.BigInteger(modulus), new java.math.BigInteger(publicExponent));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        publicKey = keyFactory.generatePublic(keySpec);
    }
    
    public String encryptData(String data) throws Exception {
        // Convert string data to be encrypted into bytes
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        
        // Initialize RSA cipher for encryption
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        
        // Encrypt the data
        byte[] encryptedBytes = cipher.doFinal(bytes);
        
        // Convert the encrypted data to a hexadecimal string
        return bytesToHex(encryptedBytes);
    }
    
    // Helper method to convert bytes to a hexadecimal string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}
