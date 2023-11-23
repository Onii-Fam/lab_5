package com.example.dqb478_lab5;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.widget.TextView;
import android.widget.Toast;
import android.content.res.AssetManager;


import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.example.dqb478_lab5.model.Role;
import com.example.dqb478_lab5.model.Scene;
import com.example.dqb478_lab5.model.User;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
    private TextView actdist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act);

        textViewScenes = findViewById(R.id.textViewScenes);

        actNumber = getIntent().getIntExtra("ACT_NUMBER", 1); // Default to Act 1 if not specified
        String userName = getIntent().getStringExtra("USER_NAME");

        actdist = findViewById(R.id.act_dis);
        if(actNumber == 1)  {
            actdist.setText("Act 1:");
        } else {
            actdist.setText("Act 2:");
        }
        allUsers = loadUsers(this); // Load all users
        currentUser = retrieveUser(userName);
        //Toast.makeText(this,"Userfound:" + currentUser.getUsername(), Toast.LENGTH_SHORT).show();
        scenes = loadScenesForAct(actNumber, this);

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
                scenesBuilder.append(scene.toString()).append("\n");
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
        for (User user :allUsers) {

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
    private List<Scene> loadScenesForAct(int actNumber, Context context) {
        List<Scene> scenes = new ArrayList<>();
        String fileName = actNumber == 1 ? "act1.txt" : "act2.txt";
        AssetManager asset = context.getAssets();
        try {
            InputStream is = asset.open(fileName);
            Scanner scanner = new Scanner(is);
            //Toast.makeText(this, "loadscenesworking?", Toast.LENGTH_SHORT).show();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" - "); // Split the ID from the rest
                if (parts.length >= 2) {
                    String title = parts[1].split(":")[0].trim(); // Get the title
                    String[] roleNames = parts[1].split(":")[1].split(","); // Get the roles
                    List<Role> roles = new ArrayList<>();
                    for (String roleName : roleNames) {
                        roles.add(new Role(roleName.trim()));
                    }
                    Scene tempscene = new Scene(Integer.parseInt(parts[0].trim()), title, roles);
                    scenes.add(tempscene);
                    //Log.i("trying", tempscene.toString()) ;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException a)
        {
            System.out.println("NULL POINTERERRR");
        }
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
        // Extracting role names within parentheses for the user
        List<String> userRoleNames = user.getRoles().stream()
                .map(Role::getRoleName)
                .map(this::extractStringInParentheses)
                .collect(Collectors.toList());

        for (Role role : scene.getRoles()) {
            String roleNameInParentheses = extractStringInParentheses(role.getRoleName());
            if (userRoleNames.contains(roleNameInParentheses)) {
                return true;
            }
        }
        return false;
    }

    private String extractStringInParentheses(String roleName) {
        int start = roleName.indexOf('(');
        int end = roleName.indexOf(')');
        if (start != -1 && end != -1 && start < end) {
            return roleName.substring(start + 1, end).trim();
        }
        return roleName; // Return original name if no parentheses found
    }


    /**
     * Loads all users from the 'users.csv' file.
     * Each line in the CSV file should contain a username, password, real name, and roles.
     *
     * @return A list of User objects parsed from the CSV file.
     * @author Alfonso Lopez Aquino
     */
    private List<User> loadUsers(Context context) {
        List<User> userList = new ArrayList<>();
        AssetManager asset = context.getAssets();
        try {
            InputStream is = asset.open("users.csv");
            Scanner reader = new Scanner(is);
            String line;
            while (reader.hasNextLine()) {
                line = reader.nextLine();
                String[] tokens = line.split(",");
                if (tokens.length >= 3) {
                    String username = tokens[0].trim();
                    String password = tokens[1].trim();
                    String realName = tokens[2].trim(); // Added trim() here too
                    List<Role> roles = new ArrayList<>();
                    for (int i = 3; i < tokens.length; i++) {
                        roles.add(new Role(tokens[i].trim())); // Added trim() here for consistency
                    }
                    User temp = new User(username, password, realName, roles);
                    userList.add(temp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    };
}
