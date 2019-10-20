import java.util.ArrayList;

/**
 * @author Yingxie Gao
 * @date 10/18/19 22:40
 */
public class Room {
    private int number;
    private boolean isClean;
    private ArrayList<Contract> contractList;

    public Room(int number) {
        this.number = number;
        this.isClean = true;
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
}
