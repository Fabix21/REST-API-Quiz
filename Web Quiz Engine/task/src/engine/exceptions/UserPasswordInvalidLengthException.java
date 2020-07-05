package engine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Password should contain at least five characters!")
public class UserPasswordInvalidLengthException extends RuntimeException {
    public UserPasswordInvalidLengthException() {
        super();
    }
}
