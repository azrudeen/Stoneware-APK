package com.example.Stoneware;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;

public class TilesPage extends BaseActivity {   // <-- extend BaseActivity

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiles_page);  // <-- only one layout

        setupToolbar(); // <-- setup toolbar with title "Tiles"
    }
}