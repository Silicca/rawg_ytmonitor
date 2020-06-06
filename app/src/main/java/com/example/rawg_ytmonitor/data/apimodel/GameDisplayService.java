package com.example.rawg_ytmonitor.data.apimodel;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GameDisplayService {
    @GET("games?search=")
    Single<ApiSearchResponse> searchGames(@Query("q") String keywords);

}

