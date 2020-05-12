
/**
 * Write a description of test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class test {
    void testSliceString() {
        FileResource fr = new FileResource();
        VigenereBreaker vc = new VigenereBreaker();
        String test = fr.asString();
        String res = vc.sliceString(test, 0, 4);
        System.out.println(res);
    }
    
    void testTryKeyLength() {
        FileResource fr = new FileResource();
        VigenereBreaker vc = new VigenereBreaker();
        String test = fr.asString();
        System.out.println(Arrays.toString(vc.tryKeyLength(test, 38, 'e')));
    }
    
    void testBreakForLanguage() {
        VigenereBreaker vc = new VigenereBreaker();
        FileResource frDict = new FileResource();
        HashSet<String> dict = vc.readDictionary(frDict);
        
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String decrypted = vc.breakForLanguage(encrypted, dict);
        System.out.println(decrypted);
    }
        
    
    void testBreakVigenere() {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }
}
