package engine.services;

import engine.exceptions.UserEmailTakenException;
import engine.models.User;
import engine.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    private static boolean isEmailValid( String email ) {
        final Pattern emailRegex = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",Pattern.CASE_INSENSITIVE);
        return emailRegex.matcher(email).matches();
    }

    public void addUser( User newUser ) {
        userRepository.findAll()
                      .stream()
                      .filter(user -> !user.getEmail().contains(newUser.getEmail()))
                      .filter(user -> isEmailValid(newUser.getEmail()))
                      .filter(user -> newUser.getPassword().length() >= 5).findAny()
                      .orElseThrow(UserEmailTakenException::new);
        userRepository.save(newUser);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername( String email ) throws UsernameNotFoundException {
        return null;
    }
}
