package HotelManagement;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CheckOut implements Menu {

    Hotel hotel;

    public CheckOut(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void menu() throws IOException { //needs contract
//        Scanner scanner = new Scanner(System.in);
//        String roomPath = cur.getPath() + File.separator + "Rooms";
//        System.out.println("which room are you going to check-out?");
//        if(showOccupiedRoom(roomPath) > 0){
//            String num = scanner.nextLine();
//            File check = new File(roomPath + File.separator + num+".txt");
//            while (!check.exists()) {
//                System.out.println("This room cannot be checked-out, enter another room \n");
//                num = scanner.nextLine();
//                check = new File(roomPath + File.separator + num+".txt");
//            }
//            changeRoomEmpty(roomPath, Integer.parseInt(num));
//            System.out.println("Success! Now the room is empty.");
//        }
//        else{
//            System.out.println("There is no room occupied.");
//        }
    }
}
