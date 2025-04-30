package com.example.Stoneware;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {

    private EditText signupName, signupUsername, signupEmail, signupPassword;
    private Button signupButton, signupPageLoginButton;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        signupName = findViewById(R.id.Signup_name);
        signupEmail = findViewById(R.id.Signup_email);
        signupUsername = findViewById(R.id.Signup_username);
        signupPassword = findViewById(R.id.Signup_password);
        signupButton = findViewById(R.id.SignupPage_Signup_button);
        signupPageLoginButton = findViewById(R.id.SignupPage_Login_button);

        reference = FirebaseDatabase.getInstance().getReference("users");

        signupButton.setOnClickListener(v -> registerUser());
        signupPageLoginButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity2.this, MainActivity.class));
            finish();
        });
    }

    private void registerUser() {
        String name = signupName.getText().toString().trim();
        String email = signupEmail.getText().toString().trim();
        String username = signupUsername.getText().toString().trim();
        String password = signupPassword.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(MainActivity2.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        reference.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Toast.makeText(MainActivity2.this, "Username already taken", Toast.LENGTH_SHORT).show();
                } else {
                    HelperClass helperClass = new HelperClass(name, email, username, password);
                    reference.child(username).setValue(helperClass)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity2.this, "You have signed up successfully!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(MainActivity2.this, MainActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(MainActivity2.this, "Error creating user", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(MainActivity2.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}