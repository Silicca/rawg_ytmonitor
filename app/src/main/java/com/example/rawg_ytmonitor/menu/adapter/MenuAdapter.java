package com.example.rawg_ytmonitor.menu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rawg_ytmonitor.R;
import com.example.rawg_ytmonitor.data.apimodel.Game;
import com.example.rawg_ytmonitor.menu.view.IMenuView;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder> {

    private List<Game> gamesList;
    private IMenuView iMenuView;

    /**
     * A suitable constructor (depends on the kind of dataset)
     * @param iMenuView the menu view
     */
    public MenuAdapter(IMenuView iMenuView) {
        gamesList = new ArrayList<>();
        this.iMenuView = iMenuView;
    }

    /**
     * Method for refresh the list of favorite games
     * @param gamesList the list of favorite games
     */
    public void bindViewModels(List<Game> gamesList) {
        this.gamesList.clear();
        this.gamesList.addAll(gamesList);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_game, parent, false);
        return new MenuViewHolder(v, iMenuView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.bind(gamesList.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return gamesList.size();
    }
}
