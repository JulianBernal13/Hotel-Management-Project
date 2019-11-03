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
        System.out.println("Welcome manager");
        Printer.printManagerMenu();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        switch (command) {
            case "exit":
                break;
            case "DH": {
//                FileReader.displayHotelInfo(info);
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
                Scanner sc2 = new Scanner(System.in);
                System.out.println("Enter the id for the employee you want to edit");
                String id = sc2.nextLine();
                System.out.println("What do you want to change about the employee?");
                String property = sc2.nextLine();
//                EmployeeFileController.modifyEmployee(info1, id, property);

                break;
            } // edit employee
            case "AE": {
                String employeePath = cur.getPath() + File.separator + "Employee";
                Scanner sc2 = new Scanner(System.in);
                System.out.println("Enter the title Name for new employee");
                String titleName = sc2.nextLine();
                System.out.println("Enter the ID for new employee");
                String id = sc2.nextLine();
                System.out.println("Enter the paymentType for new employee");
                String paymentType = sc2.nextLine();
                System.out.println("Enter the salary for new employee");
                String salary = sc2.nextLine();
                Employee emp = new Employee(employeePath, titleName, id, paymentType, Integer.parseInt(salary));
                manager.addEmployeeToFile(emp.getPath(), emp);
                break;
            }
            case "FE": {
                String employeePath = cur.getPath() + File.separator + "Employee";
                Scanner sc2 = new Scanner(System.in);
                System.out.println("Enter the ID for new employee you want to fire");
                String id = sc2.nextLine();
                manager.deleteEmployeeFromFile(employeePath, id);
                break;
            }
            case "CRT": {
                RoomFileController.applyRoomType(cur);
                break;
            } // change room type
        }
    }
}
