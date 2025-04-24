package com.example.Stoneware;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_tile, parent, false); // tile_item is your card layout XML
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