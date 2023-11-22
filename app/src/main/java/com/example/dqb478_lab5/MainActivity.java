package com.example.dqb478_lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button logButton = findViewById(R.id.log_button);
    EditText usertext = findViewById(R.id.user_edit);
    EditText passtext = findViewById(R.id.pass_edit);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}