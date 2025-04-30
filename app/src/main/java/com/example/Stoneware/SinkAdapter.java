package com.example.Stoneware;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SinkAdapter extends RecyclerView.Adapter<SinkAdapter.SinkViewHolder> {

    private Context context;
    private List<SinkModel> sinkList;

    public SinkAdapter(Context context, List<SinkModel> sinkModelList) {
        this.context = context;
        this.sinkList = sinkModelList;
    }

    @NonNull
    @Override
    public SinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sinkssss, parent, false);
        return new SinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SinkViewHolder holder, int position) {
        SinkModel sinkModel = sinkList.get(position);

        holder.sinkImage.setImageResource(sinkModel.getImageResId());
        holder.sinkName.setText(sinkModel.getName());
        holder.sinkPrice.setText(sinkModel.getPrice());

        // Set checkbox state and icon
        holder.checkBox.setChecked(sinkModel.isFavorite());
        holder.checkBox.setButtonDrawable(null); // Use background instead of button drawable
        holder.checkBox.setBackgroundResource(
                sinkModel.isFavorite() ? R.drawable.wish_list_icon_fill : R.drawable.wish_list_icon_nofill
        );

        // Toggle favorite state
        holder.checkBox.setOnClickListener(v -> {
            boolean newState = !sinkModel.isFavorite();
            sinkModel.setFavorite(newState);
            holder.checkBox.setChecked(newState);
            holder.checkBox.setBackgroundResource(
                    newState ? R.drawable.wish_list_icon_fill : R.drawable.wish_list_icon_nofill
            );
        });
    }

    @Override
    public int getItemCount() {
        return sinkList.size();
    }

    public static class SinkViewHolder extends RecyclerView.ViewHolder {
        ImageView sinkImage;
        TextView sinkName, sinkPrice;
        CheckBox checkBox;
        Button addToCartButton;

        public SinkViewHolder(@NonNull View itemView) {
            super(itemView);
            sinkImage = itemView.findViewById(R.id.imageViewSink);
            sinkName = itemView.findViewById(R.id.textViewSinkTitle);
            sinkPrice = itemView.findViewById(R.id.textViewSinkPrice);
            checkBox = itemView.findViewById(R.id.checkBoxWishlist);
            addToCartButton = itemView.findViewById(R.id.buttonAddCart);
        }
    }
}