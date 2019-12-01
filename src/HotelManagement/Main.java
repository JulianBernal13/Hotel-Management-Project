package HotelManagement;

import HotelManagement.Display.DisplayMainMenu;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Yingxie Gao
 * @date 10/18/19 22:30
 */
public class Main {
    public static Date current = new Date();
    public static Calendar calendar = new GregorianCalendar();
    public static SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");

    public static void main(String[] args) throws IOException, ParseException {
        calendar.setTime(current);
        //add 1 day to calendar for testing
        calendar.add(Calendar.DATE, 2);
        current = calendar.getTime();
        System.out.println(sdf.format(current));
//        MainMenu m = new MainMenu();
//        m.menu();
        System.out.println("Welcome to Hotel Management System");
        DisplayMainMenu menu = new DisplayMainMenu();
        menu.displayCommand();
    }
}
