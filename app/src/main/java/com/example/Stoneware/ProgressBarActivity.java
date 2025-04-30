package com.example.Stoneware;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class ProgressBarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_animation2);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(ProgressBarActivity.this, HomePage.class));
            finish();
        }, 1000); // 5 seconds delay
    }
}