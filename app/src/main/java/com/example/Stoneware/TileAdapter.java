package com.example.Stoneware;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TileAdapter extends RecyclerView.Adapter<TileAdapter.TileViewHolder> {
    private Context context;
    private List<TileModel> tileList;

    public TileAdapter(Context context, List<TileModel> tileList) {
        this.context = context;
        this.tileList = tileList;
    }

    @Override
    public TileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tile, parent, false);
        return new TileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TileViewHolder holder, int position) {
        TileModel tile = tileList.get(position);

        holder.productName.setText(tile.getName());
        holder.productPrice.setText(tile.getPrice());
        holder.productImage.setImageResource(tile.getImageResId());

        // Buy Now Button - Starts a new activity passing the tile details
        holder.buyNowButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("tileData", tile); // Send full object
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return tileList.size();
    }

    // Method to update the list
    public void updateList(List<TileModel> newTileList) {
        tileList = newTileList;
        notifyDataSetChanged(); // Notify adapter that the data set has changed
    }

    // ViewHolder pattern to optimize performance
    public static class TileViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice;
        Button buyNowButton;

        public TileViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            buyNowButton = itemView.findViewById(R.id.buyNowButton);
        }
    }
}