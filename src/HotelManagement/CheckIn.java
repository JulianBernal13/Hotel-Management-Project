package HotelManagement;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CheckIn implements Menu {

    Hotel hotel;
    File customerFolder;

    public CheckIn(Hotel hotel) {
        this.hotel = hotel;
        customerFolder = CustomerFileController.cdCustomerFolder(hotel.getPath());
    }

    @Override
    public void menu() throws IOException {
        System.out.println("Enter customer's name");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        Customer customer = hotel.getCustomer(name);
        if(customer == null) {
            int i = name.indexOf(' ');
            String firstName = name.substring(0, i), lastName = name.substring(i + 1);
            hotel.addCustomer(new Customer(firstName, lastName, customerFolder));
            customer = hotel.getCustomer(name);
        }
        if(customer.isStaying()) {
            System.out.println("This customer is already checked in");
            return;
        }
        System.out.println("What type of room do you want?((single,double,triple,queen,king))");
        String type = sc.nextLine();
        if (hotel.showTypeRoom(type) > 0) {
            System.out.println("Which room are you going to check-in?");
            Room check = hotel.getRoom(sc.nextLine());
            System.out.println(check.getNumber());
            while (!check.isEmpty() || !check.isClean() || !check.getType().equals(type)) {
                System.out.println("This room cannot be checked-in, enter another room \n");
                check = hotel.getRoom(sc.nextLine());
            }
            check.setEmpty(false);
            check.writeToFile();
            customer.setStaying(true);
            customer.writeToFile();
            System.out.println("Success! Now the customer is checked in.");
        } else {
            System.out.println("Sorry, we do not have such room available.");
        }
    }
}
