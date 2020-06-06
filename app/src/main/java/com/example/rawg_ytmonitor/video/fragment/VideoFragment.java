package com.example.rawg_ytmonitor.video.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rawg_ytmonitor.R;

public class VideoFragment extends Fragment {
    private View rootView;

    public VideoFragment() {
    }

    public static VideoFragment newInstance(){
        return new VideoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_video_list, container, false);
        return rootView;
    }
}
