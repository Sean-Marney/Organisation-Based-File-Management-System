package graphium.graphiumteam8.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static graphium.graphiumteam8.security.UserPermissions.*;

public enum UserRoles {
    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(USER_READ, USER_WRITE)),
    SYSTEMADMIN(Sets.newHashSet (SYSTEMADMIN_READ, SYSTEMADMIN_WRITE));

    private final Set<UserPermissions> permissions;

    UserRoles(Set<UserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermissions> getPermissions() {
        return permissions;
    }



}
