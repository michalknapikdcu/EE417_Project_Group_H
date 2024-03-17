package app.sensors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The entry point for the app for sensor management and serving sensor readings.
 * This code will scan its package for Components, Beans, and Configurations and
 * assemble them into working application.
 * 
 * Scheduling is enabled in order to send server stream events with sensor readings
 * to web clients.
 * 
 * @author Michal Knapik
 * 
 * Based on https://spring.io/guides/tutorials/rest
 * and https://medium.com/@AlexanderObregon/implementing-server-sent-events-sse-with-spring-cbf283171aef
 */
@SpringBootApplication
@EnableScheduling
public class SensorsServer {

	public static void main(String[] args) {
		SpringApplication.run(SensorsServer.class, args);
	}

}
