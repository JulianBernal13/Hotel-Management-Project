/**
 * @author Anji Yu
 */

public class Location {
    String name;
    String street;
    String city;
    String state;

    public Location(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
