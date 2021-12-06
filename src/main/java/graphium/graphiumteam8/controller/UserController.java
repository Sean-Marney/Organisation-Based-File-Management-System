package graphium.graphiumteam8.controller;

import graphium.graphiumteam8.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public String getUsers(Model model){

        List<String> listOfUsernames = userService.listUsernames();
        model.addAttribute("listOfUsernames", listOfUsernames);

        return "users";
    }
}
