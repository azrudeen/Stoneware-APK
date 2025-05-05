package com.example.Stoneware;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView productImage;
    TextView productTitle, productPrice, productDescription;
    Button buyNowButton, addToCartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        productImage = findViewById(R.id.productImage);
        productTitle = findViewById(R.id.productTitle);
        productPrice = findViewById(R.id.productPrice);
        productDescription = findViewById(R.id.productDescription);
        buyNowButton = findViewById(R.id.buyNowButton);
        addToCartButton = findViewById(R.id.addToCartButton);

        TileModel tile = getIntent().getParcelableExtra("tileData");

        if (tile != null) {
            productImage.setImageResource(tile.getImageResId());
            productTitle.setText(tile.getName());
            productPrice.setText(tile.getPrice());
            productDescription.setText("This is a beautiful product: " + tile.getName() + ". High quality and durable!");
        }
    }
}