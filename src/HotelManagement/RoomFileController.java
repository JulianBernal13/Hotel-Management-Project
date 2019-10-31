package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author Yingxie Gao
 * @date 10/30/19 21:48
 */
public class RoomFileController {

    public static void changeRoomType(String path, int number, String type) throws FileNotFoundException {
        File file = new File(path+File.separator+number+".txt");
        ArrayList<String> oldInfo = FileController.extractInfo(file);
        FileController.cleanFileContent(file);
        PrintWriter writer = new PrintWriter(file);
        switch (type){
            case "single":{
                writer.println(oldInfo.get(Room.RoomProperty.number.ordinal()));
                writer.println("single");
                writer.println(oldInfo.get(Room.RoomProperty.price.ordinal()));
                writer.println(oldInfo.get(Room.RoomProperty.isEmpty.ordinal()));
                writer.println(oldInfo.get(Room.RoomProperty.IsClean.ordinal()));
                writer.println(oldInfo.get(Room.RoomProperty.maintaince.ordinal()));
                writer.println(oldInfo.get(Room.RoomProperty.notes.ordinal()));
                writer.flush();
                writer.close();
                break;
            }
            case "double":{
                writer.println(oldInfo.get(Room.RoomProperty.number.ordinal()));
                writer.println("double");
                writer.println(oldInfo.get(Room.RoomProperty.price.ordinal()));
                writer.println(oldInfo.get(Room.RoomProperty.isEmpty.ordinal()));
                writer.println(oldInfo.get(Room.RoomProperty.IsClean.ordinal()));
                writer.println(oldInfo.get(Room.RoomProperty.maintaince.ordinal()));
                writer.println(oldInfo.get(Room.RoomProperty.notes.ordinal()));
                writer.flush();
                writer.close();
                break;
            }
            case "triple":{
                writer.println(oldInfo.get(Room.RoomProperty.number.ordinal()));
                writer.println("triple");
                writer.println(oldInfo.get(Room.RoomProperty.price.ordinal()));
                writer.println(oldInfo.get(Room.RoomProperty.isEmpty.ordinal()));
                writer.println(oldInfo.get(Room.RoomProperty.IsClean.ordinal()));
                writer.println(oldInfo.get(Room.RoomProperty.maintaince.ordinal()));
                writer.println(oldInfo.get(Room.RoomProperty.notes.ordinal()));
                break;
            }
            case "queen":{
                writer.println(oldInfo.get(Room.RoomProperty.number.ordinal()));
                writer.println("queen");
                writer.println(oldInfo.get(Room.RoomProperty.price.ordinal()));
                writer.println(oldInfo.get(Room.RoomProperty.isEmpty.ordinal()));
                writer.println(oldInfo.get(Room.RoomProperty.IsClean.ordinal()));
                writer.println(oldInfo.get(Room.RoomProperty.maintaince.ordinal()));
                writer.println(oldInfo.get(Room.RoomProperty.notes.ordinal()));
                writer.flush();
                writer.close();
                break;
            }
            case "king":{
                writer.println(oldInfo.get(Room.RoomProperty.number.ordinal()));
                writer.println("king");
                writer.println(oldInfo.get(Room.RoomProperty.price.ordinal()));
                writer.println(oldInfo.get(Room.RoomProperty.isEmpty.ordinal()));
                writer.println(oldInfo.get(Room.RoomProperty.IsClean.ordinal()));
                writer.println(oldInfo.get(Room.RoomProperty.maintaince.ordinal()));
                writer.println(oldInfo.get(Room.RoomProperty.notes.ordinal()));
                writer.flush();
                writer.close();
                break;
            }
            default:{

            }
        }
    }

