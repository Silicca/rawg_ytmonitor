package com.example.rawg_ytmonitor.data.apimodel;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IGameDisplayService {
    @GET("games?")
    Single<ApiSearchResponse> searchGames(@Query("search") String keywords);

    @GET("games/{id}/youtube")
    Single<ApiVideoResponse> searchVideo(@Path("id") int gameId);
}

