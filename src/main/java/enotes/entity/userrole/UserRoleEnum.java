package enotes.entity.userrole;

public enum UserRoleEnum {
    USER("User", 1),
    ADMIN("Admin", 2),
    GOD("God", 3);

    private int roleId;
    private String roleName;

    UserRoleEnum(String roleName, int id) {
        this.roleName = roleName;
        this.roleId = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public static UserRoleEnum getRoleByName(String roleName) {
        if (roleName.equals("God")) {
            return GOD;
        } else if (roleName.equals("Admin")) {
            return ADMIN;
        } else {
            return USER;
        }
    }

    public static UserRoleEnum getRoleById(int roleId) {
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
