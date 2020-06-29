package com.example.rawg_ytmonitor.video.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rawg_ytmonitor.R;
import com.example.rawg_ytmonitor.data.apimodel.Video;
import com.example.rawg_ytmonitor.video.view.IVideoView;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder>{

    private List<Video> videos;
    private IVideoView iVideoView;

    /**
     * A suitable constructor (depends on the kind of dataset)
     * @param iVideoView the video view
     */
    public VideoAdapter(IVideoView iVideoView) {
        this.iVideoView = iVideoView;
        this.videos = new ArrayList<>();
    }

    /**
     * Method for refresh the list of favorite games
     * @param videosList the list of favorite games
     */
    public void bindViewModels(List<Video> videosList) {
        this.videos.clear();
        this.videos.addAll(videosList);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(v, iVideoView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.bind(videos.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return videos.size();
    }
}
