package com.example.Stoneware;

import android.os.Parcel;
import android.os.Parcelable;

public class HelperClass implements Parcelable {

    private String name;
    private String email;
    private String username;
    private String password;

    // Required empty constructor for Firebase
    public HelperClass() {}

    public HelperClass(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    protected HelperClass(Parcel in) {
        name = in.readString();
        email = in.readString();
        username = in.readString();
        password = in.readString();
    }

    public static final Creator<HelperClass> CREATOR = new Creator<HelperClass>() {
        @Override
        public HelperClass createFromParcel(Parcel in) {
            return new HelperClass(in);
        }

        @Override
        public HelperClass[] newArray(int size) {
            return new HelperClass[size];
        }
    };

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(username);
        dest.writeString(password);
    }
}