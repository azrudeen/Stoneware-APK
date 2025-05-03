package com.example.Stoneware;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText searchInput;
    private RecyclerView searchRecyclerView;
    private TileAdapter tileAdapter;
    private List<TileModel> fullTileList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search); // We will create this XML next!

        searchInput = findViewById(R.id.search_input);
        searchRecyclerView = findViewById(R.id.search_recycler_view);

        // Get the passed tile list
        fullTileList = getIntent().getParcelableArrayListExtra("tile_list");
        if (fullTileList == null) fullTileList = new ArrayList<>();

        tileAdapter = new TileAdapter(fullTileList);
        searchRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        searchRecyclerView.setAdapter(tileAdapter);

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterTiles(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    private void filterTiles(String text) {
        List<TileModel> filteredList = new ArrayList<>();
        for (TileModel item : fullTileList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        tileAdapter.updateList(filteredList);
    }
}