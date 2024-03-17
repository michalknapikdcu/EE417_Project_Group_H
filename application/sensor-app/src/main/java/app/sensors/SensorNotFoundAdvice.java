package app.sensors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * In practice this is a wrapper on SensorNotFoundException that sends JSON error messages
 * to requester.
 * 
 * @author Michal Knapik
 * 
 * Based on https://spring.io/guides/tutorials/rest and https://www.baeldung.com/spring-request-response-body
 */
@ControllerAdvice
class SensorNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(SensorNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	BasicError employeeNotFoundHandler(SensorNotFoundException ex) {
		
		return new BasicError("NotFound", ex.getMessage());
	}
}
