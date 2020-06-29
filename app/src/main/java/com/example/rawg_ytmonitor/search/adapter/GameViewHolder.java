package com.example.rawg_ytmonitor.search.adapter;

import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.rawg_ytmonitor.R;
import com.example.rawg_ytmonitor.data.apimodel.Game;
import com.example.rawg_ytmonitor.search.view.ISearchView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.game_title)
    TextView nameTextView;
    @BindView(R.id.game_score)
    TextView scoreTextView;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.add)
    ImageView addButton;
    @BindView(R.id.remove)
    ImageView removeButton;

    private View v;
    private Game game;
    private ISearchView iSearchView;

    /**
     * Constructor
     * @param v the main view
     * @param iSearchView the search view (view for the list of searched games)
     */
    public GameViewHolder(View v, final ISearchView iSearchView) {
        super(v);
        this.v = v;
        ButterKnife.bind(this, v);
        removeButton.setVisibility(View.INVISIBLE);
        this.iSearchView = iSearchView;
        setupListeners();
    }

    /**
     * Setup a listener on the remove from favorites button and on the add to favorites button
     */
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

    /**
     * Bind a Game to the view with his name, score and picture
     * @param game the Game
     */
    void bind(Game game) {
        Resources res = this.itemView.getContext().getResources();
        this.game = game;
        nameTextView.setText(game.getName());
        scoreTextView.setText(String.format(res.getString(R.string.game_rating), game.getNote()));
        if (game.isFavorite()) {
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

    /**
     * Replace the remove button by the add button
     */
    void addVisible() {
        addButton.setVisibility(View.VISIBLE);
        removeButton.setVisibility(View.INVISIBLE);
    }

    /**
     * Replace the add button by the remove button
     */
    void removeVisible() {
        removeButton.setVisibility(View.VISIBLE);
        addButton.setVisibility(View.INVISIBLE);
    }
}
