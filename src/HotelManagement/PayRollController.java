package HotelManagement;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class PayRollController {
	
	public static void PayRollcalc(File hotel, File Secdule){
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
        }
	}
	
	public static void PayDates(File hotel, File Secudle,int id) {
		Boolean toggle = true;
        Scanner sc = new Scanner(System.in);
        String command = "";
    	ArrayList<File> list = FileController.getAllFile(Secudle);
        while(toggle) {
        	for(int i = 0; i < list.size(); i++) {
        		if(!list.get(i).toString().contentEquals(Secudle.toString() + ".txt")){
            		System.out.println(list.get(i).toString());
        		}
        	}
        	System.out.print("enter a date to add to calculation");
        }
	}

}
