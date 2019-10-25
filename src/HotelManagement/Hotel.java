package HotelManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Yingxie Gao
 * @date 10/18/19 22:30
 */
public class Hotel {
    private String name;
    private Location location;
    private int numOfLevel;
    private int levelRmNum;
    private Room rooms[][];
    private ArrayList<Employee> employees;

    public Hotel(String name,Location location, int numOfLevel, int levelRmNum) {
        this.name = name;
        this.location = location;
        this.numOfLevel = numOfLevel;
        this.levelRmNum = levelRmNum;
        rooms = new Room[numOfLevel][levelRmNum];
        for(int i = 0; i < numOfLevel; i++) {
            for(int j = 0; j < levelRmNum; j++) {
                rooms[i][j] = new Room(i* 100 + j);
            }
        }
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

//    public HashMap<Integer, Room> getRooms() {
//        return rooms;
//    }
//
//    public void setRooms(HashMap<Integer, Room> rooms) {
//        this.rooms = rooms;
//    }

//    public HashMap<Integer, Room> getEmptyRooms() {
//        return emptyRooms;
//    }
//
//    public void setEmptyRooms(HashMap<Integer, Room> emptyRooms) {
//        this.emptyRooms = emptyRooms;
//    }
//
//    public HashMap<Integer, Room> getOccupiedRooms() {
//        return occupiedRooms;
//    }
//
//    public void setOccupiedRooms(HashMap<Integer, Room> occupiedRooms) {
//        this.occupiedRooms = occupiedRooms;
//    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
}
