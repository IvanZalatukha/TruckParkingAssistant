package domain;

public enum Role {
    USER(1),
    ADMIN(2),
    WITHOUT_ROLE(3);

    private final int id;

    Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Role resolveRoleById(int id) {
        for (Role role : Role.values()) {
            if (id == role.id) {
                return role;
            }
        }
//        TODO add another Exception
        throw new RuntimeException("Role with this number does not exist");
    }

    public static int resolveIdByRole(Role externalRole) {
        for (Role role : Role.values()) {
            if (externalRole.equals(role)) {
                return role.getId();
            }
        }
//        TODO add another Exception
        throw new RuntimeException("Role with this number does not exist");
    }

}