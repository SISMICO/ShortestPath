package br.com.bexs.shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

	private HttpURLConnection getConnection(String endpoint) throws IOException {
		URL url = new URL(endpoint);
		return (HttpURLConnection) url.openConnection();
	}
	
	public String get(String endpoint) {
		String result = null;
		try {
			HttpURLConnection con = getConnection(endpoint);
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");

			int status = con.getResponseCode();
			if (status == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				in.close();
				con.disconnect();
				result = content.toString();
			}
		} catch (IOException e) {
			System.out.println(String.format("Ocorreu um erro: %s", e.getMessage()));
		}
		
		return result;
	}

}
