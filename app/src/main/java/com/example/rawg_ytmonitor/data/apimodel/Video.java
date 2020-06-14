package com.example.rawg_ytmonitor.data.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

import lombok.Data;

@Data
public class Video {
    @Expose
    @SerializedName("external_id")
    String externalId;

    @Expose
    String name;

    @Expose
    @SerializedName("channel_title")
    String channelTitle;

    @Expose
    @SerializedName("view_count")
    int viewCount;

    @Expose
    Map<String, Thumbnail> thumbnails;
}
