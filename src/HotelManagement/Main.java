package HotelManagement;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Yingxie Gao
 * @date 10/18/19 22:30
 */
public class Main {

    public static void main(String[] args) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
        Date current = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(current);
        //add 1 day to calendar
        calendar.add(Calendar.DATE, 1);
        current = calendar.getTime();
        System.out.println(sdf.format(current));
        MainMenu m = new MainMenu();
        m.menu();
    }
}
