package com.example.rawg_ytmonitor.search.presenter;

public interface ISearchPresenter {
    void searchGame(String s);
    void addToFavorites(String gameId);
    void removeFromFavorites(String gameId);
    void cancelSubscription();
    void disposeView();
}
