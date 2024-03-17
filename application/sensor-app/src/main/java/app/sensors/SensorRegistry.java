package app.sensors;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Fetch API for CRUD operations on Sensor database, where sensors are identified by Long Ids
 * (see Sensor.java for Entity model).
 * 
 * @author Michal Knapik
 * 
 * Based on https://spring.io/guides/tutorials/rest
 */
interface SensorRegistry extends JpaRepository<Sensor, Long> {}
