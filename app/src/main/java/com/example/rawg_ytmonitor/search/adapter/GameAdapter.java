package com.example.rawg_ytmonitor.search.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rawg_ytmonitor.R;
import com.example.rawg_ytmonitor.data.apimodel.Game;
import com.example.rawg_ytmonitor.search.view.ISearchView;

import java.util.ArrayList;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameViewHolder>{

    private List<Game> gamesList;
    private ISearchView iSearchView;

    // Provide a suitable constructor (depends on the kind of dataset)
    public GameAdapter(ISearchView iSearchView) {
        gamesList = new ArrayList<>();
        this.iSearchView = iSearchView;
    }

    public void bindViewModels(List<Game> gamesList) {
        this.gamesList.clear();
        this.gamesList.addAll(gamesList);
        //notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_game, parent, false);
        GameViewHolder gameViewHolder = new GameViewHolder(v, iSearchView);
        return gameViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        holder.bind(gamesList.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return gamesList.size();
    }
}
