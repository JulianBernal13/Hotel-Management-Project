package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PayRollController {
	
	public static void PayRollcalc(File hotel, File Secdule) throws NumberFormatException, IOException{
		Boolean toggle = true;
        Scanner sc = new Scanner(System.in);
        String command = "";
        while(toggle) {
    		EmployeeFileController.ListAllEmployees(hotel);
        	Printer.printPayRollOptoins();
        	command = sc.nextLine();
        	if(EmployeeFileController.checkID(hotel,command)) {
        		String temp = command.substring(0, command.length()-4);
        		PayDates(hotel, Secdule, Integer.parseInt(temp));
        	}
        	System.out.println("do you want to do more payRolls? y/n");
        	command = sc.nextLine();
        	if(command.contentEquals("n")) {
        		toggle = false;
        	}
        }
	}
	
	public static void PayDates(File hotel, File Secudle,int id) throws IOException {
		Schedule currsec = Schedule.ScheduleRead(Secudle);
		Boolean toggle = true;
        Scanner sc = new Scanner(System.in);
        String command = "";
    	ArrayList<File> list = FileController.getAllFile(Secudle);
    	PayRoll yes = new PayRoll(id);
        while(toggle) {
        	for(int i = 0; i < list.size(); i++) {
        		if(!list.get(i).getName().contentEquals(Secudle.getName() + ".txt")){
            		System.out.println(list.get(i).toString());
        		}
        	}
        	System.out.println("enter a date to add to calculation");
        	command = sc.nextLine();
        	File check = new File(Secudle.getPath() + File.separator + command);
        	if(list.contains(check)) {
        		String holder = list.get(list.indexOf(check)).getName();
        		list.remove(check);
        		System.out.println("what day is this");
        		command = sc.nextLine();
        		int wage = EmployeeFileController.Getwage(hotel, id);
        		yes.calculationHourPay(check, wage, currsec.getDaySchedule(command));
        		yes.addDate(check.getName(), 2);
        	}
        	System.out.println("add more dates? y/n");
        	command = sc.nextLine();
        	if(command.contentEquals("n")) {
        		toggle = false;
        	}
        }
        yes.savePayRoll(Secudle);
	}

}
