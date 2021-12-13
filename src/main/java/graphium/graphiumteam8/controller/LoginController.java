package graphium.graphiumteam8.controller;

import graphium.graphiumteam8.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/login-success")
    public String loginSuccess() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getAuthorities());
        switch (((User) auth.getPrincipal()).getRole()) {
            case USER: {
                System.out.println("It's a User");
                return "redirect:/user";
            }
            case ORGANISATION: {
                System.out.println("It's a Organisation");
                return "redirect:/organisation";
            }
            case ADMIN: {
                System.out.println("It's an Admin");
                return "redirect:/management";
            }
        }
        return "redirect:error";
    }
}







