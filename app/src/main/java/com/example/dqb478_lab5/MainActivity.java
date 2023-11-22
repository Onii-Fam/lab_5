package com.example.dqb478_lab5;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private List<User> users; // This will hold the users from the CSV file

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
            // Handle exceptions
        }
        return userList;
    }
}


