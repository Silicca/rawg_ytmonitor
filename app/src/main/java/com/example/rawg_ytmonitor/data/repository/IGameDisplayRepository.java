package com.example.rawg_ytmonitor.data.repository;

import com.example.rawg_ytmonitor.data.apimodel.ApiSearchResponse;
import com.example.rawg_ytmonitor.data.apimodel.Game;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface IGameDisplayRepository {
    Single<ApiSearchResponse> getApiSearchResponse(String keywords);

    Flowable<List<Game>> getFavorites();

    Completable addToFavorites(String id);

    Completable removeFromFavorites(String id);
}
