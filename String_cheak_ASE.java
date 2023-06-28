import java.io.File;

public class String_cheak_ASE {
    public static void main(String[] args) {
        
        final String secretKey = "7572";
        String originalString = "Secret site : - http:10.67.84.101";
        //Encrypt
        String encSite = AES_algo.encrypt(originalString, secretKey);

        //Decrypt
        String decSite = AES_algo.decrypt(encSite, secretKey);

        //Display
        System.out.println("Original: " + originalString);
        System.out.println("Encrypted text: " + encSite);
        System.out.println("Decrypted text: " + decSite);
        System.out.println("Key is "+ AES_algo.SecretKey);

        
    }
}
