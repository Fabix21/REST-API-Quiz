package engine.services;

import engine.models.Quiz;
import engine.repositories.QuizRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

    @Mock
    QuizRepository quizRepository;
    private QuizService quizService;

    @BeforeEach
    void setUp() {
        quizService = new QuizService(quizRepository);
    }

    @Test
    void shouldAddQuiz() {
        Quiz quiz = new Quiz();
        quiz.setText("Question");
        quiz.setTitle("Quiz Title");
        quiz.setOptions(new String[]{"0","1"});

        quizService.addQuiz(quiz);

        verify(quizRepository).save(quiz);
    }

    @Test
    void shouldFindQuizByID() {
        Quiz quiz = new Quiz();
        quiz.setId(1);
        quiz.setText("Question");
        quiz.setTitle("Quiz Title");
        quiz.setOptions(new String[]{"0","1"});

        when(quizRepository.findAll()).thenReturn(Collections.singletonList(quiz));
        Quiz returnedQuiz = quizService.findById(1);

        assertThat(returnedQuiz).isEqualTo(quiz);
    }

}
