package HotelManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class StockSerach {

	public static void StockLook() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("please enter the name of the bussiness you wish to look up");
		String input = sc.nextLine();
		BufferedReader buff = InternetSearch.googleSearch(input);
		
		File test = new File ("." + File.separator + "URLreader");
		test.createNewFile();
		PrintWriter writer = new PrintWriter(test);
		
		String out = buff.readLine();
		
		String CompanyName = "none found";
		double Companystock = 0;
		while(out != null) {
			if(out.contains("</span><span class")) {
				int TO1 = out.indexOf("</span><span class");
				int i = 1;
				char check = out.charAt(TO1-i);
				String toDouble = "";
				while(check != '>') {
					if(check != ',') {
						toDouble = check + toDouble;
					}
					i++;
					check = out.charAt(TO1-i);
					writer.println(out);

				}
		        try {
					Companystock = Double.parseDouble(toDouble);
		        } catch (NumberFormatException e) {
		        }
			}
			if(out.contains("<span class=\"vk_bk\">")) {
				CompanyName = "";
				int TO2 = out.indexOf("<span class=\"vk_bk\">");
				int j = 1;
				char check = out.charAt(TO2+j);
				while(check != '>') {
					j++;
					check = out.charAt(TO2+j);
				}
				j++;
				String toDouble = "";
				while(check != '<') {
					CompanyName = CompanyName + check;
					j++;
					check = out.charAt(TO2+j);
				}
			}
			out = buff.readLine();
		}
		if(Companystock == 0) {
			System.out.println("no stock found for search");
		} else {
			System.out.println("CompanyName: " + CompanyName + "        StockPrice: " + Companystock);
		}
		
		//key to find company name \",\"48.70 
		//             ,\"Wyndham Destinations Inc\",\"WYND\",\"48.81
		//key to find stock </span><span class
	}
}
