package alkemy.challenge.Challenge.Alkemy.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomDeniedAccessHandler implements AccessDeniedHandler {

    Logger log = LoggerFactory.getLogger(CustomDeniedAccessHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("Access Denied!");
        response.sendRedirect("/deny");
    }
}
