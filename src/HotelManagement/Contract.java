package HotelManagement;

/**
 * @author Anji Yu
 */

public class Contract {
    private int start;
    private int end;
    private Customer customer;

    public Contract(int start, int end, Customer customer) {
        this.start = start;
        this.end = end;
        this.customer = customer;
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

    boolean isLess(Contract other) {
        return this.end <= other.getStart();
    }

    boolean isGreater(Contract other) {
        return this.start >= other.getEnd();
    }


}
