import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateKeySpec;
import javax.crypto.Cipher;

public class AES_Decrypt_file {
    public static void main(String[] args) throws Exception {
        String modulusStr = "24659776610887339593593893506568723837921666069418570495851945555280966986839009138615888908494307433634029824383979460112135502029750341429395927091935526478463557840625746678716200928974919239103654153826341810133560653060030642210021776240399608145202896264354644388797075943159690447304467612756865821017394991310939146304104420429057016499804964948189609332848770101543611024830099053636875847654282283120521187392440554947320168603319246486723310764185638409197703588469119289485698568878453317889862045454417508217331829702352065970834772992852241739242711952469584440048365198544360539759053051368105301164771";
        String privateExponentStr = "52821504282129162313919943483299218016571570674105536764598649470250749432855013155520030126378060749655134774673972934525953321884841501045108792101463536815997483438410073850580486587088254702271107084896467539800299507454509763103753238887737021553116224558949609459559216553378983619324867393996441443371027652470058376187643070421715365709037855329161463503076997207255160188976201434200808522350264562668746649617876597704209323160270639716969793537356015692069583560041479968647988169015818164801637355092356722905151352054826711746862206253386775415186736010279062227922111409342065254555184258743997428961";
        String encryptedDataStr = "9A4E8FCE1AF205D649B34515C62080913309F93B8CED717FB14DE8E670CB435AF22E072A842D1A7898311FB2CF31DF55EE70C618C8F9A49EBABF3405B202B6F4BCB0A275BCD0A3AE587D6913D62B0CD25159446BDBF1721DA98EE3CDD567A401D73F589593CECC7A358B36DC2ACEF6E9C6CD78DD49D85C8705A37CD6394881337DD2062B599DA0B659E52246B845B98C8FD0DA812D3C798CB86095B4618742BD7FD3978AB26262002CFC55D27E474E062FBEB8122A4742CE380B16AC904E46CAEFF9FBA402A8641A5A8522888F6100B585FB905416931BE75F65317EE2650B1AB985A374BF512C53CD6D49853A04113B948F07B99BD188FF527F77F1192DDDB4"; // Replace with the actual encrypted data
        
        // Convert modulus and private exponent strings to BigIntegers
        BigInteger modulus = new BigInteger(modulusStr);
        BigInteger privateExponent = new BigInteger(privateExponentStr);
        
        // Create RSAPrivateKeySpec using modulus and private exponent
        RSAPrivateKeySpec privateSpec = new RSAPrivateKeySpec(modulus, privateExponent);
        
        // Generate RSAPrivateKey from the private key specification
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(privateSpec);
        
        // Initialize Cipher for decryption
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        
        // Convert the encrypted data from string to byte array
        byte[] encryptedData = hexStringToByteArray(encryptedDataStr);
        
        // Decrypt the data
        byte[] decryptedData = cipher.doFinal(encryptedData);
        
        // Convert the decrypted data to string
        String decryptedDataStr = new String(decryptedData);
        
        // Print the decrypted data
       
        final String secretKey = decryptedDataStr;
        String inputFile = "encrypt.txt";
        String outputFile = "decrypt.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
            String decSite = AES_algo.decrypt(line, secretKey);

                writer.write(decSite);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Helper method to convert a hexadecimal string to byte array
    public static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        
        return data;
        
    }
}
