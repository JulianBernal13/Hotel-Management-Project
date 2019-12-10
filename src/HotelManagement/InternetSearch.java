package HotelManagement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class InternetSearch {

	public static Document googleSearch(String search) throws UnsupportedEncodingException, IOException{
		String googleAJAX = "https://www.google.com/search?q=";
		String searchfor = "hello";
	
		Document google = Jsoup.connect(googleAJAX + URLEncoder.encode(searchfor, "UTF-8")).get();
				
		return google;
	}
}
