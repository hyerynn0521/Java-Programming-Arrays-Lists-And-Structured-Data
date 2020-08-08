package VigenereProgram;

import java.util.*;
import edu.duke.*;
import java.io.*;
public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder sb = new StringBuilder();
        for(int k = whichSlice; k<message.length();k+=totalSlices){
            char currChar = message.charAt(k);
            sb.append(currChar);
        }
        return sb.toString();
        
    }
    
    public void testSliceString(){
        System.out.println(sliceString("abcdefghijklm", 3, 5));
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        CaesarCracker ccrack = new CaesarCracker();
        int[] keys = new int[klength];
        
        for(int k = 0; k<klength; k++){
            String currentSlice = sliceString(encrypted, k, klength);
            int currentKey = ccrack.getKey(currentSlice);
            keys[k] = currentKey;
            
            
        }
        return keys;
    }
    
    public void testTryKeyLength(){
        FileResource fr = new FileResource();
        String string = fr.asString();
        int klength = 38;
        char mostcommon = 'e';
        int[] keys = tryKeyLength(string, klength, mostcommon);
        System.out.println(keys);
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        
        HashMap<String, HashSet<String>> languageToWords = 
        new HashMap<String, HashSet<String>>();
        DirectoryResource dictionary = new DirectoryResource();
        for (File f: dictionary.selectedFiles()){
            String FileName = f.getName();
            FileResource dictionaries = new FileResource
            ("VigenereProgram/dictionaries"+"/"+FileName);
            HashSet<String> words = readDictionary(dictionaries);
            languageToWords.put(FileName, words);
            System.out.println("Done reading "+FileName+" dictionary");
        }
        
        breakForAllLangs(encrypted, languageToWords);
        
        
        
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> words = new HashSet<String>();
        
        for(String line:fr.lines()){
            line = line.toLowerCase();
            words.add(line);
        }
        return words;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        String[] words = message.split("\\W+");
        
        int realWords = 0;
        for(int k = 0; k<words.length; k++){
            String currentWord = words[k].toLowerCase();
            if(dictionary.contains(currentWord)){
                realWords ++;
            }
        }
        
        return realWords;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int currentRealWords = 0;
        int maxRealWords = 0;
        String trueDecrypted = "";
        
        
        int trueKeyLength = 0;
        for(int k = 1; k<101; k++){
            
            
            char mostCommonChar = mostCommonCharIn(dictionary);
            int[] keys = tryKeyLength(encrypted, k, mostCommonChar);
            
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            currentRealWords = countWords(decrypted, dictionary);
            if(currentRealWords>maxRealWords){
                maxRealWords = currentRealWords;
                trueDecrypted = decrypted;
                trueKeyLength = keys.length;
            }
        }
        System.out.println(maxRealWords+" are real words");
        System.out.println("key: "+trueKeyLength);
        return trueDecrypted;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<String, Integer> alphabetCount = new HashMap<String, Integer>();
        int maxValue = 0;
        char maxChar = 'a';
        for(String i : dictionary){
            for(int k = 0; k<i.length(); k++){
                String currChar = i.substring(k, k+1);
                if(!alphabetCount.containsKey(currChar)){
                    alphabetCount.put(currChar, 1);
                    
                }
                else{
                    alphabetCount.put(currChar, alphabetCount.get(currChar)+1);
                }
            }
            
        }
        for(String s: alphabetCount.keySet()){
            int currentValue = alphabetCount.get(s);
            if(currentValue>maxValue){
                maxValue = currentValue;
                maxChar = s.charAt(0);
            }
        }
        return maxChar;
    }
    
    public void testMostCommonCharIn(){
        FileResource fr = new FileResource();
        HashSet<String> dictionary = readDictionary(fr);
        System.out.println("Most common letter: "+mostCommonCharIn(dictionary));
    }
    
    public void breakForAllLangs
    (String encrypted, HashMap<String, HashSet<String>> languages){
        String answer = "";
        int realWords = 0;
        int maxRealWords = 0;
        String trueAnswer = "";
        String currentLanguage = "";
        String answerLanguage = "";
        for(String s: languages.keySet()){
            answer = breakForLanguage(encrypted, languages.get(s));
            currentLanguage = s;
            realWords = countWords(answer, languages.get(s));
            if(realWords> maxRealWords){
                maxRealWords = realWords;
                answerLanguage = s;
                trueAnswer = answer;
            }
        }
        System.out.println("Language: "+answerLanguage);
        System.out.println(trueAnswer);
    } 
}   
