package week2;
import edu.duke.*;
import java.util.*;

/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        int numStored = 0;
        FileResource fr = new FileResource();
        int count = 0;
        for (String word : fr.words()){
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            
            if(index == -1){
                myWords.add(word);
                myFreqs.add(1);
                numStored++;
            }
            else{
                int value = myFreqs.get(index);
                myFreqs.set(index, value +1);
            }
        }
        
        
    }
    
    public int findIndexOfMax(){
        int currentValue = 0;
        int maxValue = 0;
        int maxIndex = 0;
        
        for (int k=0; k<myFreqs.size(); k++){
            currentValue = myFreqs.get(k);
            if (maxValue<currentValue){
                maxValue = currentValue;
                maxIndex = k;
            }
        }
        System.out.print("The word that occurs most often & its counts: "
        + myWords.get(maxIndex));
        return maxValue;
        
      
    }
    public void tester(){
        findUnique();
        System.out.println("Number of unique words: "+ myFreqs.size());
        for (int k=0; k<myFreqs.size(); k++){
           System.out.println(myWords.get(k) +"\t" + myFreqs.get(k));
        }
        System.out.println(" "+findIndexOfMax());
    }
}
