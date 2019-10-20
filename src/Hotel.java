import java.util.HashMap;

/**
 * @author Yingxie Gao
 * @date 10/18/19 22:30
 */
public class Hotel {
    private String name;
    private Location location;
    private HashMap<String,Room> rooms;
    private HashMap<String,Room> emptyRooms;
    private HashMap<String,Room> occupiedRooms;

    public Hotel(String name, Location location, HashMap<String,Room> rooms) {
        this.name = name;
        this.location = location;
        this.rooms = rooms;
        this.emptyRooms.putAll(rooms);
        this.occupiedRooms = new HashMap<String, Room>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public HashMap<String, Room> getRooms() {
        return rooms;
    }

    public void setRooms(HashMap<String, Room> rooms) {
        this.rooms = rooms;
    }

    public HashMap<String, Room> getEmptyRooms() {
        return emptyRooms;
    }

    public void setEmptyRooms(HashMap<String, Room> emptyRooms) {
        this.emptyRooms = emptyRooms;
    }

    public HashMap<String, Room> getOccupiedRooms() {
        return occupiedRooms;
    }

    public void setOccupiedRooms(HashMap<String, Room> occupiedRooms) {
        this.occupiedRooms = occupiedRooms;
    }
}
