package graphium.graphiumteam8.security;

public enum UserPermissions {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    SYSTEMADMIN_READ("systemadmin:read"),
    SYSTEMADMIN_WRITE("systemadmin:write")

    ;

    private final String permissions;

    UserPermissions(String permissions) {
        this.permissions = permissions;
    }
    public String getPermission(){
        return permissions;
    }
}
