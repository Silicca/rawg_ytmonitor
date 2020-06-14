package com.example.rawg_ytmonitor.menu.presenter;

import com.example.rawg_ytmonitor.data.apimodel.Game;
import com.example.rawg_ytmonitor.data.repository.IGameDisplayRepository;
import com.example.rawg_ytmonitor.menu.view.IMenuView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MenuPresenter implements IMenuPresenter {


    private IGameDisplayRepository repository;
    private CompositeDisposable compositeDisposable;
    private IMenuView view;

    public MenuPresenter(IMenuView view, IGameDisplayRepository repository) {
        this.view = view;
        this.repository = repository;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void setUpGameList() {
        compositeDisposable.clear();
        compositeDisposable.add(repository.getFavorites()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Game>>() {
                    @Override
                    public void onSuccess(List<Game> games) {
                        view.displayGameList(games);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public void removeFavorites(final Game game) {
        compositeDisposable.clear();
        compositeDisposable.add(repository.removeFromFavorites(game.getId()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.refreshView(game);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }


}
