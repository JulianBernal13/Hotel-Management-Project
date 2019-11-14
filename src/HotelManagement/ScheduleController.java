package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class ScheduleController {

	public static void ScheduleMenu(File hotel) throws FileNotFoundException, IOException {
		File schedulePath = new File(hotel.getPath()+ File.separator + "Schedules");
		if(!schedulePath.exists()) {
			schedulePath.mkdir();
		}
		Boolean toggle = true;
		Scanner sc = new Scanner(System.in);
		while(toggle) {
			Printer.printScheduleMenu();
			String command = sc.nextLine();
			switch (command) {
			case "list":{
				ListSchedulesMenu(schedulePath,hotel);
				break;
			}
			case "new":{
				MakeNewSchedule(schedulePath);
				break;
			}
			case "Exit":{
				toggle = false;
				break;
			}
			}
		}
	}
	
	public static void ListSchedulesMenu(File Schedules,File hotel) throws IOException {
        Boolean toggle = true;
        while(toggle) {
            for (File file : Schedules.listFiles()) {
                System.out.println("-" + file.getName());
            }
            Printer.printAllSchedules();
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            if(command.contentEquals("Exit")) {
            	toggle = false;
            	break;
            }
            for(File file : Schedules.listFiles()) {
            	if(command.contentEquals(file.getName())) {
            		ViewSchedule(file,hotel);
            		break;
            	}
            }
        }
	}
	
	public static void MakeNewSchedule(File Schedules) throws FileNotFoundException, IOException {
		String name;
		System.out.println("enter name of new Schedule");
        Scanner sc = new Scanner(System.in);
        name = sc.nextLine();
        Schedule newSchedule = new Schedule(name);
        newSchedule.saveSchedule(Schedules);
	}
	
	public static void ViewSchedule(File CurrSchedule,File hotel) throws IOException {
		Boolean toggle = true;
        Boolean trigger = false;
        String day = "";
		Schedule reader = Schedule.ScheduleRead(CurrSchedule);
		while(toggle) {
        	trigger = true;
			Printer.printDayView();
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            switch(command) {
            case"Monday":{
            	day = "Monday";
            	HashMap<Integer,Integer>holder = reader.getDaySchedule("Monday");
            	System.out.println("Monday");
            	for(int i = 0;i<24;i++){
            		System.out.println("hour: " + i + ":00  " + "ID: " + holder.get(i));
            	}
            	break;
            }
            case"Tuseday":{
            	day = "Tuseday";
            	HashMap<Integer,Integer>holder = reader.getDaySchedule("Tuesday");
            	System.out.println("Tuesday");
            	for(Integer key : holder.keySet()) {
            		System.out.println("hour: " + key + ":00  " + "ID: " + holder.get(key));    
            	}
            	break;
            }
            case"Wensday":{
            	day = "Wensday";
            	HashMap<Integer,Integer>holder = reader.getDaySchedule("Wensday");
            	System.out.println("Wensday");
            	for(int i = 0;i<24;i++){
            		System.out.println("hour: " + i + ":00  " + "ID: " + holder.get(i));
            	}
            	break;
            }
            case"Thursday":{
            	day = "Thursday";
            	HashMap<Integer,Integer>holder = reader.getDaySchedule("Thursday");
            	System.out.println("Thursday");
            	for(int i = 0;i<24;i++){
            		System.out.println("hour: " + i + ":00  " + "ID: " + holder.get(i));
            	}
            	break;
            }
            case"Friday":{
            	day = "Friday";
            	HashMap<Integer,Integer>holder = reader.getDaySchedule("Friday");
            	System.out.println("Friday");
            	for(int i = 0;i<24;i++){
            		System.out.println("hour: " + i + ":00  " + "ID: " + holder.get(i));
            	}
            	break;
            }
            case"Saturday":{
            	day = "Saturday";
            	HashMap<Integer,Integer>holder = reader.getDaySchedule("Saturday");
            	System.out.println("Saturday");
            	for(int i = 0;i<24;i++){
            		System.out.println("hour: " + i + ":00  " + "ID: " + holder.get(i));
            	}
            	break;
            }
            case"Sunday":{
            	day = "Sunday";
            	HashMap<Integer,Integer>holder = reader.getDaySchedule("Sunday");
            	System.out.println("Sunday");
            	for(int i = 0;i<24;i++){
            		System.out.println("hour: " + i + ":00  " + "ID: " + holder.get(i));
            	}
            	break;
            } 
            case "Exit":{
            	toggle = false;
            }
            default:{
            	trigger = false;
            }
            }
            while(trigger) {
            	System.out.println("would you like to edit this day? y/n");
            	command = sc.nextLine();
            	if(command.contentEquals("y")) {
            		EditscheduleDay(hotel,reader,CurrSchedule,day);
            	} else if(command.contentEquals("n")) {
            		trigger = false;
            	}
            }
		}
	}
	
	public static void EditscheduleDay(File hotel,Schedule edits,File Schedule,String day) throws FileNotFoundException, IOException {
		Boolean toggle = true;
		Boolean trigger = true;
		File Employees = new File(hotel.getPath() + File.separator + "Employee");
		Scanner sc = new Scanner(System.in);
		int Shour = -1;
		int Ehour = -1;
		while(toggle) {
        	HashMap<Integer,Integer>holder = edits.getDaySchedule(day);
        	System.out.println(day);
        	for(int i = 0;i<24;i++){
        		System.out.println("hour: " + i + ":00  " + "ID: " + holder.get(i));
        	}
        	while(trigger) {
            	System.out.println("enter an hour for a start time (ex 1,2,..,12");
        		Shour = Integer.parseInt(sc.nextLine());
        		if(Shour < 24 && 0<=Shour) {
                	System.out.println("enter an hour for a end time (ex 1,2,..,12");
                	Ehour = Integer.parseInt(sc.nextLine());
            		if(Ehour < 24 && Shour<=Ehour) {
            			trigger = false;
            		} else {
            			Ehour = -1;
            		}
        		} else {
        			Shour = -1;
        		}
        	}
        	while(!trigger) {
        		System.out.println("enter ID of employee for this worktime");
        		EmployeeFileController.ListAllEmployees(hotel);
        		String temp = sc.nextLine();
        		if(EmployeeFileController.checkID(hotel,temp)) {
        			temp = temp.substring(0, temp.length()-4);
        			while(Shour <= Ehour) {
        				edits.setDayHourID(day, Shour, Integer.parseInt(temp));
        				Shour++;
        				trigger = true;
        				File s = new File(hotel.getPath() + File.separator + "Schedules");
        				edits.saveSchedule(s);
        			}
        		}
        	}
        	toggle = false;
		}
	}
	
}
