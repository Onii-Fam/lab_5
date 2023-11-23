package com.example.dqb478_lab5;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dqb478_lab5.model.Role;
import com.example.dqb478_lab5.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * RoleActivity is responsible for displaying the roles of a logged-in user.
 * It allows the user to navigate to the details of Act One or Act Two, or to log out.
 * @author Alfonso Lopez Aquino
 */
public class RoleActivity extends AppCompatActivity {

    /**
     * Initializes the activity, sets up the user interface, and loads user data.
     * Retrieves the username passed from the previous activity and displays the user's roles.
     *
     * @param savedInstanceState Contains the data most recently supplied in onSaveInstanceState(Bundle)
     *                           if the activity is being re-initialized after previously being shut down.
     * @author Alfonso Lopez Aquino
     */
    private TextView textViewUserName;
    private TextView textViewRoles;
    private Button buttonLogout;
    private Button buttonActOne;
    private Button buttonActTwo;
    private User currentUser;
    private List<User> users;
    /**
     * Initializes the activity, sets up the user interface, and loads user data.
     * Retrieves the username passed from the previous activity and displays the user's roles.
     *
     * @param savedInstanceState Contains the data most recently supplied in onSaveInstanceState(Bundle)
     *                           if the activity is being re-initialized after previously being shut down.
     * \@author Alfonso Lopez Aquino
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        textViewUserName = findViewById(R.id.usernameTextView);
        textViewRoles = findViewById(R.id.rolesTextView);
        buttonLogout = findViewById(R.id.logoutButton);
        buttonActOne = findViewById(R.id.actOneButton);
        buttonActTwo = findViewById(R.id.actTwoButton);

        users = loadUsers(this); // Load users

        String userName = getIntent().getStringExtra("USER_NAME");
        currentUser = retrieveUser(userName);

        if (currentUser != null) {
            displayUserInfo(currentUser);
        }

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        buttonActOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToActDetails(1);
            }
        });

        buttonActTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToActDetails(2);
            }
        });
    }

    /**
     * Retrieves a User object based on the provided username.
     *
     * @param userName The username of the user to be retrieved.
     * @return The User object if found, null otherwise.
     * @author Alfonso Lopez Aquino
     */
    private User retrieveUser(String userName) {
        for (User user : users) {
            if (user.getUsername().equals(userName)) {
                return user;
            }
        }
        return null;
    }
    /**
     * Displays the real name and roles of the given user.
     *
     * @param user The User whose information is to be displayed.
     * @author Alfonso Lopez Aquino
     */
    private void displayUserInfo(User user) {
        textViewUserName.setText(user.getRealName());
        StringBuilder rolesBuilder = new StringBuilder();
        for (Role role : user.getRoles()) {
            rolesBuilder.append(role.getRoleName()).append("\n");
        }
        textViewRoles.setText(rolesBuilder.toString());
    }
    /**
     * Navigates to ActActivity to display the scenes of the specified act.
     *
     * @param actNumber The act number for which details are to be displayed.
     * @author Alfonso Lopez Aquino
     */
    private void navigateToActDetails(int actNumber) {
        // Intent to navigate to the Act Details Activity
        Intent intent = new Intent(RoleActivity.this, ActActivity.class);
        //Toast.makeText(this, "CURRENTUSER:" + currentUser.getUsername(), Toast.LENGTH_SHORT).show();
        intent.putExtra("ACT_NUMBER", actNumber);
        intent.putExtra("USER_NAME", currentUser.getUsername().toString());
        startActivity(intent);
    }

    /**
     * Logs out the current user and returns to the MainActivity.
     * @author Alfonso Lopez Aquino
     */
    private void logout() {
        Intent intent = new Intent(RoleActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Close this activity
    }
    /**
     * Loads all users from the 'users.csv' file located in the assets directory.
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
