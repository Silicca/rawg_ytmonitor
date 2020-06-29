package com.example.rawg_ytmonitor.search.presenter;

import com.example.rawg_ytmonitor.data.apimodel.Game;

public interface ISearchPresenter {
    /**
     * Make the research with the s keywords (repository)
     * @param s String of the keywords research
     */
    void searchGame(String s);

    /**
     * Add the Game to the list of favorite games (repository)
     * @param game the Game
     */
    void addToFavorites(Game game);

    /**
     * Remove the Game from the favorites (repository)
     * @param game the game
     */
    void removeFromFavorites(Game game);
}
