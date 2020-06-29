package com.example.rawg_ytmonitor.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.rawg_ytmonitor.data.apimodel.Game;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface IGameDao {

    @Query("SELECT * from Game")
    Single<List<Game>> loadFavorites();

    @Insert
    Completable addToFavorites(Game game);

    @Query("DELETE FROM Game WHERE id = :id")
    Completable removeFromFavorites(int id);
}
