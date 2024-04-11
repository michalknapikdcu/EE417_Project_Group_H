package app.sensors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin
public class LoginController2 {

    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(LoginController2.class);

    @Autowired
    private LoginRepository loginRepository;

    @PostMapping("/login")
    public ResponseEntity<Login> login(@RequestBody Login LoginDetails) {
        Login login = loginRepository.findByUsername(LoginDetails.getUsername());
        if (login != null && login.getPassword().equals(LoginDetails.getPassword())) {
            // Authentication successful, return the user object which includes the role
            return ResponseEntity.ok(login);
        } else {
            // Authentication failed, return an unauthorized response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
