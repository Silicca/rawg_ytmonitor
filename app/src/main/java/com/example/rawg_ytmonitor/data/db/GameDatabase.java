package com.example.rawg_ytmonitor.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.rawg_ytmonitor.data.apimodel.Game;

@Database(entities = {Game.class} , version = 1, exportSchema = false)
public abstract class GameDatabase extends RoomDatabase {
    public abstract GameDao gameDao();
}
