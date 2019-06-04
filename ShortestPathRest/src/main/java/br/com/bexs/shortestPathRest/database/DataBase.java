package br.com.bexs.shortestPathRest.database;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataBase {

	public static Map<String, City> cities = new HashMap<>();
	private static String filePath;
	
	public static void load(String file) {
		filePath = file;
		TextManagement importer = new TextManagement();
		try {
			cities = importer.load(filePath);
		} catch (IOException e) {
			System.out.println("Não foi possível carregar a base de dados.");
			System.out.println(String.format("Erro: %s", e.getMessage()));
		}
	}
	
	public static void add(String origin, String destination, int distance) {
		City originCity = cities.get(origin);
		if (originCity == null)
			originCity = new City(origin);
		
		City destinationCity = cities.get(destination);
		if (destinationCity == null)
			destinationCity = new City(destination);
		
		originCity.getDestinations().add(new Route(originCity, destinationCity, distance));
		
		save();
	}
	
	public static void remove(String origin, String destination) {
		City originCity = cities.get(origin);
		if (originCity != null) {
			List<Route> deleteRoutes = originCity.getDestinations().stream()
			.filter(route -> route.getDestination().getName().equals(destination))
			.collect(Collectors.toList());
			
			deleteRoutes.forEach(route -> originCity.getDestinations().remove(route));
			
			save();	
		}
	}
	
	private static void save() {
		TextManagement writer = new TextManagement();
		try {
			writer.save(cities, filePath);
		} catch (IOException e) {
			System.out.println("Não foi possível salvar a base de dados.");
			System.out.println(String.format("Erro: %s", e.getMessage()));
		}
	}
	
}
