package engine;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "This address email is already taken/invalid or password should be at least 5 characters long!")
public class UserEmailTakenException extends RuntimeException {
    UserEmailTakenException() {
        super();
    }
}

