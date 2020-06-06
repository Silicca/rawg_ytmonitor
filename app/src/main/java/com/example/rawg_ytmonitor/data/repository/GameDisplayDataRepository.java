package com.example.rawg_ytmonitor.data.repository;

import com.example.rawg_ytmonitor.data.apimodel.ApiSearchResponse;
import com.example.rawg_ytmonitor.data.apimodel.Game;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;

public class GameDisplayDataRepository implements GameDisplayRepository{

    private GameDisplayLocalDataSource gameDisplayLocalDataSource;
    private GameDisplayRemoteDataSource gameDisplayRemoteDataSource;

    public GameDisplayDataRepository(GameDisplayLocalDataSource gameDisplayLocalDataSource,
                                      GameDisplayRemoteDataSource gameDisplayRemoteDataSource) {
        this.gameDisplayLocalDataSource = gameDisplayLocalDataSource;
        this.gameDisplayRemoteDataSource = gameDisplayRemoteDataSource;
    }

    @Override
    public Single<ApiSearchResponse> getApiSearchResponse(String keywords) {
        return gameDisplayRemoteDataSource.getApiSearchResponse(keywords)
                .zipWith(gameDisplayLocalDataSource.getFavoriteIdList(), new BiFunction<ApiSearchResponse, List<String>, ApiSearchResponse>() {
                    @Override
                    public ApiSearchResponse apply(ApiSearchResponse apiSearchResponse, List<String> idList) throws Exception {
                        for (Game game : apiSearchResponse.getGameList()) {
                            if (idList.contains(game.getId())) {
                                game.setFavorite(true);
                            }
                        }
                        return apiSearchResponse;
                    }
                });
    }

    @Override
    public Flowable<List<Game>> getFavorites() {
        return null;
    }

    @Override
    public Completable addToFavorites(String id) {
        return null;
    }

    @Override
    public Completable removeFromFavorites(String id) {
        return null;
    }
}
