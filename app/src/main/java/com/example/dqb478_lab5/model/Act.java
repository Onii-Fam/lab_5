package com.example.dqb478_lab5.model;

import java.util.List;

/**
 * Represents an act in a theatrical production.
 * An act contains a number and a list of scenes.
 * @author Alfonso Lopez Aquino
 */
public class Act {
    private int actNumber;
    private List<Scene> scenes;

    /**
     * Constructs a new Act with the specified act number.
     *
     * @param actNumber The number of the act.
     * @author Alfonso Lopez Aquino
     */
    public Act(int actNumber) {
        this.actNumber = actNumber;
    }

    /**
     * Gets the number of this act.
     *
     * @return The act number.
     * @author Alfonso Lopez Aquino
     */
    public int getActNumber() {
        return actNumber;
    }

    /**
     * Sets the number of this act.
     *
     * @param actNumber The number to set for this act.
     * @author Alfonso Lopez Aquino
     */
    public void setActNumber(int actNumber) {
        this.actNumber = actNumber;
    }

    /**
     * Gets the list of scenes in this act.
     *
     * @return The list of scenes.
     * @author Alfonso Lopez Aquino
     */
    public List<Scene> getScenes() {
        return scenes;
    }

    /**
     * Sets the list of scenes for this act.
     *
     * @param scenes The list of scenes to be set for this act.
     * @author Alfonso Lopez Aquino
     */
    public void setScenes(List<Scene> scenes) {
        this.scenes = scenes;
    }
}
