package WebLogProgram;


/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("WebLogProgram/short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("WebLogProgram/weblog2_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are "+uniqueIPs+" unique IP addresses");
    }
    
    public void testprintAllHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("WebLogProgram/weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testuniqueIPVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("WebLogProgram/weblog2_log");
        ArrayList<String> uniqueIPs = la.uniqueIPVisitsInDay("Sep 27");
        System.out.println(uniqueIPs.size());
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("WebLogProgram/weblog2_log");
        int low = 200;
        int high = 299;
        int uniqueIP = la.countUniqueIPsInRange(low, high);
        System.out.println("There are "+uniqueIP+" unique ip addresses that have a status code from "
        +low+" to "+high);
    }
    
    public void testCountVisitsPerIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("WebLogProgram/short-test_log");
        System.out.println(la.countVisitsPerIP());
    }
    
    public void testMostNumberVisitsByIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("WebLogProgram/weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println("Max # of visits by one IP: "+ la.mostNumberVisitsByIP(counts));
    }
    
    public void testIPsMostVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("WebLogProgram/weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(la.iPsMostVisits(counts));
    }
    
    public void testIPsForDays(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("WebLogProgram/weblog3-short_log");
        System.out.println(la.iPsForDays());
    }
    
    public void testDayWithMostIPVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("WebLogProgram/weblog2_log");
        HashMap<String, ArrayList<String>> iPsToDays = la.iPsForDays();
        System.out.println("Date with most visits: "+la.dayWithMostIPVisits(iPsToDays));
    }
    
    public void testIPsWithMostVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("WebLogProgram/weblog2_log");
        HashMap<String, ArrayList<String>> map = la.iPsForDays();
        System.out.println(la.iPsWithMostVisitsOnDay(map, "Sep 30"));
    }
}
