package com.example.Stoneware;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdhesivesPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adhesives_page);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{-android.R.attr.state_checked}
                },
                new int[]{
                        Color.parseColor("#FFF9C4"), // Orange for selected
                        Color.parseColor("#00000000")  // Grey for unselected
                }
        );

// Apply the colors
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setItemTextColor(colorStateList);

    }
}