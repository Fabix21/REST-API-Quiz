package engine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Text or Title can't be null")
public class QuizNullException extends RuntimeException {
    public QuizNullException() {
        super();
    }

}

