package com.example.rawg_ytmonitor.video.view;

import android.content.Intent;

import com.example.rawg_ytmonitor.data.apimodel.Video;

import java.util.List;

public interface IVideoView {
    /**
     * Display the videos corresponding to the list of favorite games (adapter)
     * @param videos the List of videos
     */
    void displayVideos(List<Video> videos);

    /**
     * Method for opening the youtube videos
     * @param appIntent the app Intent Object
     * @param webIntent the web Intent Object
     */
    void openVideo(Intent appIntent, Intent webIntent);
}
