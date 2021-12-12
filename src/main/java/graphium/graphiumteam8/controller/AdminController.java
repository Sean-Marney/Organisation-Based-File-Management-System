package graphium.graphiumteam8.controller;

import graphium.graphiumteam8.entity.Role;
import graphium.graphiumteam8.entity.User;
import graphium.graphiumteam8.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequiredArgsConstructor
public class AdminController {
    private final UserRepository users;

    @GetMapping("/management")
    public String getUsers(Model model) {
        List<User> allUsers = users.findAll();
        model.addAttribute(allUsers);
        return "management";
    }

    @DeleteMapping("/management/delete/{id}")
    public void deleteUser(@PathVariable int id) {
        users.deleteById(id);
    }

    @GetMapping("/management/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add-user-form";
    }

    @PostMapping("/management/add-form")
    public String addUser(User user) {
        user.setEnabled(true);
        user.setRole(Role.USER);
        users.save(user);
        return "redirect:/management";
        // Implement other 3 CRUD methods (post, delete, put) and decide their authority (hasAuthority)
        // @PreAuthorize("hasAuthority('user:write')") for each one since they are admin
    }
}
