package com.example.rawg_ytmonitor.data.repository;

import com.example.rawg_ytmonitor.data.apimodel.ApiSearchResponse;
import com.example.rawg_ytmonitor.data.apimodel.GameDisplayService;

import io.reactivex.Single;

public class GameDisplayRemoteDataSource {

    private GameDisplayService gameDisplayService;

    public GameDisplayRemoteDataSource(GameDisplayService gameDisplayService) {
        this.gameDisplayService = gameDisplayService;
    }

    public Single<ApiSearchResponse> getApiSearchResponse(String keywords) {
        return gameDisplayService.searchGames(keywords);
    }
}
