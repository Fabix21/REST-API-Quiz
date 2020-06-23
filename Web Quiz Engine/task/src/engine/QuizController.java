package engine;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
public class QuizController {
    private final AtomicLong counter = new AtomicLong();
    private List<Quiz> quizzes = new ArrayList<>();
    private final static Logger LOGGER = Logger.getLogger(QuizController.class.getName());
    @GetMapping(path = "api/quizzes/{id}")
    public Quiz GetQuestionById( @PathVariable int id ) {
        if (quizzes.size() < id) {
            throw new QuizNotFoundException("Invalid id :" + id);
        }
        return quizzes.stream().filter(quiz -> id == quiz.getId()).findFirst().orElseThrow();

    }

    @GetMapping(path = "api/quizzes")
    public List<Quiz> GetAllQuestions() {
        return quizzes;
    }

    @PostMapping(path = "api/quizzes")
    public Quiz AddQuestion( @RequestBody Quiz quiz ) {

        Quiz addQuestionToRepository = new Quiz.Builder()
                .title(quiz.getTitle())
                .text(quiz.getText())
                .options(quiz.getOptions())
                .answer(quiz.getAnswer())
                .id(counter.incrementAndGet())
                .build();

        quizzes.add(addQuestionToRepository);
        LOGGER.log(Level.INFO,addQuestionToRepository.toString());
        return addQuestionToRepository;

    }

    @PostMapping(path = "api/quizzes/{id}/solve")
    public Feedback CheckQuestion( @RequestParam int answer,@PathVariable int id ) {
        if (quizzes.size() < id) {
            throw new QuizNotFoundException("Invalid id" + id);
        }
        Quiz question = quizzes.stream().filter(quiz -> id == quiz.getId()).findFirst().orElseThrow();

        if (Integer.valueOf(answer).equals(question.getAnswer())) {
            System.out.println(question.getAnswer());
            return new Feedback(true);
        } else return new Feedback(false);
    }
}


