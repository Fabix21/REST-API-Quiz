package engine.services;

import engine.UserPrincipal;
import engine.exceptions.InvalidUserEmailException;
import engine.exceptions.QuizNotFoundException;
import engine.exceptions.UserEmailTakenException;
import engine.exceptions.UserInvalidInputException;
import engine.models.User;
import engine.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService( UserRepository userRepository,PasswordEncoder passwordEncoder ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    protected static boolean isEmailValid( String email ) {
        final Pattern emailRegex = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",Pattern.CASE_INSENSITIVE);
        return emailRegex.matcher(email).matches();
    }
    public void addUser( User newUser ) {
        if (newUser.getEmail() == null || newUser.getPassword() == null) {
            throw new UserInvalidInputException();
        }

        if (!isEmailValid(newUser.getEmail())) {
            throw new InvalidUserEmailException();
        }

        if (!(newUser.getPassword().length() >= 5)) {
            throw new UserEmailTakenException();
        }

        if (userRepository != null) {
            userRepository.findAll()
                          .stream()
                          .filter(user -> user.getEmail().equals(newUser.getEmail()))
                          .findAny().ifPresent(user -> {
                throw new UserEmailTakenException();
            });
        }

        String password = newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setPassword(password);
        userRepository.save(newUser);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername( String email ) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new QuizNotFoundException("email");
        }
        return new UserPrincipal(user);
    }


}
