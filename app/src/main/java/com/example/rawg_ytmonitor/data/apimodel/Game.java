package com.example.rawg_ytmonitor.data.apimodel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Game {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @Expose
    private String name;

    @SerializedName("background_image")
    @ColumnInfo(name = "image_url")
    @Expose
    private String imageUrl;

    private boolean favorite;

    @SerializedName("rating")
    @Expose
    private String note;

    //rawg game ID
    @SerializedName("id")
    @ColumnInfo(name = "game_id")
    @Expose
    private int gameId;
}
