import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
        
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slicedMessage = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            slicedMessage.append(message.charAt(i));
        }
        return slicedMessage.toString();
    }
    
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        CaesarCracker cc = new CaesarCracker(mostCommon);
        int[] keys = new int[klength];
        
        for (int i = 0; i < klength; i++) {
            String slice = sliceString(encrypted, i, klength);
            int key = cc.getKey(slice);
            keys[i] = key;
        }
        return keys;
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dict = new HashSet<String>();
        
        for (String word : fr.words()) {
            dict.add(word.toLowerCase());
        }
        return dict;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        for (String word : message.split("\\W")) {
            if (dictionary.contains(word.toLowerCase())) {
                count ++;
            }
        }
        return count;
    }
    
    private char maxValueKey (HashMap<Character, Integer> map) {
        char maxKey = ' ';
        int maxValue = -1;
        for (char c : map.keySet()) {
            if (map.get(c) > map.getOrDefault(maxKey, 0)) {
                maxKey = c;
                maxValue = map.get(c);
            }
        }
        return maxKey;
    }
    
    public char mostCommonChar(HashSet<String> dictionary) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        HashMap<Character, Integer> counts = new HashMap<Character, Integer>();
        for (String word : dictionary) {
            for (char c : word.toCharArray()) {
                if (alphabet.indexOf(c) != -1) {
                    counts.put(c, counts.getOrDefault(c, 0) + 1);
                }
            }
        }
        return maxValueKey(counts);
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int highestCount = 0;
        String res = "";
        int[] finalKeys = new int[20];
        char mostCommon = mostCommonChar(dictionary);
        
        for (int i = 1; i < 100; i++) {
            int[] keys = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            
            int count = countWords(decrypted, dictionary);
            if (count > highestCount) {
                highestCount = count;
                res = decrypted;
                finalKeys = keys;
            }
        }
        return res;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int bestCount = 0;
        String bestLanguage = "";
        String bestDecrypt = "";
        for (String lang : languages.keySet()) {
            HashSet<String> dictionary = languages.get(lang);
            String decrypted = breakForLanguage(encrypted, dictionary);
            int count = countWords(decrypted, dictionary);
            if (count > bestCount) {
                bestCount = count;
                bestLanguage = lang;
                bestDecrypt = decrypted;
            }
        }

        System.out.println("Message in "+bestLanguage+":");
        System.out.println(bestDecrypt);
        System.out.println("----END----\n");
    }  
    
    private HashMap<String, HashSet<String>> loadDictionaries() {
        String[] langs = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        HashMap<String, HashSet<String>> dictionaries = new HashMap<String, HashSet<String>>();
        for (String lang : langs) {
            dictionaries.put(lang, readDictionary(new FileResource("dictionaries/"+lang)));
        }
        return dictionaries;
    }
    
    public void breakVigenere() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        HashMap<String, HashSet<String>> dictionaries = loadDictionaries();
        breakForAllLangs(encrypted, dictionaries);
        
    }
        
}
