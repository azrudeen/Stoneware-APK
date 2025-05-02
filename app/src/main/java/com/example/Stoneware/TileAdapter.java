package com.example.Stoneware;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TileAdapter extends RecyclerView.Adapter<TileAdapter.TileViewHolder> {

    private List<TileModel> tileList;

    public TileAdapter(List<TileModel> tileList) {
        this.tileList = tileList;
    }

    public void updateList(List<TileModel> newList) {
        tileList = newList;
        notifyDataSetChanged();
    }

    @Override
    public TileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tile, parent, false);
        return new TileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TileViewHolder holder, int position) {
        TileModel tile = tileList.get(position);
        holder.productName.setText(tile.getName());
        holder.productPrice.setText(tile.getPrice());
        holder.productImage.setImageResource(tile.getImageResId());
    }

    @Override
    public int getItemCount() {
        return tileList.size();
    }

    public static class TileViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice;
        ImageView productImage;

        public TileViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productImage = itemView.findViewById(R.id.productImage);
        }
    }
}