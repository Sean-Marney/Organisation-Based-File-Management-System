package graphium.graphiumteam8.controller;

import graphium.graphiumteam8.entity.User;
import graphium.graphiumteam8.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
