package com.example.rawg_ytmonitor.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.rawg_ytmonitor.data.apimodel.Game;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface IGameDao {

    @Query("SELECT * from Game")
    Flowable<List<Game>> loadFavorites();

    @Insert
    public Completable addToFavorites(Game game);

    @Query("DELETE FROM Game WHERE id = :id")
    public Completable deleteFromFavorites(String id);

    @Query("SELECT id from Game WHERE favorite = 'true'")
    Single<List<String>> getFavoriteIdList();
}
