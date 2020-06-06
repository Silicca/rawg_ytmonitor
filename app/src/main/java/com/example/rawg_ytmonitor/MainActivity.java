package com.example.rawg_ytmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.eightbitlab.bottomnavigationbar.BottomBarItem;
import com.eightbitlab.bottomnavigationbar.BottomNavigationBar;
import com.example.rawg_ytmonitor.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationBar bottomNavigationBar = findViewById(R.id.bottom_bar);

        BottomBarItem youtubeItem = new BottomBarItem(R.drawable.ic_yt_logo, R.string.videos);
        BottomBarItem menuItem = new BottomBarItem(R.drawable.ic_menu_logo, R.string.favorites);
        BottomBarItem searchItem = new BottomBarItem(R.drawable.ic_search_logo, R.string.search);

        bottomNavigationBar.addTab(youtubeItem);
        bottomNavigationBar.addTab(menuItem);
        bottomNavigationBar.addTab(searchItem);
    }
}