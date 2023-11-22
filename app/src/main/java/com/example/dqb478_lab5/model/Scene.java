package com.example.dqb478_lab5.model;

import java.util.List;

public class Scene {
    private int sceneID;
    private String title;
    private List<Role> roles;

    public Scene(int sceneID, String title) {
        this.sceneID = sceneID;
        this.title = title;
    }

    // Getter and Setter
    public int getSceneID() {
        return sceneID;
    }

    public void setSceneID(int sceneID) {
        this.sceneID = sceneID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
