package com.example.rawg_ytmonitor.video.adapter;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.rawg_ytmonitor.R;
import com.example.rawg_ytmonitor.data.apimodel.Thumbnail;
import com.example.rawg_ytmonitor.data.apimodel.Video;
import com.example.rawg_ytmonitor.video.view.IVideoView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VideoViewHolder extends RecyclerView.ViewHolder {

    private View v;
    private Video video;
    private IVideoView iVideoView;

    @BindView(R.id.video_thumbnail)
    ImageView videoThumbnail;
    @BindView(R.id.video_title)
    TextView videoTitle;
    @BindView(R.id.channel_title)
    TextView channelTitle;
    @BindView(R.id.video_view_count)
    TextView videoViewCount;

    public VideoViewHolder(View v, IVideoView iVideoView) {
        super(v);
        this.v = v;
        ButterKnife.bind(this, v);
        this.iVideoView = iVideoView;
        setListener();
    }

    private void setListener() {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + video.getExternalId()));
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=" + video.getExternalId()));
                iVideoView.openVideo(appIntent, webIntent);
            }
        });
    }

    void bind(Video video){
        Resources res = this.itemView.getContext().getResources();
        this.video = video;
        String myText;
        if (Build.VERSION.SDK_INT >= 24) {
            myText = Html.fromHtml(video.getName() , Html.FROM_HTML_MODE_LEGACY).toString();
        }
        else {
            //Html.fromHtml(string) is deprecated as of API 24 (Android 7.0 Nougat)
            myText = Html.fromHtml(video.getName()).toString();
        }
        this.videoTitle.setText(String.format(res.getString(R.string.video_name), myText));
        this.videoViewCount.setText((String.format(res.getString(R.string.view_count), video.getViewCount())));
        this.channelTitle.setText((String.format(res.getString(R.string.channel_name), video.getChannelTitle())));
        Glide.with(v)
                .load(video.getThumbnails().get(Thumbnail.DEFAULT).getUrl())
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(videoThumbnail);


    }
}
