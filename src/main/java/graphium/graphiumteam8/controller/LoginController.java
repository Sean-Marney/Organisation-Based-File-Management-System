package graphium.graphiumteam8.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getAuthorities());
//        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("USER"))){
//            System.out.println("It's a User");
//            return "redirect:/user";
//        } else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ORGANISATION"))){
//            System.out.println("It's a organisation");
//            return "redirect:/organisation";
//        }
//        if (request.isUserInRole("ROLE_ADMIN")) {
//            return "redirect:/admin";
//        } else if (request.isUserInRole("ROLE_ORGANISATION")) {
//            return "redirect:/organisation";
//        } else if (request.isUserInRole("ROLE_USER")) {
//            return "redirect:/user";
            return "redirect:/";

        }
    }

//    @GetMapping("/organisation")
//    public String organisation() {
//        return "organisation";
//    }
//
//    @GetMapping("/user")
//    public String user() {
//        return "user";
//    }

//    @GetMapping("/register")
//    public String register() {
//        return "register";
//    }



