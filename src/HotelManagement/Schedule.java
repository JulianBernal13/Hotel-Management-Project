package HotelManagement;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Schedule {
	private String ScheduleName;
    private Map<String, HashMap<Integer, Integer>> day; 


    public Schedule(String name) {
    	ScheduleName = name;
    	day = new HashMap<String, HashMap<Integer, Integer>>();
    	String dayName = "Sunday";
    	for(int i = 0; i < 7; i++) {
            HashMap<Integer, Integer> hour = new HashMap<>();
    		switch(i) {
    		case 0:{
    			break;
    		}
    		case 1:{
    			dayName = "Monday";
    			break;
    		}
    		case 2:{
    			dayName = "Tuesday";
    			break;
    		}
    		case 3:{
    			dayName = "Wensday";
    			break;
    		}
    		case 4:{
    			dayName = "Thursday";
    			break;
    		}
    		case 5:{
    			dayName = "Friday";
    			break;
    		}
    		case 6:{
    			dayName = "Saturday";
    			break;
    		}
    		}
    		for(int j = 0; j<24; j++) {
    			hour.put(j, 0);
    		}
    		day.put(dayName, hour);
    	}
    }
    
    public String getScheduleName() {
    	return this.ScheduleName;
    }
    
    public void setScheduleName(String name) {
    	this.ScheduleName = name;
    }
    
    public HashMap<Integer, Integer> getDaySchedule(String day){
    	return this.day.get(day);
    }
    
    public void setDayHour(String day, int hour, int ID) {
    	this.day.get(day).put(hour, ID);
    }
    
    public void saveSchedule(File path) throws FileNotFoundException, IOException {
        File cur = new File(path.getParent() + File.separator + this.ScheduleName);
        if(cur.createNewFile()) {
            PrintWriter writer = new PrintWriter(cur);
            
            writer.println("end");
            writer.flush();
            writer.close();
        }
    }
}
