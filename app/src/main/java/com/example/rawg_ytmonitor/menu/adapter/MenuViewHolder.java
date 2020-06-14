package com.example.rawg_ytmonitor.menu.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.rawg_ytmonitor.R;
import com.example.rawg_ytmonitor.data.apimodel.Game;
import com.example.rawg_ytmonitor.menu.view.IMenuView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuViewHolder extends RecyclerView.ViewHolder {

    private IMenuView menuView;
    private View view;
    private Game game;

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

    public MenuViewHolder(@NonNull View view, IMenuView menuView) {
        super(view);
        this.menuView = menuView;
        this.view = view;
        ButterKnife.bind(this, view);
        setupListeners();
    }

    private void setupListeners() {
        this.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuView.removeFavorites(game);
            }
        });
    }

    void bind(Game game) {
        this.game = game;
        this.nameTextView.setText(game.getName());
        this.scoreTextView.setText((game.getNote()));
        if(game.isFavorite()){
            removeVisible();
        } else {
            addVisible();
        }
        Glide.with(view)
                .load(game.getImageUrl())
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    void addVisible(){
        this.addButton.setVisibility(View.VISIBLE);
        this.removeButton.setVisibility(View.INVISIBLE);
    }

    void removeVisible(){
        this.removeButton.setVisibility(View.VISIBLE);
        this.addButton.setVisibility(View.INVISIBLE);
    }
}
