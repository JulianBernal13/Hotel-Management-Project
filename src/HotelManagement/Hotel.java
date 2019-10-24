package HotelManagement;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Yingxie Gao
 * @date 10/18/19 22:30
 */
public class Hotel {
    private String name;
    private Location location;
    private HashMap<Integer,Room> rooms;
    private HashMap<Integer,Room> emptyRooms;
    private HashMap<Integer,Room> occupiedRooms;
    private ArrayList<Employee> employees;

    public Hotel(String name, Location location, HashMap<Integer,Room> rooms) {
        this.name = name;
        this.location = location;
        this.rooms = rooms;
        this.emptyRooms = new HashMap<>();
        emptyRooms.putAll(rooms);
        this.occupiedRooms = new HashMap<>();
        this.employees = new ArrayList<>();
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

    public HashMap<Integer, Room> getRooms() {
        return rooms;
    }

    public void setRooms(HashMap<Integer, Room> rooms) {
        this.rooms = rooms;
    }

    public HashMap<Integer, Room> getEmptyRooms() {
        return emptyRooms;
    }

    public void setEmptyRooms(HashMap<Integer, Room> emptyRooms) {
        this.emptyRooms = emptyRooms;
    }

    public HashMap<Integer, Room> getOccupiedRooms() {
        return occupiedRooms;
    }

    public void setOccupiedRooms(HashMap<Integer, Room> occupiedRooms) {
        this.occupiedRooms = occupiedRooms;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
}
