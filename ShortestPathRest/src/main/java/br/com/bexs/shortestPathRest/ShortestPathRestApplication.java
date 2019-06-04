package br.com.bexs.shortestPathRest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.bexs.shortestPathRest.database.DataBase;

@SpringBootApplication
public class ShortestPathRestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ShortestPathRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String filePath = args[0];
		DataBase.load(filePath);
	}

}
