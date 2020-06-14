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

    public MenuAdapter(IMenuView iMenuView) {
        gamesList = new ArrayList<>();
        this.iMenuView = iMenuView;
    }

    public void bindViewModels(List<Game> gamesList) {
        this.gamesList.clear();
        this.gamesList.addAll(gamesList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_game, parent, false);
        return new MenuViewHolder(v, iMenuView);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.bind(gamesList.get(position));
    }

    @Override
    public int getItemCount() {
        return gamesList.size();
    }
}
