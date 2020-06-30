package engine.services;

import engine.exceptions.QuizNotFoundException;
import engine.exceptions.QuizNullException;
import engine.models.Feedback;
import engine.models.Quiz;
import engine.repositories.QuizRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;



@Getter
@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    public Quiz findById( int id ) {
        return quizRepository.findAll().stream()
                             .filter(quiz -> id == quiz.getId())
                             .findFirst()
                             .orElseThrow(() -> new QuizNotFoundException(("Invalid id" + id)));
    }

    public void addQuiz( Quiz quiz ) {
        if (quiz.getTitle() == null || quiz.getText() == null || quiz.getOptions() == null)
            throw new QuizNullException();
        quizRepository.save(quiz);
    }

    public Feedback getFeedback( Quiz quiz,String[] answer ) {
        return new Feedback(Arrays.equals(quiz.getAnswer(),answer) || (quiz.getAnswer() == null && answer.length == 0));
    }

    public void deleteQuiz( long id ) {
        quizRepository.deleteById(id);
    }

    public List<Quiz> getQuizzes() {
        return quizRepository.findAll();
    }
}
