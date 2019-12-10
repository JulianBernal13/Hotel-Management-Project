package HotelManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.Buffer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class InternetSearch {

	public static BufferedReader googleSearch(String search) throws UnsupportedEncodingException, IOException{
		//base google url to search from
		String googleAJAX = "https://www.google.com/search?q=";
		String searchfor = search; //input to search for
	
		if(searchfor == null) {
			return null;
		}
		
		//gets the URL for the completed google search
		//URL google = new URL(googleAJAX + URLEncoder.encode(searchfor, "UTF-8"));
		URLConnection google = new URL(googleAJAX + URLEncoder.encode(searchfor, "UTF-8")).openConnection();
		google.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
		google.connect();

		//google = Jsoup.connect(googleAJAX + URLEncoder.encode(searchfor, "UTF-8")).get();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(google.getInputStream()));
		return in;
	}
}
