package week2;
import edu.duke.*;
import java.util.*;
import java.io.*;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordsInFiles;
    
    public WordsInFiles(){
        wordsInFiles = new HashMap<String, ArrayList<String>>();
        
    }
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        
        for (String word: fr.words()){
            String fileName = f.getName();
            ArrayList<String> value = wordsInFiles.get(word);
            if(!wordsInFiles.containsKey(word)){
               wordsInFiles.put(word, new ArrayList<String>());
               value = wordsInFiles.get(word);
               value.add(fileName);
            }
            else{
                if(!value.contains(fileName)){
                    value.add(fileName);
                }
            }
        }
    }
    public void buildWordFileMap(){
        wordsInFiles.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    public int maxNumber(){
        
        int currentNumber = 0;
        int maxNumber = 0;
        for (String s: wordsInFiles.keySet()){
            ArrayList<String> value = wordsInFiles.get(s);
            currentNumber = value.size();
            if(currentNumber > maxNumber){
                maxNumber = currentNumber;
            }
        }
        return maxNumber;
    }
    public ArrayList<String> wordsInNumFiles(int number){
        
        ArrayList<String> collection = new ArrayList<String>();
        for (String s: wordsInFiles.keySet()){
            ArrayList<String> value = wordsInFiles.get(s);
            if(value.size() == number){
                collection.add(s);
            }
        }
        return collection;
    }
    public void printFilesIn(String word){
        ArrayList<String> value = wordsInFiles.get(word);
        for(String s: wordsInFiles.keySet()){
            
            if( s.equals(word)){
                System.out.println("Files that the word "+"\""+word+"\""+ " appears in:");
                for(int k = 0; k<value.size(); k++){
                    System.out.println(value.get(k));
                }
            }
        }
        
    }
    public void tester(){
        buildWordFileMap();
        System.out.println();
        System.out.println("Maximum number of files any word appears in: "+
        maxNumber());
        int number = 3;
        System.out.println("Words that appear in "+number+" files: "+
        wordsInNumFiles(number));
        String word = "cats";
        printFilesIn(word);
    }
}
