package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import static HotelManagement.Main.current;
import static HotelManagement.Main.sdf;

/**
 * @author Yingxie Gao
 * @date 11/7/19 08:11
 */
public class ContractFileController {

    public static void reserveRoom(Hotel hotel) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer's name");
        String name = sc.nextLine();
        Customer customer = hotel.getCustomer(name);
        if (customer == null) {
            hotel.addCustomer(name);
            customer = hotel.getCustomer(name);
        }
        if (customer.isStaying()) {
            System.out.println("This customer is already checked in");
            return;
        }
        if (customer.isVIP()) {
            System.out.println("Welcome VIP member!");
        }

        System.out.println("What type of room do you want?((single,double,triple,queen,king))");
        String type = sc.nextLine();
        if (hotel.showTypeRoom(type) > 0) {
            System.out.println("Which room are you going to reserve?");
            Room room = hotel.getRoom(sc.nextLine());
            System.out.println(room.getNumber());
            while (!room.getType().equals(type)) {
                System.out.println("This room cannot be reserved, enter another room \n");
                room = hotel.getRoom(sc.nextLine());
            }

            System.out.println("When do you want to start?(how many days later)");
            int startDay = Integer.parseInt(sc.nextLine());
            Calendar tempCalendar = new GregorianCalendar();
            tempCalendar.setTime(current);
            tempCalendar.add(Calendar.DATE,startDay);
            String start = sdf.format(tempCalendar.getTime());

            System.out.println("How long would you like to have this room?");
            int period = Integer.parseInt(sc.nextLine());
            tempCalendar.add(Calendar.DATE, period);
            String end = sdf.format(tempCalendar.getTime());

            String dir = hotel.getPath() + File.separator + "Contracts" + File.separator + "Reservation";
            File file = new File(dir);
            file.mkdirs();
            String path = dir + File.separator + room.getNumber() + " "+customer.getLastname()+".txt";
            Contract contract = new Contract(start, end, customer, room, path);
            contract.writeToFile();
            System.out.println("Success! The reservation has been completed!");
        }
        else{
            System.out.println("Sorry, we do not have such room available.");
        }
    }
}
