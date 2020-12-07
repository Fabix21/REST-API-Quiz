package engine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "email or password can't be null!")
public class UserInvalidInputException extends RuntimeException {
    public UserInvalidInputException() {
    }
}
