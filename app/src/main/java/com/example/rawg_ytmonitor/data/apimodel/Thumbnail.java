package com.example.rawg_ytmonitor.data.apimodel;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class Thumbnail {
    public static final String DEFAULT = "default";
    public static final String HIGH = "high";
    public static final String MEDIUM = "medium";
    public static final String SDDEFAULT = "sddefault";
    public static final String MAXRESDEFAULT = "maxresdefault";

    @Expose
    String url;
}
