package graphium.graphiumteam8.controller.RoleAuth;

import graphium.graphiumteam8.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
// Adam - needs request mapping for all types of admins (so maybe "/management/" before anything else)
public class AdminController {

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SYSTEMADMIN')")
    public List<User> getUsers(){
        return null; // Needs to return all users
    }

    // Implement other 3 CRUD methods (post, delete, put) and decide their authority (hasAuthority)
    // @PreAuthorize("hasAuthority('user:write')") for for each one beacuse they are admin
}
