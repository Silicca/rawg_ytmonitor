package com.example.rawg_ytmonitor;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.rawg_ytmonitor.menu.view.MenuFragment;
import com.example.rawg_ytmonitor.search.view.SearchFragment;
import com.example.rawg_ytmonitor.video.view.VideoFragment;

public class GameFragmentAdapter extends FragmentStatePagerAdapter {
    private static final int MENU_VIEW = 1;
    private static final int SEARCH_VIEW = 2;
    private VideoFragment videoFragment;
    private MenuFragment menuFragment;
    private SearchFragment searchFragment;

    /**
     * Constructor
     * @param fragmentManager the fragment manager
     */
    public GameFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);

        videoFragment = VideoFragment.newInstance();
        menuFragment = MenuFragment.newInstance();
        searchFragment = SearchFragment.newInstance();

    }

    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case MENU_VIEW:
                return menuFragment;
            case SEARCH_VIEW:
                return searchFragment;
            default:
                return videoFragment;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if (object instanceof VideoFragment) {
            videoFragment.refresh();
        }
        if (object instanceof MenuFragment) {
            menuFragment.refresh();
        }
        if (object instanceof SearchFragment) {
            searchFragment.refreshResult();
        }
        return super.getItemPosition(object);
    }
}