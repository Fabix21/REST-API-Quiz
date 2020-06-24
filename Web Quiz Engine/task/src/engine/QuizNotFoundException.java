package engine;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Quiz not found")
class QuizNotFoundException extends RuntimeException {

    QuizNotFoundException( String message ) {
        super(message);
    }

    public QuizNotFoundException() {
    }
}
