package graphium.graphiumteam8.controller;

import graphium.graphiumteam8.entity.Organisation;
import graphium.graphiumteam8.entity.User;
import graphium.graphiumteam8.repository.UserRepository;
import graphium.graphiumteam8.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
        //model.addAttribute("organisation", new Organisation());

        return "registration-form";
    }

    @PostMapping("/register-form")
    public String registerForm(User user){

        userRepository.save(user); // Save new user to database

        return "register-form-result";
    }










    
    /*
    @GetMapping({"/registration"})
    public String getRegistration(Model model) {
        return "registration";
    }

    @GetMapping("/applicant-register")
    public String serveApplicant(Model model) {
        model.addAttribute("accountForm", new AccountForm());
        return "registration";
    }


    @PostMapping("/registration")
    public String createUser(Model model, @Valid @RequestParam("username") String username, String firstname, String lastname, String email, String password, String organisation) {
//        UserCreationMessage creationMessage = userService.createUserApplicant(firstname, lastname, username, email, password, organisation);

        return "login";
    }

     */
}
