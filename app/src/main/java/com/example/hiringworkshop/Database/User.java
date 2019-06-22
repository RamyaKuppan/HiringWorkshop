package com.example.hiringworkshop.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "User")
public class User {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    Long UserID;
    String UserName;
}
