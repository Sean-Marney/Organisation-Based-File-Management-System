package graphium.graphiumteam8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }


    @GetMapping("/login-success")
    public String loginSuccess(HttpServletRequest request) {
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
}


