package graphium.graphiumteam8.controller;

import graphium.graphiumteam8.controller.Admin.Form.AccountForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class RegistrationController {


    @GetMapping({"/registration"})
    public String getRegistration(Model model) {
        return "registration";
    }

    @GetMapping("/applicant-register")
    public String serveApplicant(Model model) {
        AccountForm accountForm = new AccountForm();
        model.addAttribute("accountForm", accountForm);
        return "registration";
    }


    @PostMapping("/registerion")
    public String createUser(Model model, @Valid @RequestParam("username") String username, String firstname, String lastname, String email, String password, String organisation) {
//        UserCreationMessage creationMessage = userService.createUserApplicant(firstname, lastname, username, email, password, organisation);

        return "login";
    }

}
