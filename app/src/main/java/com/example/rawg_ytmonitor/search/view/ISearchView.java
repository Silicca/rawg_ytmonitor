package com.example.rawg_ytmonitor.search.view;

import com.example.rawg_ytmonitor.data.apimodel.ApiSearchResponse;
import com.example.rawg_ytmonitor.data.apimodel.Game;

public interface ISearchView {

    void displayGames(ApiSearchResponse apiSearchResponse);

    void addToFavorites(Game game);

    void removeFromFavorites(Game game);

    void refresh(Game game);
}
