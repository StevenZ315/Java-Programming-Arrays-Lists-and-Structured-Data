
/**
 * Write a description of WordPlay here.
 * 
 * @author StevenZ
 * @version 05/10/20
 */

import java.util.Arrays; 

public class WordPlay {
    boolean isVowel(char ch) {
        String vowel = "aeiou";
        if (vowel.contains(Character.toString(Character.toLowerCase(ch)))) {
            return true;
        } else {
            return false;
        }
    }
    
    void testIsVowel() {
        char test = 'a';
        System.out.println("Is " + test + " vowel? " + isVowel(test));
    }

    String replaceVowels(String phrase, char ch) {
        StringBuilder res = new StringBuilder(phrase);
        for (int i=0; i < res.length(); i++) {
            if (isVowel(res.charAt(i))) {
                res.setCharAt(i, ch);
            }
        }
        return res.toString();
    }
    
    void testReplaceVowels() {
        String test = "Hello World";
        char replacement = '*';
        System.out.println(replaceVowels(test, replacement));
    }
    
    String emphasize(String phrase, char ch) {
        StringBuilder res = new StringBuilder(phrase);
        for (int i=0; i < res.length(); i++) {
            if (Character.toLowerCase(res.charAt(i)) == Character.toLowerCase(ch)) {
                if (i % 2 == 0) {
                    res.setCharAt(i, '*');
                } else {
                    res.setCharAt(i, '+');
                }
            }
        }
        return res.toString();
    }
            
    void testEmphasize() {
        String test = "Mary Bella Abracadabra";
        char key = 'a';
        System.out.println(emphasize(test, key));
    }
    
}
