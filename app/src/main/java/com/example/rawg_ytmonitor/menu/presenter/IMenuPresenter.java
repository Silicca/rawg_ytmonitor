package com.example.rawg_ytmonitor.menu.presenter;

import com.example.rawg_ytmonitor.data.apimodel.Game;

public interface IMenuPresenter {
    void setUpGameList();

    void removeFavorites(Game game);
}
