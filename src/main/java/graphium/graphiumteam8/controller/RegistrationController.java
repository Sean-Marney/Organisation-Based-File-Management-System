package graphium.graphiumteam8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {

//    @GetMapping({"/","registration"})
//    public String registration(Model model) {
//        return "registration";
//    }

    @GetMapping({"/registration"})
    public String getRegistration(Model model) {
        return "registration";
    }

}
