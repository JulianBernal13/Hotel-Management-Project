package HotelManagement;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ManagerMenu implements Menu {
    Hotel hotel;
    Manager manager;

    public ManagerMenu(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void menu() throws IOException {
        File cur = new File(hotel.getPath());
        File employeeFiles = new File(cur.getPath() + File.separator + "Employee");
        System.out.println("Welcome manager");
        Printer.printManagerMenu();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        switch (command) {
            case "exit":
                break;
            case "DH": {
                FileReader.displayHotelInfo(hotel.getPath());
                break;
            }
            case "DE": {
                String employeePath = cur.getPath() + File.separator + "Employee";
                File info3 = new File(employeePath);
                File[] temp = info3.listFiles();
                for (File f : temp) {
                    FileReader.displayEmpInfo(f);
                }
                break;
            }
            case "LR":
                RoomFileController.listRooms(cur); // call Room List
            case "CE": {
                EmployeeFileController.menuLookUp(EmployeeFileController.cdEmployeeFile(cur));
                break; // call employee
            }
            case "EE": {
                EmployeeFileController.modifyEmployee(employeeFiles);
                break;
            } // edit employee
            case "AE": {
                EmployeeFileController.createEmployee(employeeFiles);
				break;
            }
            case "FE": {
              EmployeeFileController.deleteEmployee(employeeFiles);
				break;
            }
            case "CRT": {
                RoomFileController.applyRoomType(cur);
                menu();
                break;
            } // change room type
        }
    }
}
