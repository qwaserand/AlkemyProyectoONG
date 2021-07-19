package alkemy.challenge.Challenge.Alkemy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value ="error", required =false) String error, @RequestParam(value ="logout", required =false) String logout , Model model, Principal principal, RedirectAttributes flash) {

        if(principal != null) { //Si es distinto de null es porque ya habia iniciado sesión anteriormente.
            flash.addFlashAttribute("info", "Ya ha iniciado sesión anteriormente");
            return "redirect:/"; //Se evita que vuelva a iniciarse sesion.
        }

        if(error != null) {
            model.addAttribute("error", "Usuario o Contraseña incorrecta. Intentelo nuevamente");
        }

        if(logout != null) {
            model.addAttribute("success", "Ha cerrado sesión con éxito");
        }

        return "login";
    }
}
