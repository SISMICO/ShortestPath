package br.com.bexs.shortestpath;

import static org.junit.Assert.*;

import org.junit.Test;

public class RouteTest {

	@Test
	public void testConstructorWithParam() {
		Route route = new Route("ABC", "DEF", 21);
		assertEquals("ABC", route.getOrigin());
		assertEquals("DEF", route.getDestination());
		assertEquals(21, route.getDistance());
	}

	@Test
	public void testSetOrigin() {
		Route route = new Route();
		route.setOrigin("QWE");
		assertEquals("QWE", route.getOrigin());
	}

	@Test
	public void testSetDestination() {
		Route route = new Route();
		route.setDestination("POI");
		assertEquals("POI", route.getDestination());
	}

	@Test
	public void testSetDistance() {
		Route route = new Route();
		route.setDistance(76);
		assertEquals(76, route.getDistance());
	}

}
