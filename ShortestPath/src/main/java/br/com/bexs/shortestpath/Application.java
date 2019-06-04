package br.com.bexs.shortestpath;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {

	public static void main(String[] args) {
		new Application().start();
	}

	private Scanner console;
	
	Scanner getScanner() {
		return new Scanner(System.in);
	}

	public void start() {
		console = getScanner();
		Pattern pattern = Pattern.compile("([A-Za-z]{3})-([A-Za-z]{3})");
		String input;
		do {
			input = readLine();
			if (!input.isEmpty()) {
				Matcher matcher = pattern.matcher(input);
				if (!matcher.matches())
					System.out.println("Please enter the route using the format: ABC-CDE");
				else
					getShortestPath(matcher.group(1), matcher.group(2));
			}
		} while (!input.isEmpty());
		System.out.println("Thank you!");
	}

	private String readLine() {
		System.out.print("Please enter the route: ");
		return console.nextLine().toUpperCase();
	}

	private void getShortestPath(String origin, String destination) {
		Service service = new Service();
		List<Route> routes = service.shortestPath(origin, destination);
		if (routes.size() == 0) {
			System.out.println("No routes available!");
		} else {
			AtomicInteger sum = new AtomicInteger();
			StringBuilder output = new StringBuilder("Best route: ");
			output.append(routes.get(0).getOrigin());
			routes.forEach(route -> {
				output.append(String.format(" - %s", route.getDestination()));
				sum.addAndGet(route.getDistance());
			});
			output.append(String.format(" > %d", sum.get()));
			
			System.out.println(output.toString());
		}
	}
}
