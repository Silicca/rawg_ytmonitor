package com.example.rawg_ytmonitor.menu.view;

import com.example.rawg_ytmonitor.data.apimodel.Game;

import java.util.List;

public interface IMenuView {
    void displayGameList(List<Game> games);

    void refreshView(Game game);

    void removeFavorites(Game game);
}
