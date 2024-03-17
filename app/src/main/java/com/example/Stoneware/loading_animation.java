package com.example.Stoneware;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class loading_animation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_animation);

        // Find the ImageView by its ID
        ImageView loadingImageView = findViewById(R.id.loadingGif);

        // Load the GIF into the ImageView using Glide
        Glide.with(this)
                .load(R.drawable.loading_animation) // Replace with the name of your GIF file
                .into(loadingImageView); // Pass the ImageView object
    }
}
