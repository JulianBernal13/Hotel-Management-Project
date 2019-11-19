package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static HotelManagement.Main.current;
import static HotelManagement.Main.sdf;

/**
 * @author Yingxie Gao, Anji Yu
 * @date 11/7/19 08:11
 */
public class ContractFileController {

    public static void reserveRoom(Hotel hotel) throws IOException {
        Scanner sc = new Scanner(System.in);
        Customer customer = CustomerFileController.enterCustomer(hotel);

        System.out.println("What type of room do you want?((single,double,triple,queen,king))");
        String type = sc.nextLine();
        System.out.println("When do you want to start?(how many days later)");
        int startDay = Integer.parseInt(sc.nextLine());
        while (startDay<0){
            System.out.println("Invalid input, please try again.");
            startDay = Integer.parseInt(sc.nextLine());
        }
        Calendar tempCalendar = new GregorianCalendar();
        tempCalendar.setTime(current);
        tempCalendar.add(Calendar.DATE, startDay);
        String start = sdf.format(tempCalendar.getTime());

        System.out.println("How long would you like to stay?");
        int period = Integer.parseInt(sc.nextLine());
        while (period<=0){
            System.out.println("Invalid input, please try again.");
            period = Integer.parseInt(sc.nextLine());
        }
        tempCalendar.add(Calendar.DATE, period);
        String end = sdf.format(tempCalendar.getTime());

        ArrayList<Integer> roomList = showAvailableRoom(type,hotel,start,end);
        if (roomList.size() > 0) {
            System.out.println(roomList.toString());
            System.out.println("Which room are you going to reserve?");
            int roomNum = Integer.parseInt(sc.nextLine());
            while(!roomList.contains(roomNum)){
                System.out.println("This room can not be selected. Try another room again.");
                roomNum = Integer.parseInt(sc.nextLine());
            }
            Room room = hotel.getRoom(roomNum);
            while (!room.getType().equals(type)) {
                System.out.println("This room cannot be reserved, enter another room \n");
                room = hotel.getRoom(sc.nextLine());
            }

            double price = getPrice(hotel, period, room, customer);
            System.out.println("The total price would be "+price);
            System.out.println("Are you sure to reserve this room?(y/n)");
            String sure = sc.nextLine();
            switch (sure) {
                case "y": {
                    String dir = hotel.getPath() + File.separator + "Contracts" + File.separator + "Reservation";
                    String path = dir + File.separator + customer.getFirstname() + " " + customer.getLastname() + ".txt";
                    Contract contract = new Contract(start, end, customer, room, path, price);
                    contract.writeToFile();
                    System.out.println("Success! The reservation has been completed!");
                    hotel.addReservationContract(contract);
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
                File contractFile = new File(hotel.getPath() + File.separator + "Contracts" +
                        File.separator + "Reservation" + File.separator + customer.getFirstname() + " " +
                        customer.getLastname() + ".txt");
                if (!contractFile.exists() || !readContract(hotel,contractFile).getStart().equals(sdf.format(current))) {
                    System.out.println("This customer does not have a reservation today.");
                    break;
                }
                File in = new File(hotel.getPath() + File.separator + "Contracts" + File.separator + "In");
                Contract contract = readContract(hotel,contractFile);
                contractFile.renameTo(new File(hotel.getPath() + File.separator + "Contracts" + File.separator + "In" + File.separator + contractFile.getName()));
                System.out.println("Success! Now the customer has been checked in.");
                Room room = contract.getRoom();
                room.setEmpty(false);
                room.writeToFile();
                customer.setStaying(true);
                customer.writeToFile();
                hotel.addInContract(contract);
                hotel.getReservationContracts().remove(contract.getCustomer());
                break;
            }
            case "n": {
                System.out.println("What type of room do you want?((single,double,triple,queen,king))");
                String type = sc.nextLine();
                Calendar tempCalendar = new GregorianCalendar();
                tempCalendar.setTime(current);
                String start = sdf.format(tempCalendar.getTime());

                System.out.println("How long would you like to have this room?");
                int period = Integer.parseInt(sc.nextLine());
                while (period<=0){
                    System.out.println("Invalid input, please try again.");
                    period = Integer.parseInt(sc.nextLine());
                }
                tempCalendar.add(Calendar.DATE, period);
                String end = sdf.format(tempCalendar.getTime());

                ArrayList<Integer> roomList = showAvailableRoom(type,hotel,start,end);
                if (roomList.size() > 0) {
                    System.out.println(roomList.toString());
                    System.out.println("Which room are you going to check-in?");
                    int roomNum = Integer.parseInt(sc.nextLine());
                    while(!roomList.contains(roomNum)){
                        System.out.println("This room can not be selected. Try another room again.");
                        roomNum = Integer.parseInt(sc.nextLine());
                    }
                    Room room = hotel.getRoom(roomNum);
                    while (!room.isEmpty() || !room.isClean() || !room.getType().equals(type)) {
                        System.out.println("This room cannot be checked-in, enter another room \n");
                        room = hotel.getRoom(sc.nextLine());
                    }
                    if(!customer.isVIP()) {
                        System.out.println("Would you like to be our VIP member? y/n");
                        if(sc.nextLine().equals("y")) {
                            customer.setVIP(true);
                            customer.writeToFile();
                            System.out.println("Congratulations, you are now our VIP member");
                        }
                    }

                    double price = getPrice(hotel, period, room, customer);
                    System.out.println("The total price would be "+price);
                    System.out.println("Are you sure to check this room in?(y/n)");
                    String sure = sc.nextLine();
                    switch (sure) {
                        case "y": {
                            String dir = hotel.getPath() + File.separator + "Contracts" + File.separator + "In";
                            String path = dir + File.separator + customer.getFirstname() + " " + customer.getLastname() + ".txt";
                            Contract contract = new Contract(start, end, customer, room, path, price);
                            contract.writeToFile();

                            room.setEmpty(false);
                            System.out.println();
                            room.writeToFile();
                            customer.setStaying(true);
                            customer.writeToFile();
                            System.out.println("Success! Now the customer has been checked-in!");
                            hotel.addInContract(contract);
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

    public static void checkOut(Hotel hotel) throws IOException {
        File dir = new File(hotel.getPath() + File.separator + "Contracts" + File.separator + "In");
        int count = 0;
        for (File file : dir.listFiles()) {
            Contract c = readContract(hotel,file);
            if(c.getEnd().equals(sdf.format(current))) {
                System.out.println(file.getName().substring(0, file.getName().length() - 4));
                count++;
            }
        }
        if(count>0) {
            System.out.println("Enter the customer's name to check-out");
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();
            File file = new File(dir + File.separator + name + ".txt");
            if (!file.exists()) {
                System.out.println("No such customer had checked-in");
                return;
            }
            Contract contract = readContract(hotel, file);
            int roomNum = contract.getRoom().getNumber();
            File filee = new File(hotel.getPath() + File.separator + "Contracts" + File.separator + "Out" + File.separator + file.getName());
            int frequency = 1;
            while(filee.exists()){
                filee = new File(hotel.getPath() + File.separator + "Contracts" + File.separator + "Out"+ File.separator+ frequency + file.getName());
                frequency++;
            }
            file.renameTo(filee);
            Room room = contract.getRoom();
            Customer customer = contract.getCustomer();
            System.out.println("Check-out Complete!");
            room.setEmpty(true);
            room.writeToFile();
            customer.setStaying(false);
            customer.writeToFile();
            hotel.addOutContract(contract);
            hotel.getInContracts().remove(customer);
        }
        else
        {
            System.out.println("There is no room needs being checked-out.");
        }
    }

    public static void makeNotification(Hotel hotel){
        int inCount=0;
        int outCount=0;
        HashMap<Customer,Contract> reservationContracts = hotel.getReservationContracts();
        Iterator<Map.Entry<Customer,Contract>> iter = reservationContracts.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<Customer, Contract> entry = iter.next();
            if(entry.getValue().getStart().equals(sdf.format(current))){
                inCount++;
            }
            if(entry.getValue().getStart().compareTo(sdf.format(current))<0){
                iter.remove();
                File file = new File(entry.getValue().getPath());
                file.delete();
            }
        }
        HashMap<Customer,Contract> inContracts = hotel.getInContracts();
        for (Contract c: inContracts.values()) {
            if(c.getEnd().equals(sdf.format(current))){
                outCount++;
            }
        }
        System.out.println("There will be "+inCount+" customer to check-in today");
        System.out.println("There will be "+outCount+" customer to check-out today");
    }

    public static Contract readContract(Hotel hotel,File file) throws FileNotFoundException {
//        Scanner sc = new Scanner(file);
//        String start = sc.nextLine();
//        String end = sc.nextLine();
//        Customer customer = hotel.getCustomer(sc.nextLine());
//        Room room = hotel.getRoom(sc.nextLine());
//        double price = Double.parseDouble(sc.nextLine());
//        String path = file.getPath();
//        Contract contract = new Contract(start,end,customer,room,path,price);
//        return contract;
        return new Contract(file, hotel);
    }

    public static double getPrice(Hotel hotel, int period, double price, Customer c) {
        double totalPrice = period * price;
        if(c.isVIP())
            totalPrice *= hotel.getVIPDiscount();
        return totalPrice;
    }

    public static double getPrice(Hotel hotel, int period, Room r, Customer c) {
        return getPrice(hotel, period, r.getPrice(), c);
    }

    public static void priceMatch(Hotel hotel, Contract contract) throws ParseException, IOException {
        Customer customer = contract.getCustomer();
        Room room = contract.getRoom();
        Date start = getDate(contract.getStart());
        Date end = getDate(contract.getEnd());
        int period = getDifferenceDays(start, end);
        contract.setPrice(getPrice(hotel, period, room, customer));
        contract.setVIPContract(customer.isVIP());
        contract.writeToFile();
    }

    public static void changeRoom(Hotel hotel, Contract contract, Room room) throws IOException {
        contract.setRoom(room);
        contract.writeToFile();
    }

    public static Date getDate(String date) throws ParseException {
        return sdf.parse(date);
    }

    public static int getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static ArrayList<Integer> showAvailableRoom(String type, Hotel hotel,String start, String end) {

        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < hotel.getNumOfLevel(); ++i) {
            for(int j = 0; j < hotel.getLevelRmNum(); ++j) {
                Room cur = hotel.getRoom(i,j);
                if(cur.getType().equals(type)) {
                    list.add(cur.getNumber());

                }
            }
        }
        HashMap<Customer,Contract> reservationContracts = hotel.getReservationContracts();
        Iterator<Map.Entry<Customer,Contract>> iter = reservationContracts.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<Customer, Contract> entry = iter.next();
            String contractStartDay = entry.getValue().getStart();
            String contractEndDay = entry.getValue().getEnd();
            if((end.compareTo(contractStartDay)>0&&end.compareTo(contractEndDay)<=0) || (start.compareTo(contractStartDay)>=0&&start.compareTo(contractEndDay)<0) || (start.compareTo(contractStartDay)<=0&&end.compareTo(contractEndDay)>=0)){
                Iterator<Integer> iterList = list.iterator();
                while (iterList.hasNext()) {
                    if (iterList.next()==entry.getValue().getRoom().getNumber()) {
                        iterList.remove();
                    }
                }
            }
        }

        HashMap<Customer,Contract> inContracts = hotel.getInContracts();
        for (Contract c: inContracts.values()) {
            String contractStartDay = c.getStart();
            String contractEndDay = c.getEnd();
            if((end.compareTo(contractStartDay)>0&&end.compareTo(contractEndDay)<=0) || (start.compareTo(contractStartDay)>=0&&start.compareTo(contractEndDay)<0) || (start.compareTo(contractStartDay)<=0&&end.compareTo(contractEndDay)>=0)){
                Iterator<Integer> iterList = list.iterator();
                while (iterList.hasNext()) {
                    if (iterList.next()==c.getRoom().getNumber()) {
                        iterList.remove();
                    }
                }
            }
        }
        return list;
    }

    public static void deleteContract(Hotel hotel) throws FileNotFoundException {
        System.out.println("Which contract are you going to delete?");
        File contractFile = new File(hotel.getPath() + File.separator + "Contracts" +
                File.separator + "Reservation");
        File[] fs = contractFile.listFiles();
        for(File f:fs){
            System.out.println(f.getName().substring(0,f.getName().length()-4));
        }
        Scanner sc = new Scanner(System.in);
        String contractName = sc.nextLine();
        File contractLocation = new File(hotel.getPath() + File.separator + "Contracts" +
                File.separator + "Reservation"+File.separator+contractName+".txt");
        Contract contract = readContract(hotel,contractLocation);
        hotel.getReservationContracts().remove(contract.getCustomer());
        File file = new File(contract.getPath());
        file.delete();
    }

    public static void priceMatchMenu(Hotel hotel) throws IOException, ParseException {
        System.out.println("Please select the contract you would like to have price match");
        Printer.printRRContract(hotel);
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        Customer customer = hotel.getCustomer(name);
        if(!hotel.getReservationContracts().containsKey(customer)){
            System.out.println("Invalid contract");
            return;
        }
        priceMatch(hotel, hotel.getReservationContracts().get(customer));
        System.out.println("Success!");
    }

}
