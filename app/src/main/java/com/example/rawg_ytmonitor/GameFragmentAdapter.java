package com.example.rawg_ytmonitor;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.rawg_ytmonitor.menu.fragment.MenuFragment;
import com.example.rawg_ytmonitor.search.fragment.SearchFragment;
import com.example.rawg_ytmonitor.video.fragment.VideoFragment;

public class GameFragmentAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;
    private static final int VIDEO_VIEW = 0;
    private static final int MENU_VIEW = 1;
    private static final int SEARCH_VIEW = 2;
    private VideoFragment videoFragment;
    private MenuFragment menuFragment;
    private SearchFragment searchFragment;

    public GameFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);

        videoFragment = VideoFragment.newInstance();
        menuFragment = MenuFragment.newInstance();
        searchFragment = SearchFragment.newInstance();

    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case VIDEO_VIEW:
                return videoFragment;
            case MENU_VIEW:
                return menuFragment;
            case SEARCH_VIEW:
                return searchFragment;
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

}