package com.example.rawg_ytmonitor.search.presenter;

import android.util.Log;

import com.example.rawg_ytmonitor.data.apimodel.ApiSearchResponse;
import com.example.rawg_ytmonitor.data.apimodel.Game;
import com.example.rawg_ytmonitor.data.repository.IGameDisplayRepository;
import com.example.rawg_ytmonitor.search.view.ISearchView;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter implements ISearchPresenter {
    private ISearchView view;
    private IGameDisplayRepository repository;
    private CompositeDisposable compositeDisposable;

    /**
     * Constructor
     * @param view the view of the list of searched games
     * @param repository the GameDisplayRepository Object (context)
     */
    public SearchPresenter(ISearchView view, IGameDisplayRepository repository) {
        this.view = view;
        this.repository = repository;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void searchGame(String s) {
        compositeDisposable.clear();
        compositeDisposable.add(repository.getApiSearchResponse(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ApiSearchResponse>() {
            @Override
            public void onSuccess(ApiSearchResponse apiSearchResponse) {
                view.displayGames(apiSearchResponse);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Error", Objects.requireNonNull(e.getMessage()));
            }
        }));
    }

    @Override
    public void addToFavorites(final Game game) {
        compositeDisposable.add(repository.addToFavorites(game)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {
                        Log.println(Log.INFO,"Complete","Added to favorites !");
                        view.refresh(game);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", Objects.requireNonNull(e.getMessage()));
                    }
                }));
    }

    @Override
    public void removeFromFavorites(final Game game) {
        compositeDisposable.add(repository.removeFromFavorites(game.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {
                        Log.println(Log.INFO,"Complete","Removed from favorites !");
                        view.refresh(game);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", Objects.requireNonNull(e.getMessage()));
                    }
                }));
    }
}
