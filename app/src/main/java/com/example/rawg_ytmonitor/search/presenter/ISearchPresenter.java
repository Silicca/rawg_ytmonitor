package com.example.rawg_ytmonitor.search.presenter;

import com.example.rawg_ytmonitor.data.apimodel.Game;

public interface ISearchPresenter {
    void searchGame(String s);
    void addToFavorites(Game game);
    void removeFromFavorites(Game game);
    void cancelSubscription();
    void disposeView();
}
