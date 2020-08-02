package week2;

import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    
    private ArrayList<String> seenWords;
    private ArrayList<String> usedCategory;
    
    private Random myRandom;
    
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "week2/data";
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        usedCategory = new ArrayList<String>();
        seenWords = new ArrayList<String>();
    }
    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
        usedCategory = new ArrayList<String>();
       
        seenWords = new ArrayList<String>();
    }
    
    
    private void initializeFromSource(String source) {
        String[] lists = {"adjective", "noun", "color", "country", "name", "animal",
        "timeframe", "verb", "fruit"};
        for (String s : lists){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s,list);
        }
        
        
        
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (!usedCategory.contains(label)) {
            usedCategory.add(label);
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(myMap.get(label));
        
    }
    private int totalWordsInMap(){
        int totalWords = 0;
        for (String s: myMap.keySet()){
            ArrayList<String> value = myMap.get(s);
            int currentCount = value.size();
            totalWords += currentCount;
        }
        return totalWords;
    }
    private int totalWordsConsidered() {
        int totalWords = 0;
        
        for (String key : myMap.keySet()) {
            if (usedCategory.contains(key)) {
                totalWords += myMap.get(key).size();            
            }
        }
        
        return totalWords;
    }
    
    private String processWord(String w){
        
        
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        String firstAnswer = prefix+sub+suffix;
        
        
        if (seenWords.contains(sub)){
            String newsub = getSubstitute(w.substring(first+1,last));
            
            while(newsub.equals(sub)){
                
                sub = newsub;
                newsub = getSubstitute(w.substring(first+1,last));
                
            }
            seenWords.add(newsub);
            return prefix+newsub+suffix;
        }
        else if (!seenWords.contains(sub)){
            seenWords.add(sub);
        }
        
        
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        int numReplaced = 0;
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
                if (!processWord(word).equals(word)){
                    numReplaced ++;
                }
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
                if (!processWord(word).equals(word)){
                    numReplaced ++;
                }
            }
        }
        System.out.println("number of replaced words: "+numReplaced);
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("week2/data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println();
        System.out.println("Total words in map: "+ totalWordsInMap());
        System.out.println("Total words considered: "+totalWordsConsidered());
        seenWords.clear();
    }
    


}
