package HotelManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * @author Yingxie Gao
 * @date 10/18/19 22:30
 */
public class Main {

    public static void main(String[] args) throws IOException {
//        test construct a hotel
        Location location = new Location("Ames");
        Hotel hotel = new Hotel("ISU HotelManagement.Hotel",location,10,10);
        System.out.println("Welcome to "+hotel.getName() +"!");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        System.out.println(name);
    }
}
