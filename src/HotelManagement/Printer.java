package HotelManagement;

public class Printer {
    public static void printWelcome(){
        System.out.println("=============================================");
        System.out.println("*         Hotel Management System           *");
        System.out.println("=============================================");
        System.out.println("Enter 'new' to construct a new hotel         ");
        System.out.println("Enter 'manage' to manage hotel               ");
        System.out.println("Enter 'exit' to exit the system              ");
        System.out.println("=============================================");
        System.out.println("");
    }

    public static void printEditCustomer() {
        System.out.println("=============================================");
        System.out.println("Enter 'ckeckin' to check customer in         ");
        System.out.println("Enter 'ckeckout' to check customer out       ");
        System.out.println("Enter 'VIP' to make this customer VIP        ");
//        System.out.println("");
        System.out.println("=============================================");
        System.out.println("");
    }

    public static void printManager() {
        System.out.println("");
    }
}
