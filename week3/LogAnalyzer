package WebLogProgram;


/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete coanstructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for(String lines:fr.lines()){
             LogEntry le = WebLogParser.parseEntry(lines);
             records.add(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         
         for (LogEntry le: records){
             String ipAddress = le.getIpAddress();
             if(!uniqueIPs.contains(ipAddress)){
                 uniqueIPs.add(ipAddress);
             }
         }
         return uniqueIPs.size();
     }
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     public void printAllHigherThanNum(int num){
         for (LogEntry le: records){
             int statusCode = le.getStatusCode();
             if(statusCode>num){
                 System.out.println(le);
             }
         }
     }
     public ArrayList<String> uniqueIPVisitsInDay(String someday){
        ArrayList<String> ipVisits = new ArrayList<String>();
        for(LogEntry le:records){
            String date = le.getAccessTime().toString().substring(4, 10);
            if(date.equals(someday)){
                String ipAdd = le.getIpAddress();
                if(!ipVisits.contains(ipAdd)){
                    ipVisits.add(ipAdd);
                }
            }
            
        }
        return ipVisits;
     }
     public int countUniqueIPsInRange(int low, int high){
         int uniqueCount = 0;
         ArrayList<String> uniqueIP = new ArrayList<String>();
         for(LogEntry le: records){
             int statusCode = le.getStatusCode();
             String ipAdd = le.getIpAddress();
             if(statusCode>= low && statusCode<=high){
                 if(!uniqueIP.contains(ipAdd)){
                     uniqueIP.add(ipAdd);
                     uniqueCount++;
                    }
             }
         }
         return uniqueCount;
     }
     public HashMap<String, Integer> countVisitsPerIP(){
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for(LogEntry le: records){
             String currentIP = le.getIpAddress();
             if(!counts.containsKey(currentIP)){
                 counts.put(currentIP, 1);
             }
             else{
                 counts.put(currentIP, counts.get(currentIP)+1);
             }
         }
         return counts;
     }
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
         int currentValue = 0;
         int maxValue = 0;
         for (String s:counts.keySet()){
             currentValue = counts.get(s);
             if(currentValue>maxValue){
                maxValue = currentValue;
             }
         }
         return maxValue;
     }
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts){
         ArrayList<String> ipMostVisits = new ArrayList<String>();
         int maxValue = mostNumberVisitsByIP(counts);
         for (String s: counts.keySet()){
            
            int currentValue = counts.get(s);
            if (currentValue == maxValue){
                ipMostVisits.add(s);
            }
         }
         return ipMostVisits;
     }
     public HashMap<String, ArrayList<String>> iPsForDays(){
         HashMap<String, ArrayList<String>> iPsToDays = 
         new HashMap<String, ArrayList<String>>();
         for(LogEntry le: records){
             String date = le.getAccessTime().toString().substring(4, 10);
             String ipAddr = le.getIpAddress();
             if(!iPsToDays.containsKey(date)){
                
                iPsToDays.put(date, new ArrayList<String>());
                ArrayList<String> ips = iPsToDays.get(date);
                ips.add(ipAddr);
             }
             else{
                 iPsToDays.get(date).add(ipAddr);
                 
                }
         }
         return iPsToDays;
     }
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPsToDays){
         int currentValue = 0;
         int maxValue = 0;
         String currentDate = "";
         String maxDate = "";
         for (String s: iPsToDays.keySet()){
             currentValue = iPsToDays.get(s).size();
             currentDate = s;
             if(currentValue>maxValue){
                maxValue = currentValue;
                maxDate = currentDate;
             }
         }
         return maxDate;
     }
     public ArrayList<String> iPsWithMostVisitsOnDay
     (HashMap<String, ArrayList<String>> iPsToDays, String date){
         HashMap<String, ArrayList<String>> mymap = iPsForDays();
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for(String s: mymap.keySet()){
             if(s.equals(date)){
                ArrayList<String> ips = mymap.get(s);    
                for (int k= 0; k<ips.size();k++){
                    if(!counts.containsKey(ips.get(k))){
                        String key = ips.get(k);
                        counts.put(ips.get(k), 1);
                        
                    }
                    else{
                        counts.put(ips.get(k), counts.get(ips.get(k))+1);
                    }
                }
             }
         }
         return iPsMostVisits(counts);
     }
     
}
