package graphium.graphiumteam8.controller;

import graphium.graphiumteam8.entity.User;
import graphium.graphiumteam8.repository.UserRepository;
import graphium.graphiumteam8.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RegistrationController {

    private final UserRepository userRepository;
    private final UserService userService;

    public RegistrationController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegistrationForm(Model model){

        // Create new user object to be sent to form
        model.addAttribute("user", new User());

        return "registration-form";
    }

    @PostMapping("/register-form")
    public String registerForm(User user){

        userRepository.save(user); // Save new user to database

        return "register-form-result";
    }

    @GetMapping("/users")
    public String getUsers(Model model){

        List<String> listOfUsernames = userService.listUsernames();
        model.addAttribute("listOfUsernames", listOfUsernames);

        return "users";
    }
}
