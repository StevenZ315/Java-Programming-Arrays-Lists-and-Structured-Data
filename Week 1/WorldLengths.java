
/**
 * Write a description of WorldLengths here.
 * 
 * @author StevenZ 
 * @version 05/11/20
 */

import edu.duke.*;

public class WorldLengths {
    void countWordLengths(FileResource resource, int[] counts) {
        int lastIndex = counts.length - 1;
        for (String word : resource.words()) {
            if (word.isEmpty()) {
                break;
            }
            
            int wordLength = word.length();
            int len = wordLength;
            if (!Character.isLetter(word.charAt(0))) len --;
            if (wordLength > 1 && !Character.isLetter(word.charAt(wordLength - 1))) len --;
            
            if (len > lastIndex) {
                counts[lastIndex] ++;
            } else {
                counts[len] ++;
            }
        }
    }
    
    void testCountWordLengths() {
        FileResource fr = new FileResource("manywords.txt");
        int counts[] = new int[31];
        countWordLengths(fr, counts);
        for (int i=0; i < counts.length; i++) {
            System.out.println("Words count with length of " + i + " is " + counts[i]);
        }
        System.out.println("Word length with maximum word count: " + indexOfMax(counts));
    }
        
    int indexOfMax(int[] values) {
        int maxValue = 0;
        int maxIndex = 0;
        
        for (int i = 0; i < values.length; i++) {
            if (values[i] > maxValue) {
                maxValue = values[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
}
