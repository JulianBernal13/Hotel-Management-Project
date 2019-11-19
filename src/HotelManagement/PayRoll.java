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
	private Map<String,Integer> dates;
	private int payId;
	
    public PayRoll(int id){
    	this.total = 0;
    	this.dates = null;
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
    	this.dates.put(date,check);
    }
    
    void setId(int id) {
    	this.payId = id;
    }
    
    public void savePayRoll(File path) throws FileNotFoundException, IOException {
        File cur = new File(path.getPath() + File.separator + "PayRoll");
        if(!cur.exists()) {
        	cur.mkdir();
        }
        File saver = new File(cur.getPath() + File.pathSeparator + this.payId + ".txt");
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
        int firsthold = -1;
        int lasthold = -1;
        int hoursworked = 0;
        while(i < oldInfo.size()) {
        	if(Integer.parseInt(oldInfo.get(i)) == this.payId && firsthold == -1){
        		firsthold = 1;
        	}
        	if(Integer.parseInt(oldInfo.get(i)) == this.payId && lasthold == -1) {
        		lasthold = 1;
        	}
        	if(firsthold != -1 && lasthold == -1) {
        		if(curDay.containsKey(oldInfo.get(i))) {
        			int id = curDay.get(oldInfo.get(i));
        			if(id == this.payId) {
        				hoursworked++;
        			}
        		}
        	}
        	if(lasthold == 1 && firsthold == 1) {
        		lasthold = -1;
        		firsthold = -1;
        	} 
        	i++;
        }
        this.addTotal(wage * hoursworked);
    }
    
}
