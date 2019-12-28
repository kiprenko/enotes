package enotes.user.role;

public enum UserRole {
    USER("User", 1),
    ADMIN("Admin", 2),
    GOD("God", 3);

    private int roleId;
    private String roleName;

    UserRole(String roleName, int id) {
        this.roleName = roleName;
        this.roleId = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public static UserRole getRoleByName(String roleName) {
        if (roleName.equals("God")) {
            return GOD;
        } else if (roleName.equals("Admin")) {
            return ADMIN;
        } else {
            return USER;
        }
    }

    public static UserRole getRoleById(int roleId) {
        switch (roleId) {
            case 3: {
                return GOD;
            }
            case 2: {
                return ADMIN;
            }
            default: {
                return USER;
            }
        }
    }
}
