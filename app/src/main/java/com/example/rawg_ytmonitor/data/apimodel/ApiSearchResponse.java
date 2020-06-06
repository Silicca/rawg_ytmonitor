package com.example.rawg_ytmonitor.data.apimodel;

import java.util.List;

public class ApiSearchResponse {
    List<Game> gameList;

    int totalItems;

    public List<Game> getGameList() {
        return gameList;
    }

    public int getTotalItems() {
        return totalItems;
    }

}
