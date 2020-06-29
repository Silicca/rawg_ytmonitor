package com.example.rawg_ytmonitor.data.repository;

import com.example.rawg_ytmonitor.data.apimodel.Game;
import com.example.rawg_ytmonitor.data.db.GameDatabase;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class GameDisplayLocalDataSource {

    private GameDatabase gameDatabase;

    /**
     * Constructor
     * @param gameDatabase GameDatabase object
     */
    public GameDisplayLocalDataSource(GameDatabase gameDatabase) {
        this.gameDatabase = gameDatabase;
    }

    /**
     * Load the list of favorite games
     * @return Single list of Game object
     */
    public Single<List<Game>> loadFavorites() {
        return gameDatabase.gameDao().loadFavorites();
    }

    /**
     * Add a game to favorite
     * @param game Game object
     * @return Completable object
     */
    public Completable addToFavorites(Game game) {
        return gameDatabase.gameDao().addToFavorites(game);
    }

    /**
     * Remove a game, with his id, from the list of favorite games
     * @param id the int id of the game
     * @return Completable object
     */
    public Completable removeFromFavorites(int id) {
        return gameDatabase.gameDao().removeFromFavorites(id);
    }
}
