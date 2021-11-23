package graphium.graphiumteam8.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping({"/login"})
    public String getLogin(Model model) {
        return "login";
    }

//    @GetMapping("/logout-success")
//    public String logoutSuccess() {
//        return "logged-out";
//    }
    @GetMapping
    public String login(){
        return "account/login";
    }


}
