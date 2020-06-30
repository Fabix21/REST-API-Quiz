package engine.controllers;

import engine.UserService;
import engine.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path = "/api/register")
    public List<User> addUser( @RequestBody User newUser ) {
        userService.addUser(newUser);
        return userService.getUsers();
    }


}
