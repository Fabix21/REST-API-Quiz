package engine.controllers;

import engine.models.Feedback;
import engine.models.Quiz;
import engine.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class QuizController {

    @Autowired
    QuizService quizService;

    @GetMapping(path = "api/quizzes/{id}")
    public Quiz getQuestionById( @PathVariable int id ) {
        return quizService.findById(id);
    }

    @GetMapping(path = "api/quizzes")
    public List<Quiz> getAllQuestions() {
        return quizService.getQuizzes();
    }

    @PostMapping(path = "api/quizzes")
    public Quiz addQuestion( @RequestBody Quiz quiz ) {
        quizService.addQuiz(quiz);
        return quiz;
    }

    @PostMapping(path = "api/quizzes/{id}/solve")
    public Feedback checkQuestion( @RequestBody Quiz quiz,@PathVariable int id ) {
        return quizService.getFeedback(quizService.findById(id),quiz.getAnswer());
    }

    @DeleteMapping(path = "api/quizzes/{id}")
    public void deleteQuestion( @PathVariable long id ) {
        quizService.deleteQuiz(id);
    }


}


