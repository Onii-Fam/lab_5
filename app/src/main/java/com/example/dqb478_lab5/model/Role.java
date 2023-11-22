package com.example.dqb478_lab5.model;

/**
 * Represents a role in a theatrical production.
 * This class holds information about a specific character or part played by an actor.
 */
public class Role {
    private String roleName;

    /**
     * Constructs a new Role with the specified role name.
     *
     * @param roleName The name of the role.
     */
    public Role(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Gets the name of this role.
     *
     * @return The name of the role.
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the name of this role.
     *
     * @param roleName The name to set for this role.
     */
      {
        this.roleName = roleName;
    }
}
