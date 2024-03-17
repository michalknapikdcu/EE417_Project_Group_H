package app.sensors;

/**
 * This exception is raised upon a REST request for a sensor that's not
 * in the Sensor registry.
 * 
 * @author Michal Knapik
 * 
 * Based on https://spring.io/guides/tutorials/rest
 */
class SensorNotFoundException extends RuntimeException {
	  SensorNotFoundException(Long id) {
	    super(Long.toString(id));
	  }
	}
