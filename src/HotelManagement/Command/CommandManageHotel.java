package HotelManagement.Command;

import HotelManagement.DetermineOccupation;
import HotelManagement.Hotel;
import HotelManagement.Printer;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class CommandManageHotel implements iCommand {

    private File managementSystem;

    public CommandManageHotel(File managementSystem) {
        this.managementSystem = managementSystem;
    }

    @Override
    public boolean execute() throws IOException, ParseException {
        File[] hotels = managementSystem.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().charAt(0) != '.';
            }
        });

        if(hotels.length == 0) {
            System.out.println("There is no existing hotel in the system");
            System.out.println("You need to create one first");
            System.out.println();
            return true;
        }

        Printer.printBorder();
        System.out.println("Choose the hotel you want to manage");
        int i = 0;
        for (File hotel : hotels) {
            System.out.println("Press " + (i++) + " to manage hotel " + hotel.getName());
        }
        System.out.println("Press " + (i) + " to the main page");
        Printer.printBorder();

        Scanner sc = new Scanner(System.in);
        int hotelNum = sc.nextInt();
        while(hotelNum < 0 || hotelNum > i) {
            System.out.println("Invalid command, try again");
            hotelNum = sc.nextInt();
        }

        if (hotelNum == i)
            return true;

        Hotel hotel = new Hotel(hotels[hotelNum]);
        return DetermineOccupation.execute(hotel);
    }

    @Override
    public String getDescription() {
        return "manage hotel";
    }
}
