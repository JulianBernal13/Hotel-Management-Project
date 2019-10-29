package HotelManagement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author Yingxie Gao
 * @date 10/18/19 22:40
 */
public class Room {
    private int number;
    private double price;
    private boolean isEmpty;
    private boolean isClean;
    private String path; 
    private String maintaince;
    private String notes;
    private ArrayList<Contract> contractList;
    
    public static enum RoomProperty {
        number, price, isEmpty, IsClean, maintaince, notes
    }

    public Room(int number, String path) {
        this.number = number;
        this.path = path;
        this.isClean = true;
        this.isEmpty = true;
        this.price = 100;
        this.maintaince = "none";
        this.notes = "none";
        ArrayList<Contract> contractList = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isClean() {
        return isClean;
    }

    public void setClean(boolean clean) {
        isClean = clean;
    }

    public ArrayList<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(ArrayList<Contract> contractList) {
        this.contractList = contractList;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
    
    public String Maintaince() {
        return maintaince;
    }

    public void setMaintaince(String maintaince) {
        this.maintaince = maintaince;
    }
    
    public String Notes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Room{" +
                "number=" + number +
                ", price=" + price +
                ", isEmpty=" + isEmpty +
                ", isClean=" + isClean +
                ", maintaince=" + maintaince +
                ", notes" + notes + 
                '}';
    }

    public void createRoomFile() throws IOException {
        File cur = new File(this.path + File.separator + this.number + ".txt");
        if(cur.createNewFile()) {
            PrintWriter writer = new PrintWriter(cur);
            writer.println(number);
            writer.println(String.valueOf(price));
            writer.println(String.valueOf(isEmpty));
            writer.println(String.valueOf(isClean));
            writer.println(String.valueOf(maintaince));
            writer.println(String.valueOf(notes));
            writer.flush();
            writer.close();
        }
    }
}
