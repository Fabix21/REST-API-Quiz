package engine;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Text or Title can't be null")
class QuizNullException extends RuntimeException {
    QuizNullException() {
        super();
    }

}

