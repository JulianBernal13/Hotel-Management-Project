package HotelManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

import org.jsoup.nodes.Document;

public class StockSerach {

	public static void StockLook() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("please enter the name of the bussiness you wish to look up");
		String input = sc.nextLine();
		Document buff = InternetSearch.googleSearch(input);

	}
}
