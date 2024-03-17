package app.sensors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point for the app for sensor management and serving sensor readings.
 * This code will scan its package for Components, Beans, and Configurations and
 * assemble them into working application.
 * 
 * @author Michal Knapik
 * 
 * Based on https://spring.io/guides/tutorials/rest
 */
@SpringBootApplication
public class SensorsServer {

	public static void main(String[] args) {
		SpringApplication.run(SensorsServer.class, args);
	}

}
