package app.sensors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This is a startup code that helps in development. Its purpose is to populate the SensorRegistry with some initial
 * Sensor entities. Set populate to false if you want to start with an empty database.
 *
 * @author Michal Knapik
 * 
 * Based on https://spring.io/guides/tutorials/rest
 */
@Configuration
class PopulateDatabaseForTesting {

	// when false: don't populate the database with dummy data
	private static boolean populate = true;
	
	// a logging object - this will handle logging to console at startup
	private static final Logger log = LoggerFactory.getLogger(PopulateDatabaseForTesting.class);	
	
	// Spring Boot will run this at startup
	@Bean
	CommandLineRunner initDatabase(SensorRegistry registry) {
		
		// interface CommandLineRunner requires 'run' method; as it turns out lambda expressions
		// can provide that out of the box
		return args -> {
			
			// add some dummy sensors if required
			if (populate) {
				log.info("Adding some dummy Sensor entries to the database.");
				registry.save(new Sensor("noise", "decibels", "hall"));
				registry.save(new Sensor("temperature", "celsius", "hall"));				
				registry.save(new Sensor("parking", "Y/N", "Car Park 1, spot 113"));				
			} else {
				log.info("No dummy Sensor entries requested at startup.");
			}
			
		};
		
	}
  
}
