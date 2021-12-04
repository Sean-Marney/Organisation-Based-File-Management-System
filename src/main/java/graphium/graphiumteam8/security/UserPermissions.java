package graphium.graphiumteam8.security;

public enum UserPermissions {
    // Adam - Enums go here for permissions that a role can have (so, read and write)
    ;

    private final String permission;

    UserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    // Adam - create a set containing permissions (Set<SimpleGrantedAuthority> permissions) which adds to the necessary role and return permissions
}
