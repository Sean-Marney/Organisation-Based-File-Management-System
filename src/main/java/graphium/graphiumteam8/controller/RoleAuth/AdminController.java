package graphium.graphiumteam8.controller.RoleAuth;

import graphium.graphiumteam8.entity.User;
import graphium.graphiumteam8.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.jdbc.JdbcUpdateAffectedIncorrectNumberOfRowsException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
// Adam - needs request mapping for all types of admins (so maybe "/management/" before anything else)
public class AdminController {

    @Autowired
    UserRepository users;

    @GetMapping("/management")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SYSTEMADMIN')")
    public String getUsers(Model model) {
        List<User> allUsers = users.findAll();
        model.addAttribute(allUsers);
        return "management"; // Needs to return all users
    }

/*
    @DeleteMapping("/management/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SYSTEMADMIN')")
    public void deleteUser(
   }
*/
    @GetMapping("/management/add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SYSTEMADMIN')")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add-user-form";
    }

    @GetMapping("/management/add-form")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SYSTEMADMIN')")
    public String addUser(User user) {
        users.save(user);
        return "redirect:/management";
        // Implement other 3 CRUD methods (post, delete, put) and decide their authority (hasAuthority)
        // @PreAuthorize("hasAuthority('user:write')") for for each one beacuse they are admin
    }
}
