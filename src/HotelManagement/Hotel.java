package HotelManagement;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.IOException;

//enum HotelProperty {
//    name, location, numOfLevel, levelRmNum, password;
//}

/**
 * @author Yingxie Gao
 * @date 10/18/19 22:30
 */
public class Hotel {
    private String name;
    private Location location;
    private int numOfLevel;
    private int levelRmNum;
    private Room rooms[][];
    private ArrayList<Employee> employees;
    private String password;
    private String path;

    public static enum HotelProperty {
        name, location, numOfLevel, levelRmNum, password;
    }


    public Hotel(String path, String name,Location location, int numOfLevel, int levelRmNum, String password) throws IOException {
        this.path = path;
        this.name = name;
        this.location = location;
        this.numOfLevel = numOfLevel;
        this.levelRmNum = levelRmNum;
        this.password = password;
        rooms = new Room[numOfLevel][levelRmNum];


        File info = new File(path + File.separator + "info.txt");
        PrintWriter writer = new PrintWriter(info);
        writer.println(name);
        writer.println(location);
        writer.println(numOfLevel);
        writer.println(levelRmNum);
        writer.println(password);
        writer.flush();
        writer.close();

        String roomPath = path + File.separator + "Rooms";
        File roomFile = new File(roomPath);
        roomFile.mkdir();
        for(int i = 0; i < numOfLevel; i++) {
            for(int j = 0; j < levelRmNum; j++) {
                rooms[i][j] = new Room((i+1)* 100 + j, roomPath);
                rooms[i][j].createRoomFile();
            }
        }

        createDirectory(this.path, "Customer");
        createDirectory(this.path, "Employee");



        ArrayList<Employee> employees =new ArrayList<>();
    }

    public static void createDirectory(String currentPath, String name) {
        String targetPath = currentPath + File.separator + name;
        File targetFile = new File(targetPath);
        targetFile.mkdir();
    }

}
