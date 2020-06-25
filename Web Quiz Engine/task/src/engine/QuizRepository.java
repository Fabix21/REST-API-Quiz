package engine;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Component
public class QuizRepository {

    private final AtomicLong idGenerator = new AtomicLong();
    @Getter
    private List<Quiz> quizzes = new ArrayList<>();

    private long generateId() {
        return idGenerator.incrementAndGet();
    }

    Quiz findById( int id ) {
        return quizzes.stream()
                .filter(quiz -> id == quiz.getId())
                .findFirst()
                .orElseThrow();
    }

    Quiz addQuiz( Quiz quiz ) {
        quiz.setId(generateId());
        quizzes.add(quiz);
        return quiz;
    }

    Feedback getFeedback( Quiz quiz,String[] answer ) {

        return new Feedback(Arrays.equals(quiz.getAnswer(),answer) || (quiz.getAnswer() == null && answer.length == 0));

    }

    int size() {
        return quizzes.size();
    }

    void checkForIndexException( int id ) {
        if (id > quizzes.size()) {
            throw new QuizNotFoundException("Invalid id" + id);
        }
    }

}
