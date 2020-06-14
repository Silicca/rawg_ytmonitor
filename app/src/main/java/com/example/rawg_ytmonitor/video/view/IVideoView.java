package com.example.rawg_ytmonitor.video.view;

import android.content.Intent;

import com.example.rawg_ytmonitor.data.apimodel.Video;

import java.util.List;

public interface IVideoView {
    void displayVideos(List<Video> videos);

    void openVideo(Intent appIntent, Intent webIntent);
}
