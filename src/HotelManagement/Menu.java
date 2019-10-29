package HotelManagement;

import java.io.*;
import java.util.Scanner;

public class Menu {

    public static enum HotelProperty {
        name, location, numOfLevel, levelRmNum, password;
    }


    public void menu() throws IOException {
        Printer.printWelcome();

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        switch(command) {
            case "new": {
                createHotel();
                menu();
            }
            case "manage": {
                manageHotel();
                menu();
            }
            case "exit" :
                break;
            default: {
                System.out.println("Invalid command, please enter another command");
                menu();
            }
        }
    }

    public void createHotel() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your hotel's name?");
        String name = sc.nextLine();
        String path = "." + File.separator + "ManagementSystem";
        File tmp = new File(path);
        tmp.mkdir();
        path += File.separator + name;
        File hotelFile = new File(path);
        while(!hotelFile.mkdir()) {
            System.out.println("This name is taken, please enter another name");
            name = sc.nextLine();
            hotelFile = new File(path);
        }
        System.out.println("What is your hotel's location? Enter an Address.");
        String address = sc.nextLine();
        System.out.println("How many floors do your hotel has?");
        int floor = Integer.parseInt(sc.nextLine());
        System.out.println("How many rooms do your hotel has for each floor?");
        int numRoom = Integer.parseInt(sc.nextLine());
        System.out.println("Please set a password for this hotel.");
        String password = sc.nextLine();

        Hotel hotel = new Hotel(path, name, new Location(address), floor, numRoom, password);
    }

    private void manageHotel() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        String path = "." + File.separator + "ManagementSystem";
        System.out.println("Which Hotel?");
        File tmp = new File(path);
        for (File file : tmp.listFiles()) {
            System.out.println(file.getName());
        }
        System.out.println("Please enter hotel name");
        String name = sc.nextLine();
        File cur = new File(path + File.separator + name);
        while(!cur.exists()) {
            System.out.println("Hotel does not exist. Please enter another one");
            for (File file : tmp.listFiles()) {
                System.out.println(file.getName());
            }
            name = sc.nextLine();
            cur = new File(path + File.separator + name);
        }
//        path += File.separator + name;
        System.out.println("Choose your occupation number");
        System.out.println("1. Manager");
        System.out.println("2. Counter");

        int occu = sc.nextInt();
        switch(occu) {
            case 1: managedByManager(cur);
            case 2: managedByCounter(cur);
            default: break;
        }
    }

    private void managedByCounter(File cur) {

    }

    private void managedByManager(File cur) throws FileNotFoundException {
        String path = cur.getPath() + File.separator + "info.txt";
        File tmp = new File(path);
        if(tmp.exists()) {
            System.out.println("Please enter hotel password");
            System.out.println(tmp.getPath());
//            Hotel.HotelProperty property = Hotel.HotelProperty;
            String password = FileReader.getHotelInfo(tmp, Hotel.HotelProperty.password);
            System.out.println("Here is a hint " + password);
            Scanner sc = new Scanner(System.in);
            if(!sc.nextLine().equals(password)) {
                System.out.println("You are not a manager!");
                return;
            }
            System.out.println("Successfully log in");
        }
    }

    private File registerCustomer(File cur) {
        boolean found = false;
        System.out.println("Please enter customer's name:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        for(File tmp : cur.listFiles()) {
            if (tmp.getName() == name) {
                System.out.println("This customer has already existed");
                return tmp;
            }
        }
        return createCustomer(name, cur);
    }

    private File lookUpCustomer(File cur) {
        System.out.println("Please enter customer's name:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        for(File tmp : cur.listFiles()) {
            if(tmp.getName() == name) {
                return tmp;
            }
        }
        System.out.println("Customer does not exists");
        return null;
    }

    private void editCustomer(File customer) {
        System.out.println("What would you like to do to with " + customer.getName());
        Printer.printEditCustomer();

    }

    private File createCustomer(String name, File cur) {
        return null;
    }

}
