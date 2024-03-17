package app.sensors;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Supposedly, this is needed to deploy the application to an external tomcat server via war file.
 * So, this is the entry point for deployed applications.
 * 
 * @author Michal Knapik
 * 
 * Based on https://spring.io/guides/tutorials/rest and https://howtodoinjava.com/spring-boot/spring-boot-servletinitializer/
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SensorsServer.class);
	}

}
