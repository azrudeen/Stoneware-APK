package com.example.Stoneware;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SinksPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinks_page);

        RecyclerView recyclerView = findViewById(R.id.sinkRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        List<SinkModel> sinkList = new ArrayList<>();
        sinkList.add(new SinkModel("InArt Waterfall Kitchen Sink – Stainless Steel, Black Finish, 30x18 Inches, Single Bowl with Pull-Out Faucet & RO Tap\n", R.drawable.sink_image_1, "Rs 32,000.00"));
        sinkList.add(new SinkModel("Classic Sink", R.drawable.sink_image_2, "Rs 28,000.00"));
        sinkList.add(new SinkModel("Classic Sink", R.drawable.sink_image_3, "Rs 28,000.00"));
        // Add more sink items here...

        SinkAdapter adapter = new SinkAdapter(this, sinkList);
        recyclerView.setAdapter(adapter);
    }
}