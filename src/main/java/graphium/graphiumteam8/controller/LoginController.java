package graphium.graphiumteam8.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
//@RequestMapping("/login")
public class LoginController {

    @GetMapping({"/login"})
    public String getLogin(Model model) {
        return "login";
    }


    @GetMapping("/logout-success")
    public String logoutSuccess(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin";
        } else if (request.isUserInRole("ROLE_ORGANISATION")) {
            return "redirect:/organisation";
        } else if (request.isUserInRole("ROLE_USER")) {
            return "redirect:/user";
        }else {
            return "redirect:/error";
        }
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
