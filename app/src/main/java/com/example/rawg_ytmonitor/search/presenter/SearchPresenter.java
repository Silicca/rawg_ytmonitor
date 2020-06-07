package com.example.rawg_ytmonitor.search.presenter;

import com.example.rawg_ytmonitor.data.repository.GameDisplayRepository;
import com.example.rawg_ytmonitor.search.view.ISearchView;

public class SearchPresenter implements ISearchPresenter{
    private ISearchView view;
    private GameDisplayRepository repository;

    public SearchPresenter(ISearchView view, GameDisplayRepository repository) {
        this.view = view;
        this.repository = repository;
    }
}
