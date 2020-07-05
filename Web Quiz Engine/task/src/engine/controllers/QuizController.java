package engine.controllers;

import engine.models.Feedback;
import engine.models.Quiz;
import engine.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public Quiz addQuestion( @RequestBody Quiz quiz,Authentication auth ) {
        quiz.setCreatedByUser(auth.getName());
        quizService.addQuiz(quiz);
        return quiz;
    }

    @PostMapping(path = "api/quizzes/{id}/solve")
    public Feedback checkQuestion( @RequestBody Quiz quiz,@PathVariable int id ) {
        return quizService.getFeedback(quizService.findById(id),quiz.getAnswer());
    }


    @DeleteMapping(path = "api/quizzes/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteQuestion( @PathVariable int id,Authentication auth ) {
        if (quizService.findById(id).getCreatedByUser().equals(auth.getName())) {
            quizService.deleteQuiz(id);
        } else throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @GetMapping(value = "api/username")
    @ResponseBody
    public String currentUserName( Authentication authentication ) {
        if (authentication != null) {
            return authentication.getName();
        } else {
            return "";
        }
    }

}


