package engine;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuizController {

    private QuizRepository quizRepository = new QuizRepository();

    @GetMapping(path = "api/quizzes/{id}")
    public Quiz getQuestionById( @PathVariable int id ) {
        quizRepository.checkForIndexException(id);
        return quizRepository.findById(id);
    }

    @GetMapping(path = "api/quizzes")
    public List<Quiz> getAllQuestions() {
        return quizRepository.getQuizzes();
    }

    @PostMapping(path = "api/quizzes")
    public Quiz addQuestion( @RequestBody Quiz quiz ) {
        if (quiz.getTitle() == null || quiz.getText() == null)
            throw new QuizNullStringException();
        quizRepository.addQuiz(quiz);
        return quiz;
    }

    @PostMapping(path = "api/quizzes/{id}/solve")
    public Feedback checkQuestion( @RequestBody Quiz quiz,@PathVariable int id ) {
        quizRepository.checkForIndexException(id);
        return quizRepository.getFeedback(quizRepository.findById(id),quiz.getAnswer());
    }
}


