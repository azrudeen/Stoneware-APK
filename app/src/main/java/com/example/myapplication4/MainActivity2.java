package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity2 extends AppCompatActivity {

    private EditText firstNameEditText, lastNameEditText, emailEditText, passwordEditText;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Initialize EditText fields
        firstNameEditText = findViewById(R.id.first_name_edit_text);
        lastNameEditText = findViewById(R.id.last_name_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);

        // Initialize Signup Button
        Button signupButton = findViewById(R.id.button3);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }

    private void signup() {
        // Get user input values
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Check if any field is empty
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insert user data into the database
        long result = dbHelper.insertUser(firstName, lastName, email, password);

        if (result != -1) {
            // Signup successful
            Toast.makeText(this, "Signup successful!", Toast.LENGTH_SHORT).show();

            // Redirect to home page
            startActivity(new Intent(MainActivity2.this, HomePage.class));
            finish(); // Finish the current activity to prevent the user from going back to the signup page
        } else {
            // Signup failed
            Toast.makeText(this, "Signup failed!", Toast.LENGTH_SHORT).show();
        }
    }
}
