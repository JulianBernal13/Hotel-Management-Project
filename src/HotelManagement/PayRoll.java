package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PayRoll {
	private int total;
	private HashMap<String,Integer> dates;
	private int payId;
	
    public PayRoll(int id){
    	this.total = 0;
    	dates = new HashMap<String,Integer>();
    	this.payId = id;
    }
    
    public int getPayTotal() {
    	return this.total;
    }
    
    public Map<String,Integer> getDates(){
    	return this.dates;
    }
    
    public int getId() {
    	return this.payId;
    }
	
    void addTotal(int add) {
    	this.total = this.total + add;
    }
    
    void setTotal(int set) {
    	this.total = set;
    }
    
    void addDate(String date, int check) {
    	this.dates.put(date, check);
    }
    
    void setId(int id) {
    	this.payId = id;
    }
    
    public void savePayRoll(File path) throws FileNotFoundException, IOException {
        File cur = new File(path.getPath() + File.separator + "PayRoll");
        if(!cur.exists()) {
        	cur.mkdir();
        }
        File saver = new File(cur.getPath() + File.separator + this.payId + ".txt");
		saver.createNewFile();
        PrintWriter writer = new PrintWriter(saver);
	    writer.println(this.payId);
	    writer.println(this.total);
        for(String key : this.dates.keySet()) {
        	writer.println(key);
        	writer.println(this.dates.get(key));
        }
        writer.flush();
        writer.close();
    }
    
    public void calculationHourPay(File date, int wage, HashMap<Integer,Integer> curDay) throws FileNotFoundException {
        ArrayList<String> oldInfo = FileController.extractInfo(date);
        int i = 0;
        Boolean toggle = true;
        int firsthold = -1;
        int lasthold = -1;
        int timel = 0;
        int timef = 0;
        int hoursworked = 0;
        while(i < oldInfo.size()) {
        	System.out.println(this.payId + " : " + oldInfo.get(i));
        	if(Integer.parseInt(oldInfo.get(i)) == this.payId && firsthold == -1){
        		firsthold = 1;
        		timef = Integer.parseInt(oldInfo.get(i+1));
        	} else if(Integer.parseInt(oldInfo.get(i)) == this.payId && firsthold == 1) {
        		lasthold = 1;
        		timel = Integer.parseInt(oldInfo.get(i+1));
        	}
        	if(firsthold != -1) {
        		if(curDay.containsKey(Integer.parseInt(oldInfo.get(i)))){
        			int id = curDay.get(Integer.parseInt(oldInfo.get(i)));
        			if(id == this.payId) {
        				//hoursworked++;
        			}
        		}
        	}
        	if(lasthold == 1 && firsthold == 1) {
        		lasthold = -1;
        		firsthold = -1;
        		
                int j = 0;
        		while(toggle) {
        			if(curDay.get(timef + j) == this.payId){
        				hoursworked++;
        			}
        			j++;
        			if(!(j < timel - timef)) {
        				toggle = false;
        			}
        		}
        	} 
        	i++;
        }
        this.addTotal(wage * hoursworked);
    }
    
}
