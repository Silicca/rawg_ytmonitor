package com.example.rawg_ytmonitor.data.repository;

import com.example.rawg_ytmonitor.data.apimodel.ApiSearchResponse;
import com.example.rawg_ytmonitor.data.apimodel.IGameDisplayService;

import io.reactivex.Single;

public class GameDisplayRemoteDataSource {

    private IGameDisplayService IGameDisplayService;

    public GameDisplayRemoteDataSource(IGameDisplayService IGameDisplayService) {
        this.IGameDisplayService = IGameDisplayService;
    }

    public Single<ApiSearchResponse> getApiSearchResponse(String keywords) {
        return IGameDisplayService.searchGames(keywords);
    }
}
