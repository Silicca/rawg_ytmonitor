package com.example.rawg_ytmonitor.menu.view;

import com.example.rawg_ytmonitor.data.apimodel.Game;

import java.util.List;

public interface IMenuView {
    /**
     * Display the list of favorite games (adapter)
     * @param games the list of favorite games
     */
    void displayGameList(List<Game> games);

    /**
     * Refresh the bind of the game in the view (adapter)
     * @param game the Game
     */
    void refreshView(Game game);

    /**
     * Remove the Game from the list of favorites (presenter)
     * @param game the Game
     */
    void removeFavorites(Game game);
}
