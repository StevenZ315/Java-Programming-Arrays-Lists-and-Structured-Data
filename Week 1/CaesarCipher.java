
/**
 * Write a description of CaesarCipher here.
 * 
 * @author StevenZ
 * @version 05/11/20
 */
import edu.duke.*;

public class CaesarCipher {
    String encrypt(String input, int key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char currChar = encrypted.charAt(i);
            boolean isLowerCase = Character.isLowerCase(currChar);
            currChar = Character.toUpperCase(currChar);
            
            int idx = alphabet.indexOf(currChar);
            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                if (isLowerCase == true) {
                    newChar = Character.toLowerCase(newChar);
                }
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
          
    String encryptTwoKeys(String input, int key1, int key2) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = new String();
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char currChar = encrypted.charAt(i);
            boolean isLowerCase = Character.isLowerCase(currChar);
            currChar = Character.toUpperCase(currChar);
            
            if (i % 2 == 0) {
                shiftedAlphabet = shiftedAlphabet1;
            } else {
                shiftedAlphabet = shiftedAlphabet2;
            }

            int idx = alphabet.indexOf(currChar);
            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                if (isLowerCase == true) {
                    newChar = Character.toLowerCase(newChar);
                }
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    
    
    public void testEncryptTwoKeys() {
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = new String("Hfs cpwewloj loks cd Hoto kyg Cyy");
        int key1 = 12;
        int key2 = 2;
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println("key is " + key1 + "\n" + encrypted);
    }
    
    public void testCaesar() {
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = new String("Can you imagine life WITHOUT the internet AND computers in your pocket?");
        int key = 15;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
        
    

}
