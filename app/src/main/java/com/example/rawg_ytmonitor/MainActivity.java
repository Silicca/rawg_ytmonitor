package com.example.rawg_ytmonitor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.eightbitlab.bottomnavigationbar.BottomBarItem;
import com.eightbitlab.bottomnavigationbar.BottomNavigationBar;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    BottomNavigationBar bottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        bottomNavigationBar = findViewById(R.id.bottom_bar);

        setUpView();
    }

    private void setUpView() {
        bottomNavigationBar.addTab(new BottomBarItem(R.drawable.ic_yt_logo, R.string.videos));
        bottomNavigationBar.addTab(new BottomBarItem(R.drawable.ic_menu_logo, R.string.favorites));
        bottomNavigationBar.addTab(new BottomBarItem(R.drawable.ic_search_logo, R.string.search));
        viewPager.setAdapter(new GameFragmentAdapter(getSupportFragmentManager()));

        // manually change the bottom navigation position on screen swipe
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationBar.selectTab(position, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationBar.setOnSelectListener(new BottomNavigationBar.OnSelectListener() {
            @Override
            public void onSelect(int position) {
                viewPager.setCurrentItem(position);
            }
        });
    }
}