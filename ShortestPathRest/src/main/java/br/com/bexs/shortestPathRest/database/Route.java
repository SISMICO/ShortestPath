package br.com.bexs.shortestPathRest.database;

public class Route {

	private City origin;
	private City destination;
	private int distance;
	
	public Route(City origin, City destination, int distance) {
		this.origin = origin;
		this.destination = destination;
		this.distance = distance;
	}
	
	public City getOrigin() {
		return origin;
	}
	public void setOrigin(City origin) {
		this.origin = origin;
	}
	public City getDestination() {
		return destination;
	}
	public void setDestination(City destination) {
		this.destination = destination;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
}
