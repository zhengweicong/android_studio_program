package com.zwc.sellsys.android.adaper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private FragmentManager mFragmentManager;

    public MainFragmentPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragments) {
        super(fragmentManager);
        mFragments = fragments;
        mFragmentManager = fragmentManager;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position) ;
    }

    @Override
    public int getCount() {
        return mFragments != null ? mFragments.size() : 0;
    }
}
