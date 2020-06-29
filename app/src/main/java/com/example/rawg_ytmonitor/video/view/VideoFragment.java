package com.example.rawg_ytmonitor.video.view;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rawg_ytmonitor.R;
import com.example.rawg_ytmonitor.data.apimodel.Video;
import com.example.rawg_ytmonitor.data.di.FakeDependencyInjection;
import com.example.rawg_ytmonitor.video.adapter.VideoAdapter;
import com.example.rawg_ytmonitor.video.presenter.IVideoPresenter;
import com.example.rawg_ytmonitor.video.presenter.VideoPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoFragment extends Fragment implements IVideoView {
    private View rootView;
    private IVideoPresenter presenter;
    @BindView(R.id.video_list)
    RecyclerView recyclerView;
    private VideoAdapter adapter;

    public VideoFragment() {
    }

    public static VideoFragment newInstance() {
        return new VideoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_video_list, container, false);
        presenter = new VideoPresenter(this, FakeDependencyInjection.getIGameDisplayRepository());
        setUpRecyclerView();
        presenter.loadVideos();
        return rootView;
    }

    /**
     * Setup the recycler view of the video view
     */
    private void setUpRecyclerView() {
        ButterKnife.bind(this, rootView);
        adapter = new VideoAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    /**
     * Refresh the list of videos (presenter)
     */
    public void refresh(){
        presenter.loadVideos();
    }

    @Override
    public void displayVideos(List<Video> videos) {
        adapter.bindViewModels(videos);
    }

    @Override
    public void openVideo(Intent appIntent, Intent webIntent) {
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }
}
