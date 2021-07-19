package alkemy.challenge.Challenge.Alkemy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class IndexController {
    @RequestMapping("/")
    String index(){
        return "index2";
    }
}
