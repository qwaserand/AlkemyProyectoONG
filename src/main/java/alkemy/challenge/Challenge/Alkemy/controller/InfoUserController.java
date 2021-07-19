package alkemy.challenge.Challenge.Alkemy.controller;

import alkemy.challenge.Challenge.Alkemy.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class InfoUserController {

    @GetMapping("/me")
    public ResponseEntity<?> userInfo(Authentication authentication) {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        System.out.println("principal: " + auth.getPrincipal());
        return ResponseEntity.ok("User Information " + auth.getPrincipal());
    }
}
