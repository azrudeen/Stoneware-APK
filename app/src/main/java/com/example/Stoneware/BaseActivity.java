package com.example.Stoneware;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class BaseActivity extends AppCompatActivity {
    protected ImageButton toolbar_menu_icon;
    protected ImageButton homeIcon;
    protected DrawerLayout drawerLayout;
    protected NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setupToolbar() {
        toolbar_menu_icon = findViewById(R.id.toolbar_menu_icon);
        homeIcon = findViewById(R.id.Home_icon);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        if (toolbar_menu_icon != null && drawerLayout != null && navigationView != null) {
            toolbar_menu_icon.setOnClickListener(v -> drawerLayout.openDrawer(navigationView));
            navigationView.setItemIconTintList(null); // To keep color original
            navigationView.setNavigationItemSelectedListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    startActivity(new Intent(this, HomePage.class));
                } else if (itemId == R.id.nav_tiles) {
                    startActivity(new Intent(this, TilesPage.class));
                } else if (itemId == R.id.nav_granite) {
                    startActivity(new Intent(this, GranitePage.class));
                } else if (itemId == R.id.nav_marble_stone) {
                    startActivity(new Intent(this, WhiteMarblePage.class));
                } else if (itemId == R.id.nav_Italian_marbles) {
                    startActivity(new Intent(this, ItalianMarblesPage.class));
                } else if (itemId == R.id.nav_adhesives) {
                    startActivity(new Intent(this, AdhesivesPage.class));
                } else if (itemId == R.id.nav_sinks) {
                    startActivity(new Intent(this, SinksPage.class));
                } else if (itemId == R.id.nav_settings) {
                    startActivity(new Intent(this, SettingsPage.class));
                } else {
                    Toast.makeText(this, "Under development", Toast.LENGTH_SHORT).show();
                }

                drawerLayout.closeDrawer(navigationView);
                return true;
            });
        }

        if (homeIcon != null) {
            homeIcon.setOnClickListener(v -> {
                startActivity(new Intent(this, HomePage.class));
            });
        }
    }
}