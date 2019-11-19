package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Yingxie Gao
 */

public class Contract {
    private String start;
    private String end;
    private Customer customer;
    private Room room;
    private double price;
    private String path;
    private boolean isVIPContract;

    public Contract(String start, String end, Customer customer, Room room, String path, double price) {
        this.isVIPContract = customer.isVIP();
        this.start = start;
        this.end = end;
        this.customer = customer;
        this.room = room;
        this.path = path;
        this.price = price;
    }

    public Contract(File file, Hotel hotel) throws FileNotFoundException {
        this.path = file.getPath();
        Scanner sc = new Scanner(file);
        this.start = sc.nextLine();
        this.end = sc.nextLine();
        this.customer = hotel.getCustomer(sc.nextLine());
        this.room = hotel.getRoom(sc.nextLine());
        this.price = Double.parseDouble(sc.nextLine());
        this.isVIPContract = Boolean.parseBoolean(sc.nextLine());
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isVIPContract() {
        return isVIPContract;
    }

    public void setVIPContract(boolean VIPContract) {
        isVIPContract = VIPContract;
    }

    public void createContractFile() throws IOException {
        File cur = new File(this.path);
        if(cur.createNewFile()) {
            PrintWriter writer = new PrintWriter(cur);
            writer.println(start);
            writer.println(end);
            writer.println(customer.toString());
            writer.println(room.getNumber());
            writer.println(price);
            writer.println(isVIPContract);
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
