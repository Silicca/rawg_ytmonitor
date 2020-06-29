package com.example.rawg_ytmonitor.data.repository;

import com.example.rawg_ytmonitor.data.apimodel.ApiSearchResponse;
import com.example.rawg_ytmonitor.data.apimodel.ApiVideoResponse;
import com.example.rawg_ytmonitor.data.apimodel.Game;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;

public class GameDisplayDataRepository implements IGameDisplayRepository {

    private GameDisplayLocalDataSource gameDisplayLocalDataSource;
    private GameDisplayRemoteDataSource gameDisplayRemoteDataSource;

    /**
     * Constructor
     * @param gameDisplayLocalDataSource GameDisplayLocalDataSource Object, the local data source
     * @param gameDisplayRemoteDataSource GameDisplayRemoteDataSource Object, the remote data source
     */
    public GameDisplayDataRepository(GameDisplayLocalDataSource gameDisplayLocalDataSource,
                                     GameDisplayRemoteDataSource gameDisplayRemoteDataSource) {
        this.gameDisplayLocalDataSource = gameDisplayLocalDataSource;
        this.gameDisplayRemoteDataSource = gameDisplayRemoteDataSource;
    }

    @Override
    public Single<ApiSearchResponse> getApiSearchResponse(String keywords) {
        return gameDisplayRemoteDataSource.getApiSearchResponse(keywords)
                .zipWith(gameDisplayLocalDataSource.loadFavorites(), new BiFunction<ApiSearchResponse, List<Game>, ApiSearchResponse>() {
                    @Override
                    public ApiSearchResponse apply(ApiSearchResponse apiSearchResponse, List<Game> gameList) throws Exception {
                        for (Game game : apiSearchResponse.getGameList()) {
                            for (Game game1 : gameList){
                                if (game1.getGameId() == game.getGameId()) {
                                    game.setId(game1.getId());
                                    game.setFavorite(true);
                                    break;
                                }
                            }
                        }
                        return apiSearchResponse;
                    }
                });
    }

    @Override
    public Single<List<Game>> getFavorites() {
        return gameDisplayLocalDataSource.loadFavorites();
    }

    @Override
    public Completable addToFavorites(Game game) {
        return gameDisplayLocalDataSource.addToFavorites(game);
    }

    @Override
    public Completable removeFromFavorites(int id) {
        return gameDisplayLocalDataSource.removeFromFavorites(id);
    }

    @Override
    public Single<ApiVideoResponse> getVideos(int gameId) {
        return gameDisplayRemoteDataSource.getApiVideoResponse(gameId);
    }
}
