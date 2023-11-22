package com.example.dqb478_lab5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dqb478_lab5.model.Role;
import com.example.dqb478_lab5.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RoleActivity extends AppCompatActivity {

    private TextView textViewUserName;
    private TextView textViewRoles;
    private Button buttonLogout;
    private Button buttonActOne;
    private Button buttonActTwo;
    private User currentUser; // This will hold the current user's details
    private List<User> users; // Assuming this list is populated with all users

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        textViewUserName = findViewById(R.id.usernameTextView);
        textViewRoles = findViewById(R.id.rolesTextView);
        buttonLogout = findViewById(R.id.logoutButton);
        buttonActOne = findViewById(R.id.actOneButton);
        buttonActTwo = findViewById(R.id.actTwoButton);

        users = loadUsers(); // Load users (ideally this should be done once and passed around or stored globally)

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

    private User retrieveUser(String userName) {
        for (User user : users) {
            if (user.getUsername().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    private void displayUserInfo(User user) {
        textViewUserName.setText(user.getRealName());
        StringBuilder rolesBuilder = new StringBuilder();
        for (Role role : user.getRoles()) {
            rolesBuilder.append(role.getRoleName()).append("\n");
        }
        textViewRoles.setText(rolesBuilder.toString());
    }

    private void navigateToActDetails(int actNumber) {
        // Intent to navigate to the Act Details Activity
        Intent intent = new Intent(RoleActivity.this, ActActivity.class);
        intent.putExtra("ACT_NUMBER", actNumber);
        intent.putExtra("USER_NAME", currentUser.getUsername());
        startActivity(intent);
    }

    private void logout() {
        Intent intent = new Intent(RoleActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Close this activity
    }

    private List<User> loadUsers() {
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
                    User user = new User(username, password, realName, roles);
                    user.setRoles(roles);
                    userList.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }
        return userList;
    }


}
