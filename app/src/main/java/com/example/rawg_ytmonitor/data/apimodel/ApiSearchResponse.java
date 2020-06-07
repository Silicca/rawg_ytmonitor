package com.example.rawg_ytmonitor.data.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class ApiSearchResponse {
    @Expose
    int count;

    @SerializedName("results")
    @Expose
    List<Game> gameList;
}
