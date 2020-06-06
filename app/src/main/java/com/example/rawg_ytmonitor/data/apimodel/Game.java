package com.example.rawg_ytmonitor.data.apimodel;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Game {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "game_id")
    private String id;

    private String name;

    @ColumnInfo(name = "image_url")
    private String imageUrl;

    private boolean favorite = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public void setFavorite() {
        if (favorite==true){
            this.favorite = false;
        } else {
            this.favorite = true;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
