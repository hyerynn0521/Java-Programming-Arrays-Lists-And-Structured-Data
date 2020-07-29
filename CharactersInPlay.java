package week2;
import edu.duke.*;
import java.util.*;

/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> characterCount;
    public CharactersInPlay(){
       names = new ArrayList<String>();
       characterCount = new ArrayList<Integer>();
    }
    public void update(String person){
        
        
        
        
        int personIndex = names.indexOf(person);
        
        
            if (personIndex == -1){
                
                names.add(person);
                characterCount.add(1);
            }
            else{
                int value = characterCount.get(personIndex);
                characterCount.set(personIndex, value+1);
            }
        
            
    }
    
    
    public void findAllCharacters(){
        FileResource fr = new FileResource();
        String character = "";
        for (String line : fr.lines()){
            if (line.contains(".")){
                character = line.substring(0, line.indexOf("."));
                update(character);
            }
        }
    }
    public int findIndexOfMax(){
        int currentValue = 0;
        int maxValue = 0;
        int maxIndex = 0;
        
        for (int k=0; k<characterCount.size(); k++){
            currentValue = characterCount.get(k);
            if (maxValue<currentValue){
                maxValue = currentValue;
                maxIndex = k;
            }
        }
        System.out.print("The character that has the most part: "
        + names.get(maxIndex));
        return maxValue;
        
      
    }
    public void tester(){
        
        charactersWithNumParts(10, 15);
        System.out.println(" "+findIndexOfMax());
        
    }
    public void charactersWithNumParts(int num1, int num2){
        findAllCharacters();
        
        for(int k=0; k<characterCount.size();k++){
            int count = characterCount.get(k);
            if(count>=num1 && count<=num2){
                System.out.println(names.get(k) +"\t" + characterCount.get(k));
            }
            
        }
    }
}
