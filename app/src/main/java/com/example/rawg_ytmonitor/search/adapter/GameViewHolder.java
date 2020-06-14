package com.example.rawg_ytmonitor.search.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.rawg_ytmonitor.R;
import com.example.rawg_ytmonitor.data.apimodel.Game;
import com.example.rawg_ytmonitor.search.view.ISearchView;

public class GameViewHolder extends RecyclerView.ViewHolder {
    private TextView nameTextView;
    private TextView scoreTextView;
    private ImageView imageView;
    private ImageView addButton;
    private ImageView removeButton;
    private View v;
    private Game game;
    private ISearchView iSearchView;

    public GameViewHolder(View v, final ISearchView iSearchView) {
        super(v);
        this.v = v;
        nameTextView = v.findViewById(R.id.game_title);
        scoreTextView = v.findViewById(R.id.game_score);
        imageView = v.findViewById(R.id.imageView);
        addButton = v.findViewById(R.id.add);
        removeButton = v.findViewById(R.id.remove);
        removeButton.setVisibility(View.INVISIBLE);
        this.iSearchView = iSearchView;
        setupListeners();
    }

    private void setupListeners() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.setFavorite(true);
                iSearchView.addToFavorites(game);
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.setFavorite(false);
                iSearchView.removeFromFavorites(game);
            }
        });

    }

    void bind(Game game) {
        this.game = game;
        nameTextView.setText(game.getName());
        scoreTextView.setText((game.getNote()));
        if(game.isFavorite()){
            removeVisible();
        } else {
            addVisible();
        }
        Glide.with(v)
                .load(game.getImageUrl())
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    void addVisible(){
        addButton.setVisibility(View.VISIBLE);
        removeButton.setVisibility(View.INVISIBLE);
    }

    void removeVisible(){
        removeButton.setVisibility(View.VISIBLE);
        addButton.setVisibility(View.INVISIBLE);
    }
}
