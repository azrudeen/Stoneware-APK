package com.example.Stoneware;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class HomePage extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton toolbar_menu_icon;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);

        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar_menu_icon = findViewById(R.id.toolbar_menu_icon);
        navigationView = findViewById(R.id.navigationView);

        toolbar_menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(navigationView);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation item clicks here
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    // Navigate to HomeActivity when "Home" is clicked
                    startActivity(new Intent(HomePage.this, HomePage.class));
                } else if (itemId == R.id.nav_tiles) {
                    // Navigate to TilesActivity when "Tiles" is clicked
                    startActivity(new Intent(HomePage.this, TilesPage.class));
                } else if (itemId == R.id.nav_granite) {
                    // Navigate to GraniteActivity when "Granite" is clicked
                    startActivity(new Intent(HomePage.this, GranitePage.class));
                } else if (itemId == R.id.nav_marble_stone) {
                    // Navigate to GraniteActivity when "Granite" is clicked
                    startActivity(new Intent(HomePage.this, WhiteMarblePage.class));
                }else if (itemId == R.id.nav_Italian_marbles) {
                    // Navigate to GraniteActivity when "Granite" is clicked
                    startActivity(new Intent(HomePage.this, ItalianMarblesPage.class));
                }else if (itemId == R.id.nav_adhesives) {
                    // Navigate to GraniteActivity when "Granite" is clicked
                    startActivity(new Intent(HomePage.this, AdhesivesPage.class));
                }else if (itemId == R.id.nav_sinks) {
                    // Navigate to GraniteActivity when "Granite" is clicked
                    startActivity(new Intent(HomePage.this, SinksPage.class));
                }else if (itemId == R.id.nav_settings) {
                    // Navigate to GraniteActivity when "Granite" is clicked
                    startActivity(new Intent(HomePage.this, SettingsPage.class));
                }else {
                    // Handle other navigation items if needed
                    Toast.makeText(HomePage.this, "Under development", Toast.LENGTH_SHORT).show();
                }

                drawerLayout.closeDrawer(navigationView); // Close the drawer after selection
                return true;
            }
        });
    }
}
