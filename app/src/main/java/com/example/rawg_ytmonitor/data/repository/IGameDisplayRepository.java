package com.example.rawg_ytmonitor.data.repository;

import com.example.rawg_ytmonitor.data.apimodel.ApiSearchResponse;
import com.example.rawg_ytmonitor.data.apimodel.ApiVideoResponse;
import com.example.rawg_ytmonitor.data.apimodel.Game;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface IGameDisplayRepository {
    Single<ApiSearchResponse> getApiSearchResponse(String keywords);

    Single<List<Game>> getFavorites();

    Completable addToFavorites(Game game);

    Completable removeFromFavorites(int id);

    Single<ApiVideoResponse> getVideos(int gameId);
}
