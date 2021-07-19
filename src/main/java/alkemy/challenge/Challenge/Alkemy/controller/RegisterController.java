package alkemy.challenge.Challenge.Alkemy.controller;

import alkemy.challenge.Challenge.Alkemy.exception.UserAlreadyExistException;
import alkemy.challenge.Challenge.Alkemy.model.User;
import alkemy.challenge.Challenge.Alkemy.service.ErrorHandlingService;
import alkemy.challenge.Challenge.Alkemy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private ErrorHandlingService errorHandlingService;

    @Autowired
    private UserService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) throws UserAlreadyExistException {

        Map<String, Object> response = new HashMap<>();
        errorHandlingService.registerErrorHandling(result, response);

        try {
            userService.saveUser(user);
        } catch (Exception e) {
            response.put("message", "ERROR DB");
            response.put("error", e.getMessage().concat(":"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("user", user);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

}
