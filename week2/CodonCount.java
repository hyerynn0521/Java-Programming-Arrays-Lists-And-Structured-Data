package week2;
import edu.duke.*;
import java.util.*;

/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CodonCount {
    private HashMap<String, Integer> codonCount;
    
    public CodonCount(){
        codonCount = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna){
        codonCount.clear();
        int value = 0;
        dna = dna.toUpperCase();
        for (int k = start; k<dna.length(); k += 3){ 
            if (k+3 > dna.length()){
                break;
            }
            String currentCodon = dna.substring(k, k+3);
            if (! codonCount.containsKey(currentCodon)){
                codonCount.put(currentCodon, 1);
                value = codonCount.get(currentCodon);
            }
            else{
                codonCount.put(currentCodon, value +1);
                value = codonCount.get(currentCodon);
            }
        }
    }
    public String getMostCommonCodon(){
        int currentValue = 0;
        int maxValue = 0;
        String maxCodon = "";
        for (String codon : codonCount.keySet()){
            currentValue = codonCount.get(codon);
            
            if( currentValue > maxValue){
                maxValue = currentValue;
                maxCodon = codon;
            }
        }
        return maxCodon;
    }
    public void printCodonCounts(int start, int end){
        int count = 0;
        for(String key : codonCount.keySet()){
            count = codonCount.get(key);
            if(count>= start && count<= end){
                System.out.println( key + "\t" + codonCount.get(key));
            }
        }
    }
    public void tester(){
        int start = 0;
        String dna = "CGTTCAAGTTCAA";
        buildCodonMap(start, dna);
        int uniqueCodons = codonCount.size();
        String mostCodon = getMostCommonCodon();
        int mostCodonCount = codonCount.get(mostCodon);
        System.out.println();
        System.out.println("Reading frame starting with "+start+ 
        " results in " +uniqueCodons+ " unique codons");
        System.out.println("and most common codon is "+ mostCodon+
        " with count " + mostCodonCount);
        int startCount = 1;
        int endCount = 5;
        System.out.println("Counts of codons between "+startCount +" and "
        + endCount+ " inclusive are:");
        printCodonCounts(startCount, endCount);
        
    }
}
