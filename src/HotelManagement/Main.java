package HotelManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Yingxie Gao
 * @date 10/18/19 22:30
 */
public class Main {

    public static void main(String[] args) throws IOException {
/*
        Location location = new Location("Ames");
        Hotel hotel = new Hotel("ISU HotelManagement.Hotel",location,10,10);
        System.out.println("Welcome to "+hotel.getName() +"!");*/

        /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        System.out.println(name);*/

        //Construct a hotel
        System.out.println("Welcome to ISU Hotel Management System!");
        System.out.println("If you want to construct a hotel, please enter 'construct'.");
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        if(word.equals("construct")){
            System.out.println("What is your hotel's name?");
            String name = sc.nextLine();
            System.out.println("What is your hotel's location? Enter an Address.");
            String address = sc.nextLine();
            System.out.println("How many floors do your hotel has?");
            int floor = Integer.parseInt(sc.nextLine());
            System.out.println("How many rooms do your hotel has for each floor?");
            int numRoom = Integer.parseInt(sc.nextLine());
            Hotel hotel = new Hotel(name,new Location(address),floor,numRoom);
            // save the hotel      todo
            System.out.println("Success! "+hotel.getName()+" is constructed!");
        }
        else {
            System.out.println("We can not do that.");
        }

    }
}
