package com.example.Stoneware;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BaseActivity {

    DrawerLayout drawerLayout;
    ImageButton toolbar_menu_icon, searchButton;
    NavigationView navigationView;
    ViewPager2 viewPager;
    RecyclerView recyclerView;
    BottomNavigationView bottomNavigationView;
    Handler handler;
    Runnable autoScrollRunnable;

    private androidx.appcompat.widget.SearchView searchView;
    private TileAdapter tileAdapter;
    private List<TileModel> fullTileList;
    private List<TileModel> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);

        setupToolbar();

        // Initialize Views
        viewPager = findViewById(R.id.heroViewPager);
        recyclerView = findViewById(R.id.productRecycler);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar_menu_icon = findViewById(R.id.toolbar_menu_icon);
        navigationView = findViewById(R.id.navigationView);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        searchButton = findViewById(R.id.search_button); // Button to toggle SearchView visibility

        // Hero Banner
        setupHeroBanner();

        // RecyclerView
        setupRecyclerView();

        // SearchView
        setupSearchView();

        // Filter Buttons
        setupFilterButtons();

        // Bottom Navigation
        setupBottomNavigation();

        // Drawer Navigation
        setupDrawerNavigation();
    }

    private void setupHeroBanner() {
        List<BannerItem> banners = new ArrayList<>();
        banners.add(new BannerItem(R.drawable.banner1, "Find Your Perfect \nTile Aesthetic"));
        banners.add(new BannerItem(R.drawable.banner2, "Tiles That Shine"));
        banners.add(new BannerItem(R.drawable.banner3, "Affordable Luxury"));
        banners.add(new BannerItem(R.drawable.banner4, "Tiles That Shine"));
        banners.add(new BannerItem(R.drawable.banner5, "Affordable Luxury"));

        BannerAdapter adapter = new BannerAdapter(banners);
        viewPager.setAdapter(adapter);

        handler = new Handler();
        autoScrollRunnable = new Runnable() {
            int currentPage = 0;

            @Override
            public void run() {
                if (currentPage == banners.size()) currentPage = 0;
                viewPager.setCurrentItem(currentPage++, true);
                handler.postDelayed(this, 5000);
            }
        };
        handler.postDelayed(autoScrollRunnable, 5000);
    }

    private void setupRecyclerView() {
        fullTileList = new ArrayList<>();
        fullTileList.add(new TileModel("Marble White", "₹950", R.drawable.pngtree_marble_natural_breccia));
        fullTileList.add(new TileModel("Black Granite", "₹650", R.drawable.tiles_image_8));
        fullTileList.add(new TileModel("Wood Finish", "₹870", R.drawable.tiles_image_1));
        fullTileList.add(new TileModel("Sink", "₹340", R.drawable.sink_images_inart_waterfall_kitchen_sink));
        fullTileList.add(new TileModel("Marble White", "₹950", R.drawable.pngtree_marble_natural_breccia));
        fullTileList.add(new TileModel("Black Granite", "₹650", R.drawable.tiles_image_8));
        fullTileList.add(new TileModel("Wood Finish", "₹870", R.drawable.tiles_image_1));
        fullTileList.add(new TileModel("Sink", "₹340", R.drawable.sink_images_inart_waterfall_kitchen_sink));

        tileAdapter = new TileAdapter(fullTileList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(tileAdapter);
        recyclerView.setHasFixedSize(true);
    }

    private void setupSearchView() {
        searchView = findViewById(R.id.search_view); // This ID corresponds to the SearchView in your layout
        searchView.setVisibility(View.GONE); // Initially hide the SearchView

        // Set up search query listener
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterTiles(query); // Call your filter function
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterTiles(newText); // Filter as the user types
                return true;
            }
        });

        // Toggle SearchView visibility when the search button is clicked
        searchButton.setOnClickListener(view -> {
            if (searchView.getVisibility() == View.VISIBLE) {
                searchView.setVisibility(View.GONE);
                // Reset to the full list when search is closed
                resetRecyclerView();
            } else {
                searchView.setVisibility(View.VISIBLE);
                searchView.setIconified(false); // Expand SearchView immediately
            }
        });
    }

    private void resetRecyclerView() {
        // Reset the adapter data to the full list of tiles
        tileAdapter.updateList(fullTileList);  // Assuming you have the original list saved in fullTileList
    }

    private void filterTiles(String text) {
        filteredList = new ArrayList<>();
        for (TileModel item : fullTileList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        tileAdapter.updateList(filteredList);
    }

    private void setupFilterButtons() {
        MaterialButton btnPopular = findViewById(R.id.Popular);
        MaterialButton btnFilter = findViewById(R.id.Filter);
        MaterialButton btnNewest = findViewById(R.id.Newest);
        MaterialButton btnPrice = findViewById(R.id.PriceHighToLow);

        List<MaterialButton> allButtons = new ArrayList<>();
        allButtons.add(btnPopular);
        allButtons.add(btnFilter);
        allButtons.add(btnNewest);
        allButtons.add(btnPrice);

        View.OnClickListener toggleListener = view -> {
            for (MaterialButton button : allButtons) {
                button.setChecked(button.getId() == view.getId());
            }
            view.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100).withEndAction(() -> {
                view.animate().scaleX(1f).scaleY(1f).setDuration(100).start();
            }).start();

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

        btnPopular.setOnClickListener(toggleListener);
        btnFilter.setOnClickListener(toggleListener);
        btnNewest.setOnClickListener(toggleListener);
        btnPrice.setOnClickListener(toggleListener);
    }

    private void setupBottomNavigation() {
        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{-android.R.attr.state_checked}
                },
                new int[]{
                        Color.parseColor("#FFFFFF"), // Selected color
                        Color.parseColor("#00000000")  // Unselected color
                }
        );

        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setItemTextColor(colorStateList);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            Intent intent = null;
            if (id == R.id.nav_home) {
                return true;
            } else if (id == R.id.nav_orders) {
                intent = new Intent(HomePage.this, SettingsPage.class);
            } else if (id == R.id.nav_tiles) {
                intent = new Intent(HomePage.this, TilesPage.class);
            } else if (id == R.id.nav_granite) {
                intent = new Intent(HomePage.this, GranitePage.class);
            } else if (id == R.id.nav_italian) {
                intent = new Intent(HomePage.this, ItalianMarblesPage.class);
            } else if (id == R.id.nav_calculator) {
                intent = new Intent(HomePage.this, SquareFeetCalculator.class);
            } else if (id == R.id.nav_settings) {
                intent = new Intent(HomePage.this, SettingsPage.class);
            }
            if (intent != null) {
                startActivity(intent);
                return true;
            }
            return false;
        });
    }

    private void setupDrawerNavigation() {
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            Intent intent = null;

            if (id == R.id.nav_home) {
                intent = new Intent(HomePage.this, HomePage.class);
            } else if (id == R.id.nav_tiles) {
                intent = new Intent(HomePage.this, TilesPage.class);
            } else if (id == R.id.nav_granite) {
                intent = new Intent(HomePage.this, GranitePage.class);
            } else if (id == R.id.nav_marble_stone) {
                intent = new Intent(HomePage.this, WhiteMarblePage.class);
            } else if (id == R.id.nav_Italian_marbles) {
                intent = new Intent(HomePage.this, ItalianMarblesPage.class);
            } else if (id == R.id.nav_adhesives) {
                intent = new Intent(HomePage.this, AdhesivesPage.class);
            } else if (id == R.id.nav_sinks) {
                intent = new Intent(HomePage.this, SinksPage.class);
            } else if (id == R.id.nav_calculator) {
                intent = new Intent(HomePage.this, SquareFeetCalculator.class);
            } else if (id == R.id.nav_settings) {
                intent = new Intent(HomePage.this, SettingsPage.class);
            } else if (id == R.id.nav_help_feedback || id == R.id.nav_shop_login) {
                Toast.makeText(this, "Under Development", Toast.LENGTH_SHORT).show();
            }

            if (intent != null) {
                startActivity(intent);
            }

            drawerLayout.closeDrawers();
            return true;
        });
    }

    public void showPopMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_profile, popupMenu.getMenu());
        popupMenu.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null && autoScrollRunnable != null) {
            handler.removeCallbacks(autoScrollRunnable);
        }
    }
}