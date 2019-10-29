package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    public static String getHotelInfo(File file, Hotel.HotelProperty property) throws FileNotFoundException {
        Scanner in = new Scanner(file);
        int i = 0;
        while(i++ < property.ordinal() && in.hasNext())
            in.nextLine();
        return in.nextLine();
    }

    public static void displayHotelInfo(File file) throws FileNotFoundException {
        Scanner in = new Scanner(file);
        for (Hotel.HotelProperty tmp : Hotel.HotelProperty.values()) {
            System.out.println(tmp.name() + ": " + in.nextLine());
        }
    }
    
    public static void displayRoomInfo(File file) throws FileNotFoundException {
    	Scanner in = new Scanner(file);
    	for (Room.RoomProperty tmp : Room.RoomProperty.values()) {
            System.out.println(tmp.name() + ": " + in.nextLine());
        }
    }

    public static void displayEmpINfo(File info) throws FileNotFoundException{
    }
}
