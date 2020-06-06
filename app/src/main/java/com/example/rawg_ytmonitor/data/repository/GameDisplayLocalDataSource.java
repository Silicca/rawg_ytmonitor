package com.example.rawg_ytmonitor.data.repository;

import com.example.rawg_ytmonitor.data.apimodel.Game;
import com.example.rawg_ytmonitor.data.db.GameDatabase;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class GameDisplayLocalDataSource {

    private GameDatabase gameDatabase;

    public GameDisplayLocalDataSource(GameDatabase animeDatabase) {
        this.gameDatabase = animeDatabase;
    }

    public Flowable<List<Game>> loadFavorites() {
        return gameDatabase.gameDao().loadFavorites();
    }

    public Completable addToFavorites(Game game) {
        return gameDatabase.gameDao().addToFavorites(game);
    }

    public Completable deleteFromFavorites(String id) {
        return gameDatabase.gameDao().deleteFromFavorites(id);
    }

    public Single<List<String>> getFavoriteIdList() {
        return gameDatabase.gameDao().getFavoriteIdList();
    }

}
