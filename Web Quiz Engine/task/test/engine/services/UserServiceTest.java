package engine.services;

import engine.exceptions.InvalidUserEmailException;
import engine.exceptions.UserInvalidInputException;
import engine.models.User;
import engine.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {


    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository,passwordEncoder);
    }


    @Test
    void shouldExpectIncorrectMail() {
        String mail = "INCORRECTMAIL##gmail";

        boolean isEmailValid = UserService.isEmailValid(mail);

        assertThat(isEmailValid).isEqualTo(false);
    }

    @Test
    void shouldExpectCorrectMail() {
        String mail = "wojciech@gmail.com";

        boolean isEmailValid = UserService.isEmailValid(mail);

        assertThat(isEmailValid).isEqualTo(true);
    }

    @Test
    void shouldThrowWhenEmailIsIncorrect() {
        User user = new User();
        user.setEmail("zlymail");
        user.setPassword("haslooo");
        user.setId(1);

        assertThatThrownBy(() -> userService.addUser(user)).isInstanceOf(InvalidUserEmailException.class);
    }

    @Test
    void shouldThrowWhenEmailOrUserIsEmpty() {
        User user = new User();
        user.setId(1);

        assertThatThrownBy(() -> userService.addUser(user)).isInstanceOf(UserInvalidInputException.class);
    }

    @Test
    void shouldAddUser() {
        User user = new User();
        user.setEmail("dobry@gmail.com");
        user.setPassword("bykkanalizacja122");
        user.setId(1);
        when(passwordEncoder.encode(any())).thenReturn("EncodedPassword");

        userService.addUser(user);

        verify(userRepository).save(user);
    }

    @Test
    void shouldGetUsers() {
        List<User> users = List.of(new User(),new User());

        when(userRepository.findAll()).thenReturn(users);
        List<User> listOfUsers = userService.getUsers();

        assertThat(listOfUsers).hasSameElementsAs(users);
    }


}
