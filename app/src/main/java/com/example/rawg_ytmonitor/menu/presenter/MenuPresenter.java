package com.example.rawg_ytmonitor.menu.presenter;

import com.example.rawg_ytmonitor.data.apimodel.Game;
import com.example.rawg_ytmonitor.data.repository.IGameDisplayRepository;
import com.example.rawg_ytmonitor.menu.view.IMenuView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class MenuPresenter implements IMenuPresenter {

    private IGameDisplayRepository repository;
    private CompositeDisposable compositeDisposable;
    private IMenuView view;

    public MenuPresenter(IMenuView view, IGameDisplayRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void setUpGameList() {
        compositeDisposable.clear();
        compositeDisposable.add(repository.getFavorites()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<Game>>() {
                    @Override
                    public void onNext(List<Game> games) {
                        view.displayGameList(games);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));

    }
}
