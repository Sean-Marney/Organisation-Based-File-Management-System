package graphium.graphiumteam8.controller;

import graphium.graphiumteam8.entity.Organisation;
import graphium.graphiumteam8.entity.User;
import graphium.graphiumteam8.service.OrganisationService;
import graphium.graphiumteam8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RegistrationController {

    // Delete this if we dont need it im not sure
//<<<<<<< HEAD
//    private final UserRepository userRepository;
//
//    //encoder in the controller to encode the password
//    @Autowired
//    private PasswordEncoder encoder;
//
//    public RegistrationController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//=======
        @Autowired
    private PasswordEncoder encoder;

    private final UserService userService;
    private final OrganisationService organisationService;

    public RegistrationController(UserService userService, OrganisationService organisationService) {
        this.userService = userService;
        this.organisationService = organisationService;
    }

    @GetMapping("/register")
    public String getRegistrationForm(Model model){

        // TODO i want to assign this to a user
        List<Organisation> availableOrganisations = organisationService.listOrganisations();

        // Create new user object to be sent to form
        model.addAttribute("user", new User());
//        model.addAttribute("organisationList", organisationList);

        // Choose from list of available organisations
        model.addAttribute("availableOrganisations", availableOrganisations);

        return "registration-form";
    }

    @PostMapping("/register-form")
    public String registerForm(User user){


        user.setRole("USER");
        user.setEnabled(Boolean.TRUE);

//      hashing incoming passwords
        user.setPassword(encoder.encode(user.getPassword()));

        userService.saveUser(user); // Save new user to database

        return "register-form-result";
    }

//    //dropdown list
//    static List<String> organisationList = null;
//    static {
//        organisationList = new ArrayList<>();
//        organisationList.add("Organisation 1");
//        organisationList.add("Organisation 2");
//        organisationList.add("Organisation 3");
//        organisationList.add("Organisation 4");
//    }




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
