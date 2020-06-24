package engine;

import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class QuizController {

    private QuizRepository quizRepository = new QuizRepository();

    @GetMapping(path = "api/quizzes/{id}")
    public Quiz getQuestionById( @PathVariable int id ) {
        quizRepository.findById(id);
        if (quizRepository.size() < id) {
            throw new QuizNotFoundException("Invalid id :" + id);
        }
        return quizRepository.findById(id);
    }

    @GetMapping(path = "api/quizzes")
    public List<Quiz> getAllQuestions() {
        return quizRepository.getQuizzes();
    }

    @PostMapping(path = "api/quizzes")
    public Quiz addQuestion( @RequestBody Quiz quiz ) {
        quizRepository.addQuiz(quiz);
        return quiz;
    }

    @PostMapping(path = "api/quizzes/{id}/solve")
    public Feedback checkQuestion( @RequestParam int answer,@PathVariable int id ) {
        if (quizRepository.size() < id) {
            throw new QuizNotFoundException("Invalid id" + id);
        }
        return quizRepository.getFeedback(quizRepository.findById(id),answer);
    }
}


