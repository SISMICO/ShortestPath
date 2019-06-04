package br.com.bexs.shortestpath;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Service {

	private String urlServiceShortestPath = "http://localhost:8080/shortestPath";

	public Service() { }
	
	HttpUtil httpUtil() {
		return new HttpUtil();
	}

	public List<Route> shortestPath(String origin, String destination) {
		List<Route> list = new ArrayList<Route>();
		
		String result = httpUtil().get(String.format("%s/%s/%s", urlServiceShortestPath, origin, destination));
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			list = objectMapper.readValue(result, new TypeReference<List<Route>>(){});
		} catch (Exception e) {
			System.out.println(String.format("Ocorreu um erro: %s", e.getMessage()));
		}
		
		return list;
	}

}
