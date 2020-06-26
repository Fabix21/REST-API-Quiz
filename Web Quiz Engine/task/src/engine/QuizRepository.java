package engine;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Getter
@Component
public class QuizRepository {

    private final AtomicLong idGenerator = new AtomicLong();
    private List<Quiz> quizzes = new ArrayList<>();


    Quiz findById( int id ) {
        return quizzes.stream()
                .filter(quiz -> id == quiz.getId())
                .findFirst()
                .orElseThrow();
    }

    void addQuiz( Quiz quiz ) {
        quizzes.add(quiz);
    }

    Feedback getFeedback( Quiz quiz,String[] answer ) {
        return new Feedback(Arrays.equals(quiz.getAnswer(),answer) || (quiz.getAnswer() == null && answer.length == 0));
    }


    void checkForIndexException( int id ) {
        if (id > quizzes.size()) {
            throw new QuizNotFoundException("Invalid id" + id);
        }
    }

    List<Quiz> setQuizzes( List<Quiz> quizzes ) {
        this.quizzes = quizzes;
        return quizzes;
    }


}
