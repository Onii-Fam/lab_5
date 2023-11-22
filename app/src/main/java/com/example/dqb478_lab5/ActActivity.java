package com.example.dqb478_lab5;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.example.dqb478_lab5.model.Role;
import com.example.dqb478_lab5.model.Scene;
import com.example.dqb478_lab5.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * ActActivity displays the scenes in which the current user appears for a selected act.
 * This activity is responsible for loading user data, determining which scenes the user
 * is part of, and displaying those scenes to the user.
 * @author Alfonso Lopez Aquino
 */
public class ActActivity extends AppCompatActivity {

    private TextView textViewScenes;
    private User currentUser;
    private int actNumber;
    private List<Scene> scenes;
    private List<User> allUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act);

        textViewScenes = findViewById(R.id.textViewScenes);

        actNumber = getIntent().getIntExtra("ACT_NUMBER", 1); // Default to Act 1 if not specified
        String userName = getIntent().getStringExtra("USER_NAME");

        allUsers = loadAllUsers(); // Load all users
        currentUser = retrieveUser(userName);
        scenes = loadScenesForAct(actNumber);

        displayScenes();
    }

    /**
     * Builds and displays a list of scenes based on the user's roles.
     * If the user does not have any scenes, it displays "No scenes".
     * @author Alfonso Lopez Aquino
     */
    private void displayScenes() {
        StringBuilder scenesBuilder = new StringBuilder();
        for (Scene scene : scenes) {
            if (isUserInScene(currentUser, scene)) {
                scenesBuilder.append(scene.getTitle()).append("\n");
            }
        }

        if (scenesBuilder.length() == 0) {
            textViewScenes.setText("No scenes");
        } else {
            textViewScenes.setText(scenesBuilder.toString());
        }
    }
    /**
     * Retrieves a user object based on the provided username.
     *
     * @param userName The username of the user to retrieve.
     * @return The User object corresponding to the username, or null if not found.
     * @author Alfonso Lopez Aquino
     */
    private User retrieveUser(String userName) {
        for (User user : allUsers) {
            if (user.getUsername().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Loads the scenes for a specified act.
     *
     * @param actNumber The number of the act for which scenes need to be loaded.
     * @return A list of Scene objects for the specified act.
     * @author Alfonso Lopez Aquino
     */
    private List<Scene> loadScenesForAct(int actNumber) {
        List<Scene> scenes = new ArrayList<>();
        return scenes;
    }
    /**
     * Determines if a user is part of a given scene.
     *
     * @param user  The user whose participation in the scene is to be checked.
     * @param scene The scene to check the user's participation in.
     * @return True if the user is part of the scene, false otherwise.
     * @author Alfonso Lopez Aquino
     */
    private boolean isUserInScene(User user, Scene scene) {
        List<String> userRoleNames = user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList());
        for (Role role : scene.getRoles()) {
            if (userRoleNames.contains(role.getRoleName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Loads all users from the 'users.csv' file.
     * Each line in the CSV file should contain a username, password, real name, and roles.
     *
     * @return A list of User objects parsed from the CSV file.
     * @author Alfonso Lopez Aquino
     */
    private List<User> loadAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            InputStream is = getAssets().open("users.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length >= 3) {
                    String username = tokens[0].trim();
                    String password = tokens[1].trim();
                    String realName = tokens[2].trim();
                    List<Role> roles = new ArrayList<>();
                    for (int i = 3; i < tokens.length; i++) {
                        roles.add(new Role(tokens[i].trim()));
                    }
                    userList.add(new User(username, password, realName, roles));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
