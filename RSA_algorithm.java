import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class RSA_algorithm {
    public static void main(String[] args) {
        try {
            // Generate RSA key pair
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048); // Set key size
            KeyPair keyPair = keyGen.generateKeyPair();

            // Get public and private keys
            String publicKey = keyPair.getPublic().toString();
            String privateKey = keyPair.getPrivate().toString();

            // Print the keys
            System.out.println("Public Key: " + publicKey);
            System.out.println("Private Key: " + privateKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}

