package HotelManagement;

import java.io.*;

/**
 * @author Yingxie Gao
 * @date 10/18/19 22:30
 */
public class Main {

    public static void main(String[] args) throws IOException {
        MainMenu m = new MainMenu();
        File managementSystem = new File("." + File.separator + "ManagementSystem");
        managementSystem.mkdir();
        m.menu();

        MainMenuTwo m2 = new MainMenuTwo();
        m2.menu();
    }
}
