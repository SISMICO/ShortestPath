package br.com.bexs.shortestPathRest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bexs.shortestPathRest.database.DataBase;

@RestController
public class RoutesController {

	@GetMapping("/routes")
	public List<Route> getRoutes() {
		return DataBase.cities.values().stream()
				.map(city -> city.getDestinations())
				.flatMap(list -> list.stream())
				.map(route -> new Route(
						route.getOrigin().getName(), 
						route.getDestination().getName(), 
						route.getDistance()
						))
				.collect(Collectors.toList());
	}

	@DeleteMapping("/routes/{origin}/{destination}")
	public void deleteRoute(@PathVariable String origin, @PathVariable String destination) {
		DataBase.remove(origin, destination);

	}

	@PutMapping("/routes")
	public void newRoute(@RequestBody Route newRoute) {
		DataBase.add(newRoute.getOrigin(), newRoute.getDestination(), newRoute.getDistance());
	}

}
