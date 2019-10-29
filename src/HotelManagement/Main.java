package HotelManagement;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Yingxie Gao
 * @date 10/18/19 22:30
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Menu m = new Menu();
        m.menu();
//        System.out.println("Welcome to ISU Hotel Management System!\n" +
//        "Enter 'new' to start a new hotel.\n"+
//        "Enter 'manage' to manage current hotels.\n");
//        Scanner sc = new Scanner(System.in);
//        String word = sc.nextLine();
//        //construct a hotel
//        if(word.equals("new")){
//            System.out.println("What is your hotel's name?");
//            String name = sc.nextLine();
//            System.out.println("What is your hotel's location? Enter an Address.");
//            String address = sc.nextLine();
//            System.out.println("How many floors do your hotel has?");
//            int floor = Integer.parseInt(sc.nextLine());
//            System.out.println("How many rooms do your hotel has for each floor?");
//            int numRoom = Integer.parseInt(sc.nextLine());
//            System.out.println("Please set a password for this hotel.");
//            String password = sc.nextLine();
//            Hotel hotel = new Hotel(name,new Location(address),floor,numRoom,password);
//            // save the hotel
//            String home = System.getProperty("user.home");
//            String sep = File.separator;
//            System.out.println(home);
//            File f = new File(home + sep + "ManagementSystem" + sep + name + ".txt");
////            f.createNewFile();
////            File f = new File("./ManagementSystem/"+name+".txt");
////            File(FileNameUtils.normalize(home + "/Desktop/Testing/Java.txt"))
//            String path = home + File.separator + "ManagementSystem" + File.separator + name + ".txt";
//            PrintWriter out = new PrintWriter(path);
//            out.println(name);
//            out.println(address);
//            out.println(password);
//            out.println("Rooms:");
//            for (int i = 0; i <floor ; i++) {
//                for (int j = 0; j < numRoom; j++) {
//                    out.println(hotel.getRooms()[i][j].toString());
//                }
//                out.println();
//            }
//            out.println("Employees:"+" \n");
//            try {
//                for (int i = 0; i < hotel.getEmployees().size(); i++) {
//                    out.write(hotel.getEmployees().get(i).toString() + " \n");
//                }
//            }
//            catch (NullPointerException e){
//                out.write("There is no employee.");
//            }
//            out.flush();
//            out.close();
//            System.out.println("Success! "+hotel.getName()+" has been constructed!");
//        }
//        else if(word.equals("manage")){
//            //go to local file to check which hotels are already constructed
//            System.out.println("which hotel? " + "Please Enter the hotel name.");
//            String path = "./ManagementSystem/";
//            File file = new File(path);
//            File[] array = file.listFiles();
//            for (int i = 0; i <array.length ; i++) {
//                System.out.println(array[i].getName());
//            }
//            String name = sc.nextLine();
//            String currentHotelPath = "./ManagementSystem/"+name+".txt";
//
//            //login
//            System.out.println("If you are the manager please enter the password, else enter 'employee'");
//            File currentHotel = new File(currentHotelPath);
//            Scanner hotelSc = new Scanner(currentHotel);
//            String currentHotelName = hotelSc.nextLine();
//            String currentHotelAddress = hotelSc.nextLine();
//            String currentPassword = hotelSc.nextLine();
//            //manager can do
//            if(currentPassword.equals(sc.nextLine())) {
//                System.out.println("Welcome Manager!");
//                System.out.println("Enter 'checkin' to check-in");
//                System.out.println("Enter 'checkout' to check-out");
//                System.out.println("Enter 'hire' to add employee");
//                System.out.println("Enter 'edit employee' to edit employee");
//                System.out.println("Enter 'delete' to delte the hotel");
//                System.out.println("Enter 'edit password' to reset the password");
//                String word2 = sc.nextLine();
//                if (word2.equals("delete")) {
//                    currentHotel.delete();
//                }
//                else if(word2.equals("edit password")){
//                    String newPassword = sc.nextLine();
//                    //write the new password into the local file   todo
//                }
//            }
//            //employee can do
//            else {
//                System.out.println("Welcome Employee!");
//                System.out.println("Enter 'checkin' to check-in");
//                System.out.println("Enter 'checkout' to check-out");
//            }
//        }
//        else {
//            System.out.println("We can not do that.");
//        }

    }
}
