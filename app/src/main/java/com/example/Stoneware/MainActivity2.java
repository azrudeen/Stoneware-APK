package com.example.Stoneware;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {

    EditText signupName, signupUsername, signupEmail, signupPassword;
    Button SignupPage_Login_button;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        signupName = findViewById(R.id.Signup_name);
        signupEmail = findViewById(R.id.Signup_email);
        signupUsername = findViewById(R.id.Signup_username);
        signupPassword = findViewById(R.id.Signup_password);
        SignupPage_Login_button = findViewById(R.id.SignupPage_Login_button);
        signupButton = findViewById(R.id.SignupPage_Signup_button);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String name = signupName.getText().toString();
                String email = signupEmail.getText().toString();
                String username = signupUsername.getText().toString();
                String password = signupPassword.getText().toString();

                HelperClass helperClass = new HelperClass(name, email, username, password);
                reference.child(username).setValue(helperClass);

                Toast.makeText(MainActivity2.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        SignupPage_Login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}














        // Initialize DatabaseHelper
   /*    dbHelper = new DatabaseHelper(this);

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
        }*/


