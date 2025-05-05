package com.example.Stoneware;

import android.os.Parcel;
import android.os.Parcelable;

public class TileModel implements Parcelable {
    private String name;
    private String price;
    private int imageResId;

    public TileModel(String name, String price, int imageResId) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
    }

    protected TileModel(Parcel in) {
        name = in.readString();
        price = in.readString();
        imageResId = in.readInt();
    }

    public static final Creator<TileModel> CREATOR = new Creator<TileModel>() {
        @Override
        public TileModel createFromParcel(Parcel in) {
            return new TileModel(in);
        }

        @Override
        public TileModel[] newArray(int size) {
            return new TileModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(price);
        dest.writeInt(imageResId);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}