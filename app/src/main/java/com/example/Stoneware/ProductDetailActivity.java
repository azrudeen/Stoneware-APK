package com.example.Stoneware;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView productName, productPrice;
    private Button buyNowButton;

    private RecyclerView relatedRecyclerView;
    private TileAdapter tileAdapter;
    private List<TileModel> fullTileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        productImage = findViewById(R.id.productImage);
        productName = findViewById(R.id.productTitle);
        productPrice = findViewById(R.id.productPrice);
        buyNowButton = findViewById(R.id.buyNowButton);

        relatedRecyclerView = findViewById(R.id.relatedRecyclerView);

        // Get data from intent
        TileModel tile = getIntent().getParcelableExtra("tileData");
        if (tile != null) {
            productName.setText(tile.getName());
            productPrice.setText(tile.getPrice());
            productImage.setImageResource(tile.getImageResId());
        }

        buyNowButton.setOnClickListener(v -> {
            // Handle buy now action (future logic can go here)
        });

        // Setup related items RecyclerView
        setupRelatedRecyclerView();
    }

    private void setupRelatedRecyclerView() {
        fullTileList = new ArrayList<>();
        fullTileList.add(new TileModel("Marble White", "₹950", R.drawable.pngtree_marble_natural_breccia));
        fullTileList.add(new TileModel("Black Granite", "₹650", R.drawable.tiles_image_8));
        fullTileList.add(new TileModel("Wood Finish", "₹870", R.drawable.tiles_image_1));
        fullTileList.add(new TileModel("Sink", "₹340", R.drawable.sink_images_inart_waterfall_kitchen_sink));
        fullTileList.add(new TileModel("Marble White", "₹950", R.drawable.pngtree_marble_natural_breccia));
        fullTileList.add(new TileModel("Black Granite", "₹650", R.drawable.tiles_image_8));
        fullTileList.add(new TileModel("Wood Finish", "₹870", R.drawable.tiles_image_1));
        fullTileList.add(new TileModel("Sink", "₹340", R.drawable.sink_images_inart_waterfall_kitchen_sink));

        tileAdapter = new TileAdapter(this, fullTileList);
        relatedRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        relatedRecyclerView.setAdapter(tileAdapter);
        relatedRecyclerView.setHasFixedSize(true);
    }
}