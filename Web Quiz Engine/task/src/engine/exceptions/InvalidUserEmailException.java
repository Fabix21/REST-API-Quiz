package engine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "This address email is invalid!")
public class InvalidUserEmailException extends RuntimeException {
    public InvalidUserEmailException() {
        super();
    }
}
