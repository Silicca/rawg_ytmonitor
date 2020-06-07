package com.example.rawg_ytmonitor.search.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.rawg_ytmonitor.R;
import com.example.rawg_ytmonitor.data.apimodel.ApiSearchResponse;
import com.example.rawg_ytmonitor.data.apimodel.Game;
import com.example.rawg_ytmonitor.data.di.FakeDependencyInjection;
import com.example.rawg_ytmonitor.search.presenter.ISearchPresenter;
import com.example.rawg_ytmonitor.search.presenter.SearchPresenter;

import java.util.Timer;
import java.util.TimerTask;

public class SearchFragment extends Fragment implements ISearchView {
    private View rootView;
    private SearchView searchView;
    private ProgressBar progressBar;
    private SearchView.OnQueryTextListener queryListener;
    private String query = "";
    private ISearchPresenter presenter;
    FakeDependencyInjection fake = new FakeDependencyInjection();

    public SearchFragment() {
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_search_game, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new SearchPresenter(this, FakeDependencyInjection.getGameDisplayRepository());
        setupSearchView();
    }

    private void setupSearchView() {
        searchView = rootView.findViewById(R.id.search_view);
        progressBar = rootView.findViewById(R.id.loading);
        this.queryListener = new SearchView.OnQueryTextListener() {
            private Timer timer = new Timer();
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length() == 0) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    query = s;
                    timer.cancel();
                    timer = new Timer();
                    int sleep = 350;
                    if (s.length() == 1) {
                        sleep = 5000;
                    } else if (s.length() <= 3) {
                        sleep = 300;
                    } else if (s.length() <= 5) {
                        sleep = 200;
                    }
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            presenter.searchGame(query);
                        }
                    }, sleep);
                }
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryListener);
    }

    @Override
    public void displayGames(ApiSearchResponse apiSearchResponse) {
        for (Game game: apiSearchResponse.getGameList()) {

        }
    }
}
