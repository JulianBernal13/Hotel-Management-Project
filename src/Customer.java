/**
 * @author Anji Yu
 */

public class Customer {
    private String firstName;
    private String lastName;
    private Contract[] contracts;

    public Customer(String firstName, String lastName, Contract[] contracts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contracts = contracts;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }


}
