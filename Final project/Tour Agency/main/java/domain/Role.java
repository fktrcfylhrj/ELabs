package domain;

public enum Role {
    CUSTOMER("role.customer"),
    TRAVELAGENT("role.travelAgent"),
    ADMINISTRATOR("role.admin");

    private String name;

    private Role(String name) {
        this.name = name;
    }

    public Integer getId() {
        return Integer.valueOf(ordinal());
    }

    public String getName() {
        return name;
    }
}
