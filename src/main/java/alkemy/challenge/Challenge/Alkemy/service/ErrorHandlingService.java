package alkemy.challenge.Challenge.Alkemy.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ErrorHandlingService {

    public ResponseEntity<?> registerErrorHandling(BindingResult result, Map<String, Object> response) {

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "The field '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("error", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

}
