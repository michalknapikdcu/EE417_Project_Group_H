package app.sensors;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

/**
 * This controller deals with serving data per path-based requests. This also includes stream of sensor readings.
 * 
 * @author Michal Knapik
 * 
 * Based on https://spring.io/guides/tutorials/rest and https://github.com/Christian-Oette/demo-chat-app-sse-spring-boot
 */
@RestController
class SensorsController {

	// database: the registry of all the Sensors
	private final SensorRegistry registry;
  
	// a helper for building Sensors
	private final SensorModelAssembler assembler;
  
	// when true: log operations to server's log
	private static boolean verbose = true;
	
	// a logging object - this will handle logging to console during operations
	private static final Logger log = LoggerFactory.getLogger(SensorsController.class);	

	// a list of emitters assigned to connecting clients
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
    
    // a mock data source for numeric sensor readings
    private final MockSensorReading mockReadingSource = new MockSensorReading();

    // register a new client - helper function
    public SseEmitter registerClient() {
        var emitter = new SseEmitter(Long.MAX_VALUE); // make a new emitter	
        emitters.add(emitter); // insert it into the client's emitters list
        emitter.onCompletion(() -> emitters.remove(emitter)); // remove it from the list if done
        emitter.onTimeout(() -> emitters.remove(emitter)); // remove it from the list on timeout
   
        return emitter;
    }
	
	// a constructor
	SensorsController(SensorRegistry registry, SensorModelAssembler assembler) {
		this.registry = registry; 
		this.assembler = assembler;    
	}

	// operation: get Sensor registry
	@GetMapping("/sensors")
	CollectionModel<EntityModel<Sensor>> all() {

		if (this.verbose) log.info("Sending Sensor list");
		
		// collect all sensors from registry
		List<EntityModel<Sensor>> sensors = registry.findAll().stream().map(assembler::toModel).collect(Collectors.toList());

		// CollectionModel is a wrapper on collection of Entities
		return CollectionModel.of(sensors, linkTo(methodOn(SensorsController.class).all()).withSelfRel());
	} 

	// operation: save a sensor (Sensor model guarantees unique Ids)
	@PostMapping("/sensors")
	Sensor newSensor(@RequestBody Sensor sensor) {
		
		if (this.verbose) log.info("Saved new Sensor: " + sensor);
		
		return registry.save(sensor);
	}

	// operation: return a sensor with a given id
	@GetMapping("/sensors/{id}")
	EntityModel<Sensor> one(@PathVariable Long id) {
		
		if (this.verbose) log.info("Handling request for Sensor with id " + id);
		
		Sensor sensor = registry.findById(id).orElseThrow(() -> new SensorNotFoundException(id));
		
		return assembler.toModel(sensor);
	} 
  
 	// operation: update a sensor with a given id if it is in registry
	@PutMapping("/sensors/{id}")
	Sensor replaceSensor(@RequestBody Sensor sensor, @PathVariable Long id) {
		
		if (this.verbose) log.info("Handling request for updating Sensor " + sensor + " under id " + id);
		
		// check if the sensor with a given Id is there
		var sensorInBaseOpt = registry.findById(id);
		
		if (sensorInBaseOpt.isPresent()) { // if so..
			
			// then update it with the data of the incoming Sensor and return
			Sensor sensorInBase = sensorInBaseOpt.get();
			sensorInBase.setName(sensor.getName());
			sensorInBase.setUnit(sensor.getUnit());
			sensorInBase.setLocation(sensor.getLocation());
			sensorInBase.setId(id);
			
			if (this.verbose) log.info("Updated");
 			
			return registry.save(sensorInBase);
			
		} else { 
			throw new SensorNotFoundException(id);
		}
	
	}

	// operation: delete a sensor with a given id
	@DeleteMapping("/sensors/{id}")
	void deleteSensor(@PathVariable Long id) {

	  if (this.verbose) log.info("Deleting Sensor with id " + id);
	  
	  // get sensor from registry if it's there and throw exception if not
	  Sensor sensor = registry.findById(id).orElseThrow(() -> new SensorNotFoundException(id));	    

	  // delete the sensor from registry if found
	  registry.delete(sensor);
    
	}
 
	// operation: register a new client to receive stream of sensor readings
    @GetMapping("/sensors/stream")
    public SseEmitter sseEmitter() {
       	return this.registerClient();
    }
    
    // scheduled operation: every 10 seconds send a JSON with readings of all the sensors to all the emitters
    @Scheduled(fixedRate = 10000)
    public void sendEvents() {
    	
    	// prepare a map from sensor ids to themselves, updated with readings
		List<Sensor> sensorsWithReadings = registry.findAll().stream().collect(Collectors.toList());
		for (Sensor sensor: sensorsWithReadings) {
			this.mockReadingSource.getDummyReading(sensor);		
		}
		
    	// send the map of all the readings to all subscribers
    	for (SseEmitter emitter: emitters) {
            try {
                emitter.send(sensorsWithReadings);
            } catch (IOException e) {
                emitter.complete();
                emitters.remove(emitter);
            }
        }
    }    
	
}
