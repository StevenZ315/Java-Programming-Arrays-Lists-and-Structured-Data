
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author StevenZ
 * @version 05/12/20
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
    
    private ArrayList<LogEntry> records;
    
    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }
    
    public void readFile(String filename) {
        FileResource fr = new FileResource();
        for (String rec : fr.lines()) {
            records.add(WebLogParser.parseEntry(rec));
        }
    }
    
    private int countUniqueByIP (ArrayList<LogEntry> list) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : list) {
            String address = le.getIpAddress();
            if (! uniqueIPs.contains(address))
                uniqueIPs.add(address);
        }
        return uniqueIPs.size();
    }

    
    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (!uniqueIPs.contains(ip)) {
                uniqueIPs.add(ip);
            }
        }   
        return uniqueIPs.size();        
    }
    
    void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            if (le.getStatusCode() > num) {
                System.out.println(le);
                }
            }
    }
    
    private String getDate(LogEntry le) {
        return le.getAccessTime().toString().substring(4, 10);
    }
    
    ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            if (getDate(le).equals(someday) && !uniqueIPs.contains(le.getIpAddress())) {
                uniqueIPs.add(le.getIpAddress());
            }
        }
        return uniqueIPs;
    }
    
    public int countUniqueIPsInRange (int low, int high) {
        ArrayList<LogEntry> byStatus = new ArrayList<LogEntry>();
        for (LogEntry le : records) {
            if (low <= le.getStatusCode() && le.getStatusCode() <= high)
                byStatus.add(le);
        }

        return countUniqueByIP(byStatus);
    }
    
    public HashMap<String, Integer> countVisitsPerIP () {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (LogEntry le : records) {
            String address = le.getIpAddress();
            counts.put(address, counts.getOrDefault(address, 0) + 1);
        }
        return counts;
    }

    public int mostNumberVisitsByIP (HashMap<String, Integer> counts) {
        if (counts == null || counts.isEmpty()) return 0;

        int most = 0;
        for (String address : counts.keySet()) {
            int count = counts.get(address);
            if (count > most) most = count;
        }
        return most;
    }

    public ArrayList<String> iPsMostVisits (HashMap<String, Integer> counts) {
        ArrayList<String> results = new ArrayList<String>();
        int maxVisits = mostNumberVisitsByIP(counts);

        for (String key : counts.keySet()) {
            if (counts.get(key) == maxVisits) results.add(key);
        }

        return results;
    }  

    public HashMap<String, ArrayList<String>> iPsForDays () {
        HashMap<String, ArrayList<String>> forDays = new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records) {
            String mmm_dd = getDate(le);
            ArrayList<String> today = forDays.getOrDefault(mmm_dd, new ArrayList<String>());
            today.add(le.getIpAddress());
            forDays.put(mmm_dd, today);
        }
        return forDays;
    }

    public String dayWithMostIPVisits (HashMap<String, ArrayList<String>> byDays) {
        if (byDays == null || byDays.isEmpty()) return "";

        String most = "";
        for (String day : byDays.keySet()) {
            if (byDays.get(day).size() > byDays.getOrDefault(most, new ArrayList<String>()).size()) {
                most = day;
            }
        }

        return most;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay (HashMap<String, ArrayList<String>> byDays, String mmm_dd) {
        ArrayList<String> results = new ArrayList<String>();
        ArrayList<String> theDay = byDays.getOrDefault(mmm_dd, new ArrayList<String>());
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (String address : theDay) {
            counts.put(address, counts.getOrDefault(address, 0) + 1);
        }
        int most = mostNumberVisitsByIP(counts);

        for (String key : counts.keySet()) {
            if (counts.get(key) == most)
                results.add(key);
        }

        return results;
    } // iPsWithMostVisitsOnDay
    
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
}
