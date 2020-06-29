package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;


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
        return quizRepository.findById(id);
    }

    @GetMapping(path = "api/quizzes")
    public List<Quiz> getAllQuestions() {
        return quizRepository.getQuizzes();
    }

    @PostMapping(path = "api/quizzes")
    public Quiz addQuestion( @RequestBody Quiz quiz ) {
        quizRepository.addQuiz(quiz);
        h2Repository.save(quiz);
        return quiz;
    }

    @PostMapping(path = "api/quizzes/{id}/solve")
    public Feedback checkQuestion( @RequestBody Quiz quiz,@PathVariable int id ) {
        return quizRepository.getFeedback(quizRepository.findById(id),quiz.getAnswer());
    }

    @PostMapping(path = "/api/register")
    public List<User> addUser( @RequestBody User newUser ) {
        userRepository.findAll()
                .stream().filter(user -> !user.getEmail()
                .contains(newUser.getEmail()))
                .findAny()
                .orElseThrow(UserEmailTakenException::new);

        userRepository.save(newUser);
        return userRepository.findAll();
    }

    @DeleteMapping(path = "api/quizzes/{id}")
    public void deleteQuestion( @PathVariable long id ) {
        h2Repository.deleteById(id);
        quizRepository.deleteQuiz((int) id);
    }

    public static boolean isEmailValid( String email ) {
        final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }
}


