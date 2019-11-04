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
        this.maintenance = "none";
        this.notes = "none";
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
        File cur = new File(this.path + File.separator + this.number + ".txt");
        if(cur.createNewFile()) {
            PrintWriter writer = new PrintWriter(cur);
            writer.println(number);
            writer.println(type);
            writer.println(String.valueOf(price));
            writer.println(String.valueOf(isEmpty));
            writer.println(String.valueOf(isClean));
            writer.println(String.valueOf(maintenance));
            writer.println(String.valueOf(notes));
            writer.flush();
            writer.close();
        }
    }

    
    public static Room getRoomFile(File cur) throws FileNotFoundException {
        ArrayList<String> oldInfo = FileController.extractInfo(cur);
        Room newRoom = new Room(Integer.parseInt(oldInfo.get(RoomProperty.number.ordinal())), "hello");
    	newRoom.setClean(Boolean.parseBoolean(oldInfo.get(RoomProperty.IsClean.ordinal())));
    	newRoom.setEmpty(Boolean.parseBoolean(oldInfo.get(RoomProperty.isEmpty.ordinal())));
    	newRoom.setMaintenance(oldInfo.get(RoomProperty.maintenance.ordinal()));
    	newRoom.setPrice(Double.parseDouble(oldInfo.get(RoomProperty.price.ordinal())));
    	newRoom.setNotes(oldInfo.get(RoomProperty.notes.ordinal()));
    	newRoom.setType(oldInfo.get(RoomProperty.type.ordinal()));
    	return newRoom;
    }
    
    public static String noteMaker(String writer) throws IOException {
		Scanner sc = new Scanner(System.in);
		String command = "";
    	while(!command.equals("done")) {
    		System.out.println("type 'Backspace' to deleate previous line or press 'enter' to commit current line \n +"
    				+ "type save when done");
    		System.out.print(writer);
    		command = sc.nextLine();
    		switch(command) {
    		case"Backspace":{
    			System.out.print("hello");
    		}
    		case"save":{
    			break;
    		}
    		}
    	}

    	return writer;
    }
}
