package com.example.Stoneware;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText loginUsername, loginPassword;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        Button loginButton = findViewById(R.id.LoginPage_Login_button);
        Button signupRedirect = findViewById(R.id.LoginPage_Signup_button);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        loginButton.setOnClickListener(v -> validateAndLogin());

        signupRedirect.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MainActivity2.class));
            finish();
        });
    }

    private void validateAndLogin() {
        String username = loginUsername.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        if (username.isEmpty()) {
            loginUsername.setError("Username required");
            return;
        }
        if (password.isEmpty()) {
            loginPassword.setError("Password required");
            return;
        }

        databaseReference.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    HelperClass user = snapshot.getValue(HelperClass.class);
                    if (user != null && user.getPassword().equals(password)) {
                        startActivity(new Intent(MainActivity.this, ProgressBarActivity.class)
                                .putExtra("user_data", user)); // ✅ Corrected parentheses
                        finish();
                    } else {
                        loginPassword.setError("Invalid password");
                    }
                } else {
                    loginUsername.setError("User not found");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}