    public static void applyRoomType(File cur) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        String roomPath = cur.getPath() + File.separator + "Rooms";
        File f = new File(roomPath);
        FileReader.diaplayAllChosenRoom(f, Room.RoomProperty.type);
        System.out.println("Which room are you going to change type?");
        int num = Integer.parseInt(scanner.nextLine());
        System.out.println("What type are you going to change?(single,double,triple,queen,king)");
        String type = scanner.nextLine();
        changeRoomType(roomPath, num, type);
        System.out.println("Success! Now the room is " + type);
    }

    public static void changeRoomOccupied(String path,int number) throws FileNotFoundException {
        File file = new File(path+File.separator+number+".txt");
        ArrayList<String> oldInfo = FileController.extractInfo(file);
        FileController.cleanFileContent(file);

        PrintWriter writer = new PrintWriter(file);
        writer.println(oldInfo.get(Room.RoomProperty.number.ordinal()));
        writer.println(oldInfo.get(Room.RoomProperty.type.ordinal()));
        writer.println(oldInfo.get(Room.RoomProperty.price.ordinal()));
        writer.println("false");
        writer.println(oldInfo.get(Room.RoomProperty.IsClean.ordinal()));
        writer.println(oldInfo.get(Room.RoomProperty.maintaince.ordinal()));
        writer.println(oldInfo.get(Room.RoomProperty.notes.ordinal()));
        writer.flush();
        writer.close();
    }

    public static void changeRoomEmpty(String path, int number) throws FileNotFoundException {
        File file = new File(path+File.separator+number+".txt");
        ArrayList<String> oldInfo = FileController.extractInfo(file);
        FileController.cleanFileContent(file);

        PrintWriter writer = new PrintWriter(file);
        writer.println(oldInfo.get(Room.RoomProperty.number.ordinal()));
        writer.println(oldInfo.get(Room.RoomProperty.type.ordinal()));
        writer.println(oldInfo.get(Room.RoomProperty.price.ordinal()));
        writer.println("true");
        writer.println(oldInfo.get(Room.RoomProperty.IsClean.ordinal()));
        writer.println(oldInfo.get(Room.RoomProperty.maintaince.ordinal()));
        writer.println(oldInfo.get(Room.RoomProperty.notes.ordinal()));
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

    public static int showOccupiedRoom(String path) throws FileNotFoundException {
        File allRoom = new File(path);
        ArrayList<String> emptyRooms = new ArrayList<>();
        int count = 0;
        for (File file : allRoom.listFiles()) {
            Scanner sc = new Scanner(file);
            String originalNumber = sc.nextLine();
            String originalType = sc.nextLine();
            String originalPrice = sc.nextLine();
            String originalIsEmpty = sc.nextLine();
            if (originalIsEmpty.equals("false")) {
                emptyRooms.add(originalNumber + "    " + originalType + "     " + originalPrice);
                count++;
            }
        }
        Collections.sort(emptyRooms);
        Printer.printArray(emptyRooms);
        if (count > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void checkIn(File cur) throws FileNotFoundException {
        String roomPath = cur.getPath() + File.separator + "Rooms";
        System.out.println("What type of room do you want?((single,double,triple,queen,king))");
        Scanner scanner = new Scanner(System.in);
        String type = scanner.nextLine();
        if (showTypeRoomEmpty(roomPath, type) == 1) {
            System.out.println("Which room are you going to check-in?");
            int num = Integer.parseInt(scanner.nextLine());
            changeRoomOccupied(roomPath, num);
            System.out.println("Success! Now the room is occupied.");
        } else {
            System.out.println("Sorry, we do not have such room available.");
        }
    }

    public static void checkOut(File cur) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        String roomPath = cur.getPath() + File.separator + "Rooms";
        System.out.println("which room are you going to check-out?");
        if(showOccupiedRoom(roomPath)==1){
            int num = Integer.parseInt(scanner.nextLine());
            changeRoomEmpty(roomPath, num);
            System.out.println("Success! Now the room is empty.");
        }
        else{
            System.out.println("There is no room occupied.");
        }
    }
}
