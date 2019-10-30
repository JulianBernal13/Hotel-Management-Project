package HotelManagement;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
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
    private String maintaince;
    private String notes;

    public static enum RoomProperty {
        number, type, price, isEmpty, IsClean, maintaince, notes
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
        this.maintaince = "none";
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

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
    
    public String Maintaince() {
        return maintaince;
    }

    public void setMaintaince(String maintaince) {
        this.maintaince = maintaince;
    }
    
    public String Notes() {
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
            writer.println(String.valueOf(maintaince));
            writer.println(String.valueOf(notes));
            writer.flush();
            writer.close();
        }
    }

    public static void changeRoomType(String path, int number, String type) throws FileNotFoundException {
        File file = new File(path+File.separator+number+".txt");
        Scanner sc = new Scanner(file);
        ArrayList<String> oldInfo = new ArrayList<>();
        while(sc.hasNext()){
            oldInfo.add(String.valueOf(sc.nextLine()));
        }
        FileReader.cleanFileContent(file);
        PrintWriter writer = new PrintWriter(file);
        switch (type){
            case "single":{
                writer.println(oldInfo.get(0));
                writer.println("single");
                writer.println(oldInfo.get(2));
                writer.println(oldInfo.get(3));
                writer.println(oldInfo.get(4));
                writer.println(oldInfo.get(5));
                writer.println(oldInfo.get(6));
                writer.flush();
                writer.close();
                break;
            }
            case "double":{
                writer.println(oldInfo.get(0));
                writer.println("double");
                writer.println(oldInfo.get(2));
                writer.println(oldInfo.get(3));
                writer.println(oldInfo.get(4));
                writer.println(oldInfo.get(5));
                writer.println(oldInfo.get(6));
                writer.flush();
                writer.close();
                break;
            }
            case "triple":{
                writer.println(oldInfo.get(0));
                writer.println("triple");
                writer.println(oldInfo.get(2));
                writer.println(oldInfo.get(3));
                writer.println(oldInfo.get(4));
                writer.println(oldInfo.get(5));
                writer.println(oldInfo.get(6));
                break;
            }
            case "queen":{
                writer.println(oldInfo.get(0));
                writer.println("queen");
                writer.println(oldInfo.get(2));
                writer.println(oldInfo.get(3));
                writer.println(oldInfo.get(4));
                writer.println(oldInfo.get(5));
                writer.println(oldInfo.get(6));
                writer.flush();
                writer.close();
                break;
            }
            case "king":{
                writer.println(oldInfo.get(0));
                writer.println("king");
                writer.println(oldInfo.get(2));
                writer.println(oldInfo.get(3));
                writer.println(oldInfo.get(4));
                writer.println(oldInfo.get(5));
                writer.println(oldInfo.get(6));
                writer.flush();
                writer.close();
                break;
            }
            default:{

            }
        }
    }
    public static void checkIn(String path,int number) throws FileNotFoundException {
        File file = new File(path+File.separator+number+".txt");
        Scanner sc = new Scanner(file);
        ArrayList<String> oldInfo = new ArrayList<>();
        while(sc.hasNext()){
            oldInfo.add(String.valueOf(sc.nextLine()));
        }
        FileReader.cleanFileContent(file);
        PrintWriter writer = new PrintWriter(file);
        writer.println(oldInfo.get(0));
        writer.println(oldInfo.get(1));
        writer.println(oldInfo.get(2));
        writer.println("false");
        writer.println(oldInfo.get(4));
        writer.println(oldInfo.get(5));
        writer.println(oldInfo.get(6));
        writer.flush();
        writer.close();
    }

    public static void checkOut(String path, int number) throws FileNotFoundException {
        File file = new File(path+File.separator+number+".txt");
        Scanner sc = new Scanner(file);
        ArrayList<String> oldInfo = new ArrayList<>();
        while(sc.hasNext()){
            oldInfo.add(String.valueOf(sc.nextLine()));
        }
        FileReader.cleanFileContent(file);
        PrintWriter writer = new PrintWriter(file);
        writer.println(oldInfo.get(0));
        writer.println(oldInfo.get(1));
        writer.println(oldInfo.get(2));
        writer.println("true");
        writer.println(oldInfo.get(4));
        writer.println(oldInfo.get(5));
        writer.println(oldInfo.get(6));
        writer.flush();
        writer.close();
    }

    public static int showTypeRoomEmpty(String path, String type) throws FileNotFoundException {
        File allRoom= new File(path);
        ArrayList<String> emptyRooms = new ArrayList<>();
        int count=0;
        for (File file : allRoom.listFiles()) {
            Scanner sc = new Scanner(file);
            String originalNumber = sc.nextLine();
            String originalType = sc.nextLine();
            String originalPrice = sc.nextLine();
            String originalIsEmpty = sc.nextLine();
            if(originalType.equals(type) && originalIsEmpty.equals("true")){
                emptyRooms.add(originalNumber + "    "+type + "     " + originalPrice);
                count++;
            }
        }
        Collections.sort(emptyRooms);
        Printer.printArray(emptyRooms);

        if(count>0){
            return 1;
        }
        else {
            return 0;
        }
    }
}
