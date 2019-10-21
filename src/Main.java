import java.util.HashMap;

/**
 * @author Yingxie Gao
 * @date 10/18/19 22:30
 */
public class Main {

    public static void main(String[] args) {
        //construct a hotel
        Room room101 = new Room(101);
        Room room102 = new Room(102);
        Room room103 = new Room(103);
        HashMap<Integer,Room> rooms = new HashMap<>();
        rooms.put(101,room101);
        rooms.put(102,room102);
        rooms.put(103,room103);
        Location location = new Location("Ames");
        Hotel hotel = new Hotel("ISU Hotel",location,rooms);

        System.out.println("Welcome to "+hotel.getName() +"!");
    }
}
