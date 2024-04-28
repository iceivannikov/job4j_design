package ru.job4j.generic;

public class Role extends Base {
    private final RoleType role;

    public Role(String id, RoleType role) {
        super(id);
        this.role = role;
    }

    public RoleType getRole() {
        return role;
    }
}
