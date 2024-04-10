package app.sensors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * This is a helper for building representations for Sensor models and adding links to the resources
 * (i.e. Sensor JSON representations are extended with links to them) and the root (i.e. the registry of
 * all the sensors). 
 * 
 * @author Michal Knapik
 * 
 *  Based on https://spring.io/guides/tutorials/rest
 */
@Component
class SensorModelAssembler implements RepresentationModelAssembler<Sensor, EntityModel<Sensor>> {

  @SuppressWarnings("null")
  @Override
  public EntityModel<Sensor> toModel(Sensor sensor) {

    return EntityModel.of(sensor,
        linkTo(methodOn(SensorsController.class).one(sensor.getId())).withSelfRel(),
        linkTo(methodOn(SensorsController.class).all()).withRel("sensors"));
  }
  
}
