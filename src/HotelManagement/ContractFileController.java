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
        Customer customer = CustomerFileController.enterCustomer(hotel);

        System.out.println("What type of room do you want?((single,double,triple,queen,king))");
        String type = sc.nextLine();
        if (hotel.showTypeRoom(type) > 0) {
            System.out.println("Which room are you going to reserve?");
            Room room = hotel.getRoom(sc.nextLine());
            while (!room.getType().equals(type)) {
                System.out.println("This room cannot be reserved, enter another room \n");
                room = hotel.getRoom(sc.nextLine());
            }

            System.out.println("When do you want to start?(how many days later)");
            int startDay = Integer.parseInt(sc.nextLine());
            Calendar tempCalendar = new GregorianCalendar();
            tempCalendar.setTime(current);
            tempCalendar.add(Calendar.DATE, startDay);
            String start = sdf.format(tempCalendar.getTime());

            System.out.println("How long would you like to have this room?");
            int period = Integer.parseInt(sc.nextLine());
            double price = period*room.getPrice();
            System.out.println("The total price would be "+price);
            System.out.println("Are you sure to reserve this room?(y/n)");
            String sure = sc.nextLine();
            switch (sure) {
                case "y": {
                    tempCalendar.add(Calendar.DATE, period);
                    String end = sdf.format(tempCalendar.getTime());

                    String dir = hotel.getPath() + File.separator + "Contracts" + File.separator + "Reservation";
                    File file = new File(dir);
                    file.mkdirs();
                    String path = dir + File.separator + customer.getFirstname() + " " + customer.getLastname() + ".txt";
                    Contract contract = new Contract(start, end, customer, room, path, price);
                    contract.writeToFile();
                    System.out.println("Success! The reservation has been completed!");
                }
                case "n": {
                    break;
                }
            }
        } else {
            System.out.println("Sorry, we do not have such room available.");
        }
    }

    public static void checkIn(Hotel hotel) throws IOException {
        Scanner sc = new Scanner(System.in);
        Customer customer = CustomerFileController.enterCustomer(hotel);
        System.out.println("Do you have a reservation before?(y/n)");
        String check = sc.nextLine();
        switch (check) {
            case "y": {
                File contract = new File(hotel.getPath() + File.separator + "Contracts" + File.separator + "Reservation" + File.separator + customer.getFirstname() + " " + customer.getLastname() + ".txt");
                if (!contract.exists()) {
                    System.out.println("This customer does not have a reservation.");
                    break;
                }
                File in = new File(hotel.getPath() + File.separator + "Contracts" + File.separator + "In");
                in.mkdir();
                contract.renameTo(new File(hotel.getPath() + File.separator + "Contracts" + File.separator + "In" + File.separator + contract.getName()));
                System.out.println("Success! Now the customer has been checked in.");
                break;
            }
            case "n": {
                System.out.println("What type of room do you want?((single,double,triple,queen,king))");
                String type = sc.nextLine();
                if (RoomFileController.showTypeRoomEmpty(hotel.getPath()+File.separator+"Rooms",type) > 0) {
                    System.out.println("Which room are you going to reserve?");
                    int roomNum = Integer.parseInt(sc.nextLine());
                    Room room = hotel.getRoom(roomNum);
                    while (!room.isEmpty() || !room.isClean() || !room.getType().equals(type)) {
                        System.out.println("This room cannot be checked-in, enter another room \n");
                        room = hotel.getRoom(sc.nextLine());
                    }
                    Calendar tempCalendar = new GregorianCalendar();
                    tempCalendar.setTime(current);
                    String start = sdf.format(tempCalendar.getTime());

                    System.out.println("How long would you like to have this room?");
                    int period = Integer.parseInt(sc.nextLine());
                    double price = period*room.getPrice();
                    System.out.println("The total price would be "+price);
                    System.out.println("Are you sure to check this room in?(y/n)");
                    String sure = sc.nextLine();
                    switch (sure) {
                        case "y": {
                            tempCalendar.add(Calendar.DATE, period);
                            String end = sdf.format(tempCalendar.getTime());
                            String dir = hotel.getPath() + File.separator + "Contracts" + File.separator + "In";
                            File file = new File(dir);
                            file.mkdirs();
                            String path = dir + File.separator + customer.getFirstname() + " " + customer.getLastname() + ".txt";
                            Contract contract = new Contract(start, end, customer, room, path, price);
                            contract.writeToFile();
                            RoomFileController.changeRoomOccupied(hotel.getPath()+File.separator+"Rooms", contract.getRoom().getNumber());
                            System.out.println("Success! Now the customer has been checked-in!");
                            break;
                        }
                        case "n": {
                            break;
                        }
                    }

                } else {
                    System.out.println("Sorry, we do not have such room available.");
                    break;
                }
            }
        }
    }

    public static void checkOut(Hotel hotel) throws FileNotFoundException {
        File dir = new File(hotel.getPath() + File.separator + "Contracts" + File.separator + "In");
        for (File file : dir.listFiles()) {
            System.out.println(file.getName().substring(0,file.getName().length()-4));
        }
        System.out.println("Enter the customer's name to check-out");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        File file = new File(dir+File.separator+name+".txt");
        while(!file.exists()){
            System.out.println("No such customer had checked-in");
            name = sc.nextLine();
            file = new File(dir+File.separator+name+".txt");
        }
        Contract contract = readContract(hotel,file);
        int roomNum =contract.getRoom().getNumber();
        File out = new File(hotel.getPath() + File.separator + "Contracts" + File.separator + "Out");
        out.mkdir();
        file.renameTo(new File(hotel.getPath() + File.separator + "Contracts" + File.separator + "Out" + File.separator + file.getName()));

        RoomFileController.changeRoomEmpty(hotel.getPath()+File.separator+"Rooms", roomNum);
        System.out.println("Check-out Complete!");

    }

    public static Contract readContract(Hotel hotel,File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String start = sc.nextLine();
        String end = sc.nextLine();
        Customer customer = hotel.getCustomer(sc.nextLine());
        Room room = hotel.getRoom(sc.nextLine());
        double price = Double.parseDouble(sc.nextLine());
        String path = file.getPath();
        Contract contract = new Contract(start,end,customer,room,path,price);
        return contract;
    }
}
