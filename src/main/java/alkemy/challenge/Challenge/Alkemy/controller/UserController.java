package alkemy.challenge.Challenge.Alkemy.controller;

import alkemy.challenge.Challenge.Alkemy.exception.UserAlreadyExistException;
import alkemy.challenge.Challenge.Alkemy.model.User;
import alkemy.challenge.Challenge.Alkemy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import java.util.List;
import java.util.Optional;

@ApiIgnore
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> listUsers(){
        return userService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        userService.softDelete(user.get());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * Guardar usuario en la base de datos
     */
    @PostMapping("/")
    public void saveUser(User u) throws UserAlreadyExistException {
        userService.saveUser(u);
    }

    /**
     * Loguear un usuario nuevo
     */
    @GetMapping("/")
    public User loginUser() {
        User u = new User();
        return u;
    }
}
