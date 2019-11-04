package HotelManagement;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HotelFileController implements FileController{
    public static void createHotel(File file) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your new hotel's name?");
        String name = sc.nextLine();
        File hotelFile = new File(file.getPath() + File.separator + name);
        while (!hotelFile.mkdir()) {
            System.out.println("This name is taken, please enter another name");
            name = sc.nextLine();
            hotelFile = new File(file.getPath() + File.separator + name);
        }
        System.out.println("What is your hotel's location? Enter an Address.");
        String address = sc.nextLine();
        System.out.println("How many floors does your hotel have?");
        int floor = Integer.parseInt(sc.nextLine());
        System.out.println("How many rooms does your hotel have for each floor?");
        int numRoom = Integer.parseInt(sc.nextLine());
        System.out.println("Please set a password for this hotel.");
        String password = sc.nextLine();
        writeHotelToFile(hotelFile, name, new Location(address), floor, numRoom, password);
    }

    public static void writeHotelToFile(File hotelFile, String name, Location location,
                                        int numOfLevel, int levelRmNum, String password) throws IOException {
        File info = FileController.createTxtFile(hotelFile, "info");
        PrintWriter writer = new PrintWriter(info);
        writer.println(name);
        writer.println(location);
        writer.println(numOfLevel);
        writer.println(levelRmNum);
        writer.println(password);
        writer.flush();
        writer.close();

        FileController.createDirectory(hotelFile, "Customer");
        FileController.createDirectory(hotelFile, "Employee");

        File roomsFile = FileController.createDirectory(hotelFile, "Rooms");
        for (int i = 1; i <= numOfLevel; i++) {
            for (int j = 0; j < levelRmNum; j++) {
                new Room(i * 100 + j, roomsFile.getPath()).createRoomFile();
            }
        }
    }
}
