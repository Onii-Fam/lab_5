package com.example.dqb478_lab5.model;

import java.util.List;

public class Act {
    private int actNumber;
    private List<Scene> scenes;

    public Act(int actNumber) {
        this.actNumber = actNumber;
    }

    // Getter and Setter
    public int getActNumber() {
        return actNumber;
    }

    public void setActNumber(int actNumber) {
        this.actNumber = actNumber;
    }

    public List<Scene> getScenes() {
        return scenes;
    }

    public void setScenes(List<Scene> scenes) {
        this.scenes = scenes;
    }
}

