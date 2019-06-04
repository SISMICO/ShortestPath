package br.com.bexs.shortestPathRest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.bexs.shortestPathRest.database.Graph;

@RestController
public class ShortetestPathController {

	@GetMapping("/shortestPath/{origin}/{destination}")
	public List<Route> getShortestPath(@PathVariable String origin, @PathVariable String destination) {
		List<Route> result = new ArrayList<Route>();
		Graph graph = new Graph();
		graph.shortestPath(origin, destination).forEach(route -> {
			Route r = new Route(
					route.getOrigin().getName(), 
					route.getDestination().getName(), 
					route.getDistance()
				);
			result.add(r);
		});
		return result;
	}
	
}
