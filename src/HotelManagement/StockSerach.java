package HotelManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class StockSerach {

	public static void StockLook() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("please enter the name of the bussiness you wish to look up");
		String input = sc.nextLine();
		BufferedReader buff = InternetSearch.googleSearch(input);
		
		String line = buff.readLine();
		while(line != null) {
			System.out.println(line);
			line = buff.readLine();
		}
	}
}
