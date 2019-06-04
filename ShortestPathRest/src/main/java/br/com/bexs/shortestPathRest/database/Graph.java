package br.com.bexs.shortestPathRest.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Graph {

	private Map<String, City> cities = DataBase.cities;
	
	public List<Route> shortestPath(String origin, String destination) {
		City refCity = this.cities.get(origin);
		refCity.setDistance(0);
		
		List<City> visited = new ArrayList<City>();
		List<City> remain = new ArrayList<City>();
		remain.add(refCity);
		
		while (remain.size() > 0) {
			refCity = remain.get(0);
			
			refCity.getDestinations().stream().forEach(route -> {
				this.calculateMinDistance(route);
				
				if (!visited.contains(route.getDestination())) {
					remain.add(route.getDestination());
				}
			});
			
			visited.add(refCity);
			remain.remove(refCity);
		}
		
		return makeShortestPath(this.cities.get(destination));
	}
	
	private void calculateMinDistance(Route route) {
		int distDestination = route.getDestination().getDistance() != null ? route.getDestination().getDistance() : 0;
		int newDistance = route.getOrigin().getDistance() + route.getDistance();
		
		if (route.getDestination().getDistance() == null || newDistance < distDestination) {
			route.getDestination().setPrevious(route);
			route.getDestination().setDistance(newDistance);
		}
	}
	
	private List<Route> makeShortestPath(City destination) {
		List<Route> resultado = new ArrayList<Route>();
		
		City path = destination;
		while (path != null) {
			if (path.getPrevious() != null) {
				resultado.add(0, path.getPrevious());
				path = path.getPrevious().getOrigin();
			} else {
				path = null;
			}
		}
		return resultado;
	}
	
}
