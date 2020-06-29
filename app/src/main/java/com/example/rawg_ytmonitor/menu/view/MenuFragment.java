package com.example.rawg_ytmonitor.menu.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rawg_ytmonitor.R;
import com.example.rawg_ytmonitor.data.apimodel.Game;
import com.example.rawg_ytmonitor.data.di.FakeDependencyInjection;
import com.example.rawg_ytmonitor.menu.adapter.MenuAdapter;
import com.example.rawg_ytmonitor.menu.presenter.IMenuPresenter;
import com.example.rawg_ytmonitor.menu.presenter.MenuPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuFragment extends Fragment implements IMenuView {
    private View rootView;
    private IMenuPresenter presenter;
    @BindView(R.id.game_list)
    RecyclerView recyclerView;
    @BindView(R.id.error_msg)
    TextView errorMsg;
    private MenuAdapter adapter;

    private List<Game> games = new ArrayList<>();

    public MenuFragment() {
    }

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_game_list, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new MenuPresenter(this, FakeDependencyInjection.getIGameDisplayRepository());
        presenter.setUpGameList();
        setUpRecyclerView();
    }

    /**
     * Setup the recycler view of the menu view (view for the list of favorite games)
     */
    private void setUpRecyclerView() {
        ButterKnife.bind(this, rootView);
        adapter = new MenuAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    /**
     * Refresh the list of favorite games (presenter)
     */
    public void refresh() {
        presenter.setUpGameList();
    }

    @Override
    public void displayGameList(List<Game> games) {
        if (games.isEmpty()) {
            errorMsg.setVisibility(View.VISIBLE);
        } else {
            errorMsg.setVisibility(View.GONE);
        }
        this.games = games;
        adapter.bindViewModels(this.games);
    }

    @Override
    public void refreshView(Game game) {
        games.remove(game);
        adapter.bindViewModels(games);
    }

    @Override
    public void removeFavorites(Game game) {
        presenter.removeFavorites(game);
    }
}
