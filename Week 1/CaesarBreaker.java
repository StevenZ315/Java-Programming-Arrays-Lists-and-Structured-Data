
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author StevenZ
 * @version 05/11/20
 */

import edu.duke.*; 

public class CaesarBreaker {
    private String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private int[] counts = new int[ALPHABET.length()];
    
    void countLetters(String message, int[] counts) {
        for (int i = 0; i < message.length(); i++) {
            char ch = Character.toLowerCase(message.charAt(i));
            int index = ALPHABET.indexOf(ch);
            if (index != -1) counts[index] ++;
        }
    }
    
    int maxIndex(int[] counts) {
        int maxValue = 0;
        int maxIndex = 0;
        
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > maxValue) {
                maxValue = counts[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    String halfOfString(String s, int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < s.length(); i += 2) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
    
    void testHalfOfString() {
        String test = "abcdefghi gleish78970";
        System.out.println(halfOfString(test, 0));
        System.out.println(halfOfString(test, 1));
    }
    
    int getKey(String s) {
        int[] counts = new int[ALPHABET.length()];
        countLetters(s, counts);
        int indexOfE = maxIndex(counts);
        int key = indexOfE - 4;
        if (key < 0) key = key + 26;
        return key;
    }
    
    String decryptTwoKeys(String encrypted) {
        String string1 = halfOfString(encrypted, 0);
        String string2 = halfOfString(encrypted, 1);
        int key1 = getKey(string1);
        System.out.println("Key1 = " + key1);
        int key2 = getKey(string2);
        System.out.println("Key2 = " + key2);
        CaesarCipher cc = new CaesarCipher();
        return (cc.encryptTwoKeys(encrypted, 26-key1, 26-key2));
    }
    
    void testDecryptTwoKeys() {
        String test = "Uybi Gfqgykii Jgziegv Uigeixdiex Smiizzin\n" +
                "\n" +
                "Sei sw klv deec lrpcqrvbw sw fyi jytgvwj yej sivr jiyzxwyc tscprffvrxzsew edsek hzjwiiiex kisltj nmklzr xyi hvtrvkqvrk, azxy iijirvtl kisltj zr sklvv hvtrvkqvrkw ek Uybi, nmkl sklvv mewkmkykij, eeh azxy zruyjxic. Rw av dsmi mexf klv zrwsiqrxzse rkv, xyi jfglw sw jgziegv zw wymwxzrx wvfq xyi hzwtsmiic sw ein zrwsiqrxzse ks xyi gfqgykekmfrrpcc mexvrjmmi xrwb fj tistijwzrx rru rrrppdzrx zrwsiqrxzse.\n" +
                "\n" +
                "Ni lrzv fykwkeehzrx gvfkiedw me xifqvximt tsdtlxzrx; mexvveik jcjxvqj, rvxnsiozrx, eeh wvglvzxp; fzscsxmtec tsdtlxzrx; qvqfvp jcjxvqj rru dejwzzv ueke qrrrkvqvrk; eeh pveirzrx rru dsuicmek. Klv iijirvtl mexvvvwkw sw fyi wetycxp fzvvceg nmkl xyiji eiirw eeh azxy iijirvtlvv eiirw me fxyii umjgztcmeij jytl ej smfpfkp, iekzrviimek, eeeskitlescsxc, rru vrmmiseqvrkec jgziegvw. \n" +
                "\n" +
                "GJ Uigx Tysks Av rpjs hf nsio me r eydfvv sw fxyii zqgsixrrk rvvej zrtplhzrx tsdtlxvv kieglzgj rru mmjyrpzdrxzse, wvrjsi eikafvbw, eydiimtec rrrppwzw, jswxneii iekzrviimek, tsdtciomkc xyifvp, eeh vfffxzgj\n" +
                "\n" +
                "Klv uigeixdiex mj rvxyrfcc yemhyv zr xyi wpqsmfwzw xyek vbzwkw fvxnivr xyi iuytekmfr kislt eeh xyi vvwveigy wetycxp. Xyi wprvvxc fvxnivr xyid yej sivr e ovc xf klv jytgvwj zr gfrkmeyrpcc vvjfvdmek xyi glvimtycyd rru zrkixvrxzrx iijirvtl eeh iuytekmfr. Klv uigeixdiex mj lwzrx r uyrp egtisrgy ks gfqsmei vvwveigy rru vhlgrxzse. Fimekzrx iijirvtl mexf klv tyivzglplq mj klv sijx arc xf kvrme jxlhvrkw esslx xyi qfwk rhmeegvh xvgyrfpfkp rru ks hzwjidmeeki xyi prxvwk uimicsgqvrkw sw tsdtlxzrx kitlescsxc me jstmvxp.\n";
        
        String test1 = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";        
        System.out.println(decryptTwoKeys(test));
    }
    
    
    String decrypt(String encrypted) {
        countLetters(encrypted, counts);
        int indexOfE = maxIndex(counts);
        int key = indexOfE - 4;
        if (key < 0) key = key + 26;
        System.out.println(indexOfE);
        System.out.println(key);
        
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encrypt(encrypted, 26 - key);
        
        return message;
    }
              
    void testDecrypt() {
        String message = "Pi cddc qt xc iwt rdcutgtcrt gddb lxiw ndjg wpi dc udg p hjgegxht epgin. NTAA ADJS! tttttttttttttttttttttttttt";
        System.out.println(decrypt(message));
    }
                
}
