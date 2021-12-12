package graphium.graphiumteam8.controller;

import graphium.graphiumteam8.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')") // You already enabled Pre/Post Authorize so use them....
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user")
    public String getUser() {
        return "user";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<String> listOfUsernames = userService.listUsernames();
        model.addAttribute("listOfUsernames", listOfUsernames);
        return "users";
    }
}
