package HotelManagement;

/**
 * @author Anji Yu
 */

public class Contract {
    private int start;
    private int end;
    private Customer customer;
    private Room room;

    public Contract(int start, int end, Customer customer, Room room) {
        this.start = start;
        this.end = end;
        this.customer = customer;
        this.room = room;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    boolean isLessThan(Contract other) {
        return this.end <= other.getStart();
    }

    boolean isGreaterThan(Contract other) {
        return this.start >= other.getEnd();
    }


}
