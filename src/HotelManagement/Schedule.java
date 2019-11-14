package HotelManagement;

import java.io.*;
import java.util.ArrayList;
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
    
    
    public void setDayHourID(String day, int hour, int ID) {
    	this.day.get(day).put(hour, ID);
    }
    
    public static Schedule ScheduleRead(File current) throws FileNotFoundException {
        File cur = new File(current.getPath() + File.separator + current.getName() + ".txt");
        Schedule back = new Schedule("check");
        int line = 0;
        String day = "";
        int hour = 0;
        int ID = 0;
        back.setScheduleName(current.getName());
        ArrayList<String> oldInfo = FileController.extractInfo(cur);
        int i = 0;
        while(i < oldInfo.size()) {
        	day = oldInfo.get(i);
        	i++;
        	for(int j=0;j<24;j++) {
        		hour = Integer.parseInt(oldInfo.get(i));
        		i++;
        		ID = Integer.parseInt(oldInfo.get(i));
        		i++;
        		back.setDayHourID(day, hour, ID);
        	}
        }
        
    	return back;
    }
    
    public void saveSchedule(File path) throws FileNotFoundException, IOException {
        File cur = new File(path.getPath() + File.separator + this.ScheduleName);
        if(!cur.exists()) {
        	cur.mkdir();
        }
        cur = new File(cur.getPath() + File.separator + this.ScheduleName + ".txt");
        if(cur.exists()) {
            PrintWriter writer = new PrintWriter(cur);
            for(String key : day.keySet()) {
                writer.println(key);
            	for(int key2 : day.get(key).keySet()) {
            		writer.println(key2);
            		writer.println(day.get(key).get(key2));
            	}
            }
            writer.flush();
            writer.close();
            System.out.println("reached");	
        }
        else if (cur.createNewFile()) {
            PrintWriter writer = new PrintWriter(cur);
            for(String key : day.keySet()) {
                writer.println(key);
            	for(int key2 : day.get(key).keySet()) {
            		writer.println(key2);
            		writer.println(day.get(key).get(key2));
            	}
            }
            writer.flush();
            writer.close();
            System.out.println("reached");
        }
    }
}
