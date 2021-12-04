package graphium.graphiumteam8.security;

import java.util.Set;

public enum UserRoles {
    // Adam - create enum for each role (USER, ADMIN, SYSTEMADMIN) here
    ;

    private final Set<UserPermissions> permissions;

    UserRoles(Set<UserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermissions> getPermissions() {
        return permissions;
    }


}
