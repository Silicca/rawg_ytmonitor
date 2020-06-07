package com.example.rawg_ytmonitor.search.presenter;

import android.util.Log;

import com.example.rawg_ytmonitor.data.apimodel.ApiSearchResponse;
import com.example.rawg_ytmonitor.data.repository.GameDisplayRepository;
import com.example.rawg_ytmonitor.search.view.ISearchView;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter implements ISearchPresenter {
    private ISearchView view;
    private GameDisplayRepository repository;
    private CompositeDisposable compositeDisposable;

    public SearchPresenter(ISearchView view, GameDisplayRepository repository) {
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
    public void addToFavorites(String gameId) {

    }

    @Override
    public void removeFromFavorites(String gameId) {

    }

    @Override
    public void cancelSubscription() {
        compositeDisposable.clear();
    }

    @Override
    public void disposeView() {

    }
}
