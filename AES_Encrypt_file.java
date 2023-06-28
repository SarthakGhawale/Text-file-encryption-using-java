import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKey;

public class AES_Encrypt_file {
    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "encrypt.txt";

        final String secretKey = getSecretKeyFromUsername();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String encSite = AES_algo.encrypt(line, secretKey);

                writer.write(encSite);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String modulus = "24659776610887339593593893506568723837921666069418570495851945555280966986839009138615888908494307433634029824383979460112135502029750341429395927091935526478463557840625746678716200928974919239103654153826341810133560653060030642210021776240399608145202896264354644388797075943159690447304467612756865821017394991310939146304104420429057016499804964948189609332848770101543611024830099053636875847654282283120521187392440554947320168603319246486723310764185638409197703588469119289485698568878453317889862045454417508217331829702352065970834772992852241739242711952469584440048365198544360539759053051368105301164771";
            String publicExponent = "65537";
            
            Encrypt_RSA encryptor = new Encrypt_RSA(modulus, publicExponent);
            
            String data = secretKey;
            String encryptedData = encryptor.encryptData(data);
            
            System.out.println("Encrypted Data: " + encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String getSecretKeyFromUsername() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter your username: ");
        String username = null;
        try {
            username = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Process the username to generate a secret key
        final String secretKey = processUsername(username);

        return secretKey;
    }

    private static String processUsername(String username) {
        try {
            // Create a MessageDigest instance with SHA-256 algorithm
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            // Convert the username to bytes
            byte[] usernameBytes = username.getBytes();

            // Generate the hash value for the username
            byte[] hashBytes = messageDigest.digest(usernameBytes);

            // Convert the hash bytes to a hexadecimal string representation
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // Take the first 16 characters as the secret key
           String secretKey = hexString.toString().substring(0, 16);
            return secretKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
