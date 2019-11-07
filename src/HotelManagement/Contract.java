package HotelManagement;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Anji Yu
 */

public class Contract {
    private String start;
    private String end;
    private Customer customer;
    private Room room;
    private String path;

    public Contract(String start, String end, Customer customer, Room room,String path) {
        this.start = start;
        this.end = end;
        this.customer = customer;
        this.room = room;
        this.path = path;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void createContractFile() throws IOException {
        File cur = new File(this.path);
        if(cur.createNewFile()) {
            PrintWriter writer = new PrintWriter(cur);
            writer.println(start);
            writer.println(end);
            writer.println(customer.getFirstname()+" "+customer.getLastname());
            writer.println(room.getNumber());
            writer.flush();
            writer.close();
        }
    }

    public void writeToFile() throws IOException {
        File cur = new File(this.path);
        cur.delete();
        createContractFile();
    }
}
