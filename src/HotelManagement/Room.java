package HotelManagement;

import java.io.*;
import java.util.ArrayList;
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
        this.notes = "Notes:true\n" + "end";
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
        		while(oldInfo.get(i) != "Notes:true") {
        			if(oldInfo.get(i) == "Maintenacne:true") {
        				maints = "none";
        				i++;
        			} else {
        				maints = maints + oldInfo.get(i);
        				i++;
        			}
        		}
        		while(oldInfo.get(i) != "end") {
        			if(oldInfo.get(i) == "Notes:true") {
        				not = "none";
        				i++;
        			} else {
        				not = not + oldInfo.get(i);
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
    		room.setNotes("Notes:true\n" + room.getNotes() + "\nend");
            PrintWriter writer = new PrintWriter(cur);
            writer.println(room.getNumber());
            writer.println(room.getType());
            writer.println(String.valueOf(room.getPrice()));
            writer.println(String.valueOf(room.isEmpty));
            writer.println(String.valueOf(room.isClean));
            writer.println(String.valueOf(room.getMaintenance()));
            writer.println(String.valueOf(room.getNotes()));
            writer.flush();
            writer.close();

    }
    
    public static String noteMaker(String writer) throws IOException {
		Scanner sc = new Scanner(System.in);
		String command = "";
		String changes = writer;
    	while(!command.equals("Done")) {
    		Printer.printNoteMaker();
    		if(!changes.isBlank()) {
    			String lines[] = changes.split("\\r?\\n");
    			for(int i = 0; i<lines.length;i++) {
    				System.out.println("("+i+")"+lines[i]);
    			}
    		} else {
    			System.out.print("no writing exist enter a new line");
    		}
    		command = sc.nextLine();
    		switch(command) {
    		case"delete":{
    			break;
    		}
    		case"Done":{
    			writer = changes;
    			break;
    		}
    		case"Exit":{
    			command = "Done";
    			break;
    		}
    		default:{
    			changes = changes + "\n" + command;
    		}
    	}
    	}
		return writer;
    }
}
