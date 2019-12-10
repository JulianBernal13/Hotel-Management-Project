package HotelManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class InternetSearch {

	public static BufferedReader googleSearch(String search) throws IOException {
		String googleAJAX = "https://www.google.com/search?q=";
		String searchfor = search;
		
		URL url = new URL(googleAJAX + URLEncoder.encode(searchfor, "UTF-8"));
		URLConnection urlConn = url.openConnection();
		InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
		BufferedReader buff = new BufferedReader(inStream);
		return buff;
	}
}
