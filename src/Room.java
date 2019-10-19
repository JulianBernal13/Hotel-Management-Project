/**
 * @author Yingxie Gao
 * @date 10/18/19 22:40
 */
public class Room {
    private int number;
    private boolean isEmpty;

    public Room(int number) {
        this.number = number;
        this.isEmpty = true;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
}
