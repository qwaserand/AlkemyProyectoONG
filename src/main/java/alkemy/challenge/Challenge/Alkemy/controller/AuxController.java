package alkemy.challenge.Challenge.Alkemy.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping("/returnme")
public class AuxController {

    @GetMapping
    public Object returnUsername(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetails) principal).getUsername();
    }
}
