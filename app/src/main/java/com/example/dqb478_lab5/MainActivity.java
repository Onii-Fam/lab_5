package com.example.dqb478_lab5;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
/**
 * MainActivity is the entry point of the application.
 * This activity handles user login by loading user data from a CSV file and validating credentials.
 * Upon successful login, it navigates to the RoleActivity where user roles are displayed.
 * @author Alfonso Lopez Aquino
 */
public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private List<User> users;

    /**
     * Initializes the activity. This method sets up the user interface and loads the users.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this contains the most recent data provided by onSaveInstanceState(Bundle).
     * @author Alfonso Lopez Aquino
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.user_edit);
        editTextPassword = findViewById(R.id.pass_edit);
        buttonLogin = findViewById(R.id.log_button);

        users = loadUsers(); // Load users from CSV

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    /**
     * Handles user login. Validates the entered username and password against the loaded users.
     * If credentials are valid, navigates to the RoleActivity.
     *  @author Alfonso Lopez Aquino
     */
    private void login() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        for (User user : users) {
            if (user.validate(username, password)) {
                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, RoleActivity.class);
                intent.putExtra("USER_NAME", user.getRealName()); // Passing user's real name
                startActivity(intent);
                return;
            }
        }

        Toast.makeText(MainActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
    }
    /**
     * Loads users from the 'users.csv' file in the assets directory.
     * Each line in the CSV file should contain a username, password, real name, and roles.
     *
     * @return List of User objects parsed from the CSV file.
     * @author Alfonso Lopez Aquino
     */
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
                    String realName = tokens[2].trim(); // Added trim() here too
                    List<Role> roles = new ArrayList<>();
                    for (int i = 3; i < tokens.length; i++) {
                        roles.add(new Role(tokens[i].trim())); // Added trim() here for consistency
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


