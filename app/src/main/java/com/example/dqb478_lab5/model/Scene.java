package com.example.dqb478_lab5.model;

import java.util.List;

/**
 * Represents a scene in a theatrical production.
 * A scene is identified by an ID and a title, and it contains a list of roles involved in that scene.
 */
public class Scene {
    private int sceneID;
    private String title;
    private List<Role> roles;

    /**
     * Constructs a new Scene with the specified scene ID and title.
     *
     * @param sceneID The unique identifier for the scene.
     * @param title   The title of the scene.
     */
    public Scene(int sceneID, String title) {
        this.sceneID = sceneID;
        this.title = title;
    }

    /**
     * Gets the scene ID.
     *
     * @return The unique identifier of the scene.
     */
    public int getSceneID() {
        return sceneID;
    }

    /**
     * Sets the scene ID.
     *
     * @param sceneID The unique identifier to set for the scene.
     */
    public void setSceneID(int sceneID) {
        this.sceneID = sceneID;
    }

    /**
     * Gets the title of the scene.
     *
     * @return The title of the scene.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the scene.
     *
     * @param title The title to set for the scene.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the list of roles involved in the scene.
     *
     * @return A list of roles in the scene.
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Sets the list of roles for the scene.
     *
     * @param roles The list of roles to be set for the scene.
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
