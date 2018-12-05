package com.zwc.sellsys.android.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.zwc.sellsys.android.R;
import com.zwc.sellsys.android.adaper.MainFragmentPagerAdapter;
import com.zwc.sellsys.android.fragment.AboutUsFragment;
import com.zwc.sellsys.android.fragment.HomeFragment;
import com.zwc.sellsys.android.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener {
    private static final int ZERO = 0;
    private static final int FIRST = 1;

    private ViewPager mViewPager;
    private BottomNavigationView mBottomNavigationView;


    private FragmentPagerAdapter mFragmentPagerAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != mActionBar) {
            mActionBar.show();
            mActionBar.setTitle("简易售卖苹果信息");
        }
    }

    @Override
    protected void setContentView() {

        setContentView(R.layout.activity_main);

    }

    @Override
    protected void initView() {

        mViewPager = findViewById(R.id.vp_view_pager);
        mBottomNavigationView = findViewById(R.id.bnv_navigation_view);

    }

    @Override
    protected void initData() {

        mFragments.add(HomeFragment.newInstance());
        mFragments.add(AboutUsFragment.newInstance());
        mFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mViewPager.setCurrentItem(ZERO);
        mViewPager.addOnPageChangeListener(this);

        mBottomNavigationView.inflateMenu(R.menu.menu_bottom_navigation);
        mBottomNavigationView.setSelectedItemId(R.id.menu_main_home);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_home:
                mViewPager.setCurrentItem(ZERO);
                break;
            case R.id.menu_main_about:
                mViewPager.setCurrentItem(FIRST);
                break;
            default:
                mViewPager.setCurrentItem(ZERO);
                break;
        }
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        switch (position) {
            case ZERO:
                mBottomNavigationView.setSelectedItemId(R.id.menu_main_home);
                break;
            case FIRST:
                mBottomNavigationView.setSelectedItemId(R.id.menu_main_about);
                break;
            default:
                mBottomNavigationView.setSelectedItemId(R.id.menu_main_home);
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onBackPressed() {
        AppUtil.exitApp();
    }


}
