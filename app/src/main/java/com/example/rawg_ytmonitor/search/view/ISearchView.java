package com.example.rawg_ytmonitor.search.view;

import com.example.rawg_ytmonitor.data.apimodel.ApiSearchResponse;
import com.example.rawg_ytmonitor.data.apimodel.Game;

public interface ISearchView {

    /**
     * Display the games corresponding to the API search response (adapter)
     * @param apiSearchResponse the API search response
     */
    void displayGames(ApiSearchResponse apiSearchResponse);

    /**
     * Add the Game to the list of favorites (presenter)
     * @param game the Game
     */
    void addToFavorites(Game game);

    /**
     * Remove the Game from the list of favorites (presenter)
     * @param game the Game
     */
    void removeFromFavorites(Game game);

    /**
     * Refresh the bind of the game in the view (adapter)
     * @param game the Game
     */
    void refresh(Game game);
}
