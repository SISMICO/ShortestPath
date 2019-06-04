package br.com.bexs.shortestPathRest.database;

import java.util.ArrayList;
import java.util.List;

public class City {

	private String name;
	private List<Route> destinations = new ArrayList<Route>();
	private Integer distance;
	private Route previous;
	
	public City(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	public List<Route> getDestinations() {
		return destinations;
	}
	public void setDestinations(List<Route> destinations) {
		this.destinations = destinations;
	}
	public Route getPrevious() {
		return previous;
	}
	public void setPrevious(Route previous) {
		this.previous = previous;
	}
	
}
