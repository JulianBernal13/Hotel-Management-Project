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


    public static void listRooms(File cur) throws IOException {
        Boolean toggle = true;
        while(toggle) {
            File rooms = new File(cur.getPath() + File.separator + "Rooms");
            //rooms.delete();
        	Printer.printRoomview();
            Scanner sc = new Scanner(System.in);
            for (File file : rooms.listFiles()) {
                System.out.println("-" + file.getName());
            }
            String room = sc.nextLine();
            if(room.contentEquals("exit")) {
            	toggle = false;
            	break;
            }
            File check = new File(rooms + File.separator + room);
            while (!check.exists() && toggle) {
                System.out.println("command does not exit enter another");
                room = sc.nextLine();
                if(room.contentEquals("exit")) {
                	toggle = false;
                	break;
                }
                check = new File(rooms + File.separator + room);
            }
            if(check.exists() && toggle) {
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
                	curRoom.setNotes(Room.noteMaker(curRoom.getNotes()));
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
