package com.example.rawg_ytmonitor.menu.presenter;

import com.example.rawg_ytmonitor.data.apimodel.Game;

public interface IMenuPresenter {
    /**
     * Setup the list of favorite games
     */
    void setUpGameList();

    /**
     * Remove the Game from the favorites (repository)
     * @param game the Game
     */
    void removeFavorites(Game game);
}
