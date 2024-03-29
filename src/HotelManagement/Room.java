package HotelManagement;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Yingxie Gao
 * @date 10/18/19 22:40
 */
public class Room {
    private int number;
    private String type;
    private double price;
    private boolean isEmpty;
    private boolean isClean;
    private String path;
    private String maintenance;
    private String notes;
    private boolean isCIDiscount = false;
    private boolean isRRDiscount = false;

    public static enum RoomProperty {
        number, type, price, isEmpty, IsClean, maintenance, notes
    }
    static final int singleRoom=100;
    static final int doubleRoom=150;
    static final int tripleRoom=200;
    static final int queenRoom=200;
    static final int kingRoom=250;

    public Room(int number, String path) {
        this.type = "single";
        this.number = number;
        this.path = path;
        this.isClean = true;
        this.isEmpty = true;
        this.price = 100;
        this.maintenance = "Maintenance:true";
        this.notes = "Notes:true";
    }

    public Room(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        this.path = file.getPath();
        this.number = Integer.parseInt(sc.nextLine());
        this.type = sc.nextLine();
        this.price = Double.parseDouble(sc.nextLine());
        this.isEmpty = Boolean.parseBoolean(sc.nextLine());
        this.isClean = Boolean.parseBoolean(sc.nextLine());
        this.maintenance = sc.nextLine();
        this.notes = sc.nextLine();
    }

    public String getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isClean() {
        return isClean;
    }

    public void setClean(boolean clean) {
        isClean = clean;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setType(String type) {
    	this.type = type;
    }


    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
    
    public String getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(String maintenance) {
        this.maintenance = maintenance;
    }
    
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void createRoomFile() throws IOException {
        File cur = new File(this.path);
        if(cur.createNewFile()) {
            PrintWriter writer = new PrintWriter(cur);
            writer.println(number);
            writer.println(type);
            writer.println(price);
            writer.println(isEmpty);
            writer.println(isClean);
            writer.println(maintenance);
            writer.println(notes);
            writer.println("end");
            writer.flush();
            writer.close();
        }
    }

    public void writeToFile() throws IOException {
        File cur = new File(this.path);
        cur.delete();
        createRoomFile();
    }

    
    public static Room getRoomFile(File cur) throws FileNotFoundException {
        ArrayList<String> oldInfo = FileController.extractInfo(cur);
        String maints = "";
        String not= "";
        for(int i = 0;i < oldInfo.size();i++) {
        	if(oldInfo.get(i).contentEquals("Maintenance:true")) {
        		while(!oldInfo.get(i).contentEquals("Notes:true")) {
        			if(oldInfo.get(i).contentEquals("Maintenance:true")) {
        				if(oldInfo.get(i+1).contentEquals("none")){
        					i++;
        				} else {
        					maints = "none\n";
        					i++;
        				}
        			} else {
        				maints = maints + oldInfo.get(i) + "\n";
        				i++;
        			}
        		}
        		while(!oldInfo.get(i).contentEquals("end")){
        			if(oldInfo.get(i).contentEquals("Notes:true")) {
        				if(oldInfo.get(i+1).contentEquals("none")){
        					i++;
        				} else {
        					not = "none\n";
        					i++;
        				}
        			} else {
        				not = not + oldInfo.get(i) + "\n";
        				i++;
        			}
        		}
        		break;
        	}
        }
        Room newRoom = new Room(Integer.parseInt(oldInfo.get(RoomProperty.number.ordinal())), "hello");
    	newRoom.setClean(Boolean.parseBoolean(oldInfo.get(RoomProperty.IsClean.ordinal())));
    	newRoom.setEmpty(Boolean.parseBoolean(oldInfo.get(RoomProperty.isEmpty.ordinal())));
    	newRoom.setMaintenance(maints);
    	newRoom.setPrice(Double.parseDouble(oldInfo.get(RoomProperty.price.ordinal())));
    	newRoom.setNotes(not);
    	newRoom.setType(oldInfo.get(RoomProperty.type.ordinal()));
    	return newRoom;
    }
    
    public static void WriteToRoomeFile(File cur, Room room) throws IOException {
    		room.setMaintenance("Maintenance:true\n" + room.getMaintenance());
    		String maints[] = room.getMaintenance().split("\\r?\\n");
    		room.setNotes("Notes:true\n" + room.getNotes() + "\nend");
    		String not[] = room.getNotes().split("\\r?\\n");
            PrintWriter writer = new PrintWriter(cur);
            writer.println(room.getNumber());
            writer.println(room.getType());
            writer.println(String.valueOf(room.getPrice()));
            writer.println(String.valueOf(room.isEmpty));
            writer.println(String.valueOf(room.isClean));
            for(int i = 0; i < maints.length;i++) {
            	writer.println(String.valueOf(maints[i]));
            }
            for(int j = 0;j < not.length; j++) {
            	writer.println(String.valueOf(not[j]));
            }
            writer.flush();
            writer.close();

    }
    
    public static String noteMaker(String writer) throws IOException {
		Scanner sc = new Scanner(System.in);
		String command = "";
		String changes = writer;
		boolean toggle = true;
		String lines[] = changes.split("\\r?\\n");
	    ArrayList<String> theList = new ArrayList<String>(Arrays.asList(lines));
    	while(!command.equals("Done")) {
    		Printer.printNoteMaker();
    		if(!changes.isBlank()) {
    			for(int i = 0; i<theList.size();i++) {
    				System.out.println("("+i+")"+theList.get(i));
    			}
    		} else {
    			System.out.println("no writing exist enter a new line");
    		}
    		command = sc.nextLine();
    		switch(command) {
    		case"delete":{
    			while(toggle) {
    				command = sc.nextLine();
    				if(Integer.parseInt(command) < theList.size()) {
    					theList.remove(Integer.parseInt(command));
    					toggle = false;
    				} else {
    					System.out.print("line does not exsit");
    					toggle = false;
    				}
    			}
    			toggle = true;
    			break;
    		}
    		case"Done":{
    			String sender = "";
    			for(int k=0;k<theList.size();k++) {
    				sender = sender + "\n" + theList.get(k);
    			}
    			writer = sender;
    			break;
    		}
    		case"Exit":{
    			command = "Done";
    			break;
    		}
    		default:{
    			theList.add(command);
    		}
    	}
    	}
		return writer;
    }

    @Override
    public String toString() {
        return String.valueOf(this.number);
    }
}
