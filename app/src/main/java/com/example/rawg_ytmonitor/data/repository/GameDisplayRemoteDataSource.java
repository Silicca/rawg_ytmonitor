package com.example.rawg_ytmonitor.data.repository;

import com.example.rawg_ytmonitor.data.apimodel.ApiSearchResponse;
import com.example.rawg_ytmonitor.data.apimodel.ApiVideoResponse;
import com.example.rawg_ytmonitor.data.apimodel.IGameDisplayService;

import io.reactivex.Single;

public class GameDisplayRemoteDataSource {

    private IGameDisplayService IGameDisplayService;

    /**
     * Constructor
     * @param IGameDisplayService Interface GameDisplayService
     */
    public GameDisplayRemoteDataSource(IGameDisplayService IGameDisplayService) {
        this.IGameDisplayService = IGameDisplayService;
    }

    /**
     * Get the API response for a game research with keywords
     * @param keywords String of the research
     * @return Single ApiSearchResponse object, the response of the rawg Api
     */
    public Single<ApiSearchResponse> getApiSearchResponse(String keywords) {
        return IGameDisplayService.searchGames(keywords);
    }

    /**
     * Get the API response for a video corresponding to the game id
     * @param gameId the int id of the game
     * @return Single ApiVideoResponse object, the response of the video Api
     */
    public Single<ApiVideoResponse> getApiVideoResponse(int gameId) {
        return IGameDisplayService.searchVideo(gameId);
    }
}
