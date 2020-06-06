package com.example.rawg_ytmonitor.data.apimodel;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Game {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "game_id")
    private String id;

    private String name;

    @ColumnInfo(name = "image_url")
    private String imageUrl;

    private boolean favorite;
}
