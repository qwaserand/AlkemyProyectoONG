package alkemy.challenge.Challenge.Alkemy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class DenyController {

    @GetMapping("/deny")
    public void accessDenied() throws Exception {
        throw new Exception("User is not authorized");
    }
}
