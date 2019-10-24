package HotelManagement;

import java.util.ArrayList;

/**
 * @author Anji Yu
 */


public class Customer {
    private String firstName;
    private String lastName;
    private ArrayList<Contract> contracts;

    public Customer(String firstName, String lastName, ArrayList<Contract> contracts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contracts = contracts;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }


}
