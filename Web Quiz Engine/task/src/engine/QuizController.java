package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuizController {

    private final QuizRepository quizRepository = new QuizRepository();
    @Autowired
    H2Repository h2Repository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    public QuizController( H2Repository h2Repository ) {
        quizRepository.setQuizzes(h2Repository.findAll());
    }

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
        checkForNulls(quiz);
        quizRepository.addQuiz(quiz);
        h2Repository.save(quiz);
        return quiz;
    }

    private void checkForNulls( @RequestBody Quiz quiz ) {
        if (quiz.getTitle() == null || quiz.getText() == null || quiz.getOptions() == null)
            throw new QuizNullException();
    }

    @PostMapping(path = "api/quizzes/{id}/solve")
    public Feedback checkQuestion( @RequestBody Quiz quiz,@PathVariable int id ) {
        quizRepository.checkForIndexException(id);
        return quizRepository.getFeedback(quizRepository.findById(id),quiz.getAnswer());
    }

    @PostMapping(path = "/api/register")
    public List<User> addUser( @RequestBody User user ) {
        userRepository.save(user);
        return userRepository.findAll();
    }


}


