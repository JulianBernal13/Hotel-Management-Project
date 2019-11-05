package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static HotelManagement.Room.*;

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
                oldInfo.set(Room.RoomProperty.type.ordinal(),"single");
                oldInfo.set(Room.RoomProperty.price.ordinal(), String.valueOf(singleRoom));
                for(String tmp:oldInfo){
                    writer.println(tmp);
                }
                writer.flush();
                writer.close();
                break;
            }
            case "double":{
                oldInfo.set(Room.RoomProperty.type.ordinal(),"double");
                oldInfo.set(Room.RoomProperty.price.ordinal(), String.valueOf(doubleRoom));
                for(String tmp:oldInfo){
                    writer.println(tmp);
                }
                writer.flush();
                writer.close();
                break;
            }
            case "triple":{
                oldInfo.set(Room.RoomProperty.type.ordinal(),"triple");
                oldInfo.set(Room.RoomProperty.price.ordinal(), String.valueOf(tripleRoom));
                for(String tmp:oldInfo){
                    writer.println(tmp);
                }
                writer.flush();
                writer.close();
                break;
            }
            case "queen":{
                oldInfo.set(Room.RoomProperty.type.ordinal(),"queen");
                oldInfo.set(Room.RoomProperty.price.ordinal(), String.valueOf(queenRoom));
                for(String tmp:oldInfo){
                    writer.println(tmp);
                }
                writer.flush();
                writer.close();
                break;
            }
            case "king":{
                oldInfo.set(Room.RoomProperty.type.ordinal(),"king");
                oldInfo.set(Room.RoomProperty.price.ordinal(), String.valueOf(kingRoom));
                for(String tmp:oldInfo){
                    writer.println(tmp);
                }
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
        oldInfo.set(RoomProperty.isEmpty.ordinal(), "false");
        for(String tmp:oldInfo){
            writer.println(tmp);
        }
        writer.flush();
        writer.close();
    }

    public static void changeRoomEmpty(String path, int number) throws FileNotFoundException {
        File file = new File(path+File.separator+number+".txt");
        ArrayList<String> oldInfo = FileController.extractInfo(file);
        FileController.cleanFileContent(file);

        PrintWriter writer = new PrintWriter(file);
        oldInfo.set(RoomProperty.isEmpty.ordinal(), "true");
        for(String tmp:oldInfo){
            writer.println(tmp);
        }
        writer.flush();
        writer.close();
    }

    public static int showTypeRoomEmpty(String path, String type) throws FileNotFoundException {
        File allRoom= new File(path);
        ArrayList<String> emptyRooms = new ArrayList<>();
        int count=0;
        for (File file : allRoom.listFiles()) {
            ArrayList<String> oldInfo = FileController.extractInfo(file);
            String originalType = oldInfo.get(RoomProperty.type.ordinal());
            String originalIsEmpty = oldInfo.get(RoomProperty.isEmpty.ordinal());
            String originalNumber = oldInfo.get(RoomProperty.number.ordinal());
            String originalPrice = oldInfo.get(RoomProperty.price.ordinal());
            if(originalType.equals(type) && originalIsEmpty.equals("true")){
                emptyRooms.add(originalNumber + "    "+type + "     " + originalPrice);
                count++;
            }
        }
        Collections.sort(emptyRooms);
        Printer.printArray(emptyRooms);
        return count;
    }

    public static int showOccupiedRoom(String path) throws FileNotFoundException {
        File allRoom = new File(path);
        ArrayList<String> emptyRooms = new ArrayList<>();
        int count = 0;
        for (File file : allRoom.listFiles()) {
            ArrayList<String> oldInfo = FileController.extractInfo(file);
            String originalType = oldInfo.get(RoomProperty.type.ordinal());
            String originalIsEmpty = oldInfo.get(RoomProperty.isEmpty.ordinal());
            String originalNumber = oldInfo.get(RoomProperty.number.ordinal());
            String originalPrice = oldInfo.get(RoomProperty.price.ordinal());
            if (originalIsEmpty.equals("false")) {
                emptyRooms.add(originalNumber + "    " + originalType + "     " + originalPrice);
                count++;
            }
        }
        Collections.sort(emptyRooms);
        Printer.printArray(emptyRooms);
        return count;
    }

    public static void checkIn(File hotel) throws IOException {
        System.out.println("Enter customer's name");
        Scanner sc = new Scanner(System.in);
        File customer = CustomerFileController.registerCustomer(CustomerFileController.cdCustomerFolder(hotel), sc.nextLine());
        String isStaying = CustomerFileController.getCustomerInfo(customer, "isStaying");
        if(isStaying.equals("true")) {
            System.out.println("This customer is already checked in");
            return;
        }
        String roomPath = hotel.getPath() + File.separator + "Rooms";
        System.out.println("What type of room do you want?((single,double,triple,queen,king))");
        Scanner scanner = new Scanner(System.in);
        String type = scanner.nextLine();
        if (showTypeRoomEmpty(roomPath, type) > 0) {
            System.out.println("Which room are you going to check-in?");
            int num = Integer.parseInt(scanner.nextLine());
            File check = new File(roomPath + File.separator + num+".txt");
            while (!check.exists()) {
                System.out.println("This room cannot be checked-in, enter another room \n");
                num = Integer.parseInt(scanner.nextLine());
                check = new File(roomPath + File.separator + num+".txt");
            }
            changeRoomOccupied(roomPath, num);
            CustomerFileController.modifyCustomer(customer, "isStaying", "true");
            //change customer's states
            System.out.println("Success! Now the room is occupied.");
        } else {
            System.out.println("Sorry, we do not have such room available.");
        }
    }

    public static void checkOut(File cur) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        String roomPath = cur.getPath() + File.separator + "Rooms";
        System.out.println("which room are you going to check-out?");
        if(showOccupiedRoom(roomPath) > 0){
            String num = scanner.nextLine();
            File check = new File(roomPath + File.separator + num+".txt");
            while (!check.exists()) {
                System.out.println("This room cannot be checked-out, enter another room \n");
                num = scanner.nextLine();
                check = new File(roomPath + File.separator + num+".txt");
            }
            changeRoomEmpty(roomPath, Integer.parseInt(num));
            System.out.println("Success! Now the room is empty.");
        }
        else{
            System.out.println("There is no room occupied.");
        }
    }

    public static void listRooms(File cur) throws IOException {
        Boolean toggle = true;
        while(toggle) {
            File rooms = new File(cur.getPath() + File.separator + "Rooms");
        	Printer.printRoomview();
            Scanner sc = new Scanner(System.in);
            for (File file : rooms.listFiles()) {
                System.out.println("-" + file.getName());
            }
            String room = sc.nextLine();
            if(room == "exit") {
            	toggle = false;
            	break;
            }
            File check = new File(rooms + File.separator + room);
            while (!check.exists()) {
                System.out.println("command does not exit enter another");
                room = sc.nextLine();
                if(room == "exit") {
                	toggle = false;
                	break;
                }
                check = new File(rooms + File.separator + room);
            }
            if(check.exists()) {
            	DisplayRoom(check);
            }
        }

        // james
    }
    public static void DisplayRoom(File room) throws IOException {
        Boolean toggle = true;
        Scanner sc = new Scanner(System.in);
        Room curRoom = Room.getRoomFile(room);
        while(toggle) {
            curRoom = Room.getRoomFile(room);
            Printer.printRoomInfo(curRoom);
        	System.out.println("enter 'Edit' to modifty current room or 'Exit' to return to manager screen");
        	String command = sc.nextLine();
            switch(command) {
                case "Edit": {
                    EditRoom(room);
                    break;
                }
                case "Exit": {
                	toggle = false;
                    break;
                }
                default: {
                    System.out.println("command does not exit \n" +
                            "enter new command");
                }
            }
        }

    }

    public static void EditRoom(File room) throws IOException {
        Room curRoom = Room.getRoomFile(room);
        Scanner sc = new Scanner(System.in);
        Boolean toggle = true;
        while(toggle){
            Printer.printRoomEditMenu(curRoom);
            String input = sc.nextLine();
            switch(input){
                case"Empty":{
                    if(curRoom.isEmpty() == true) {
                        curRoom.setEmpty(false);
                    } else {
                        curRoom.setEmpty(true);
                    }
                    break;
                }
                case"clean":{
                    if(curRoom.isClean() == true) {
                        curRoom.setClean(false);
                    } else {
                        curRoom.setClean(true);
                    }
                    break;
                }
                case"Maintaince":{
                    curRoom.setMaintenance(Room.noteMaker(curRoom.getMaintenance()));
                    break;
                }
                case"Notes":{
                	curRoom.setNotes(Room.noteMaker(curRoom.getMaintenance()));
                    break;
                }

                case"Exit":{
                	toggle = false;
                	Room.WriteToRoomeFile(room,curRoom);
                    break;
                }
                default:


            }
        }
    }

}
