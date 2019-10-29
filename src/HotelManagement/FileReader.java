package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    public static String getHotelInfo(File file, Hotel.HotelProperty property) throws FileNotFoundException {
        Scanner in = new Scanner(file);
        int i = 0;
        while(i++ < property.ordinal() && in.hasNext())
            in.nextLine();
        return in.nextLine();
    }
}
