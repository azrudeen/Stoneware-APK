
package com.example.Stoneware;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;
import java.util.ArrayList;
import com.google.android.material.button.MaterialButton;

import com.google.android.material.navigation.NavigationView;

public class HomePage extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton toolbar_menu_icon;
    NavigationView navigationView;



    public void showPopMenu(View View) {
        PopupMenu popupMenu = new PopupMenu(this,View);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_profile, popupMenu.getMenu());
        popupMenu.show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);

        ViewPager2 viewPager = findViewById(R.id.heroViewPager);

        List<BannerItem> banners = new ArrayList<>();
        banners.add(new BannerItem(R.drawable.banner1, "Find Your Perfect \nTile Aesthetic"));
        banners.add(new BannerItem(R.drawable.banner2, "Tiles That Shine"));
        banners.add(new BannerItem(R.drawable.banner3, "Affordable Luxury"));
        banners.add(new BannerItem(R.drawable.banner4, "Tiles That Shine"));
        banners.add(new BannerItem(R.drawable.banner5, "Affordable Luxury"));

        BannerAdapter adapter = new BannerAdapter(banners);
        viewPager.setAdapter(adapter);

// Auto-scroll every 5 seconds
        Handler handler = new Handler();
        Runnable autoScrollRunnable = new Runnable() {
            int currentPage = 0;

            @Override
            public void run() {
                if (currentPage == banners.size()) currentPage = 0;
                viewPager.setCurrentItem(currentPage++, true);
                handler.postDelayed(this, 5000);
            }
        };
        handler.postDelayed(autoScrollRunnable, 5000);

        RecyclerView recyclerView = findViewById(R.id.productRecycler);
        List<TileModel> tileList = new ArrayList<>();

        // Sample tiles
        tileList.add(new TileModel("Marble White", "₹950", R.drawable.pngtree_marble_natural_breccia));
        tileList.add(new TileModel("Black Granite", "₹650", R.drawable.tiles_image_8));
        tileList.add(new TileModel("Wood Finish", "₹870", R.drawable.tiles_image_1));
        tileList.add(new TileModel("Wood Finish", "₹340", R.drawable.sink_images_inart_waterfall_kitchen_sink));
        tileList.add(new TileModel("Marble White", "₹550", R.drawable.tiles_image_1));
        tileList.add(new TileModel("Marble White", "₹250", R.drawable.tiles_image_2));
        tileList.add(new TileModel("Marble White", "₹460", R.drawable.tiles_image_3));
        tileList.add(new TileModel("Marble White", "₹150", R.drawable.tiles_image_4));
        tileList.add(new TileModel("Marble White", "₹220", R.drawable.tiles_image_5));
        tileList.add(new TileModel("Marble White", "₹410", R.drawable.tiles_image_6));
        tileList.add(new TileModel("Marble White", "₹650", R.drawable.tiles_image_7));
        tileList.add(new TileModel("Marble White", "₹260", R.drawable.tiles_image_8));

        TileAdapter tileAdapter = new TileAdapter(this, tileList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(tileAdapter);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar_menu_icon = findViewById(R.id.toolbar_menu_icon);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);
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
        // ✅ Filter Button Toggle Logic
        MaterialButton btnPopular = findViewById(R.id.Popular);
        MaterialButton btnFilter = findViewById(R.id.Filter);
        MaterialButton btnNewest = findViewById(R.id.Newest);
        MaterialButton btnPrice = findViewById(R.id.PriceHighToLow);

        List<MaterialButton> allButtons = new ArrayList<>();
        allButtons.add(btnPopular);
        allButtons.add(btnFilter);
        allButtons.add(btnNewest);
        allButtons.add(btnPrice);

        // Button click listener to toggle checked state and handle actions
        View.OnClickListener toggleListener = view -> {
            for (MaterialButton button : allButtons) {
                button.setChecked(button.getId() == view.getId()); // Toggle checked state
            }

            view.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100).withEndAction(() -> {
                view.animate().scaleX(1f).scaleY(1f).setDuration(100).start();
            }).start();

            // Perform specific action based on the button clicked
            int id = view.getId();
            if (id == R.id.Popular) {
                Toast.makeText(HomePage.this, "Popular clicked", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.Filter) {
                Toast.makeText(HomePage.this, "Filter clicked", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.Newest) {
                Toast.makeText(HomePage.this, "Newest clicked", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.PriceHighToLow) {
                Toast.makeText(HomePage.this, "Price High to Low clicked", Toast.LENGTH_SHORT).show();
            }
        };

        // Set the click listener on all buttons
        btnPopular.setOnClickListener(toggleListener);
        btnFilter.setOnClickListener(toggleListener);
        btnNewest.setOnClickListener(toggleListener);
        btnPrice.setOnClickListener(toggleListener);
    }
}
