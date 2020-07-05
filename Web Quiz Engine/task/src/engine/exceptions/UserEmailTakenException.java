package engine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "This address email is already taken!")
public class UserEmailTakenException extends RuntimeException {
    public UserEmailTakenException() {
        super();
    }
}

