package br.com.bexs.shortestPathRest.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TextManagement {

	public Map<String, City> load(String filePath) throws IOException {
		Map<String, City> cities = new HashMap<>();
		BufferedReader reader = new BufferedReader(new FileReader(filePath));

		reader.lines().forEach(line -> {
			String[] columns = line.split(",");
			
			City origin = cities.get(columns[0]); 
			if (origin == null) { 
				origin = new City(columns[0]);
				cities.put(columns[0], origin);
			}
			
			City destination = cities.get(columns[1]);
			if (destination == null) {
				destination = new City(columns[1]);
				cities.put(columns[1], destination);
			}
			
			Route route = new Route(origin, destination, Integer.parseInt(columns[2]));
			origin.getDestinations().add(route);
		});
		
		reader.close();
		
		return cities;
	}
	
	public void save(Map<String, City> cities, String filePath) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
		
		cities.values().stream()
			.map(city -> city.getDestinations())
			.flatMap(list -> list.stream())
			.forEach(route -> {
				try {
					writer.write(String.format("%s,%s,%d\n", 
							route.getOrigin().getName(),
							route.getDestination().getName(),
							route.getDistance()
							));
				} catch (IOException e) {
					System.out.println("Não foi possível salvar a base de dados.");
				}
			});
		writer.close();
	}
	
}
