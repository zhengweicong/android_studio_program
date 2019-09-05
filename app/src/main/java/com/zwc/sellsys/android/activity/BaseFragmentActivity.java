package com.zwc.sellsys.android.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zwc.sellsys.android.R;
import com.zwc.sellsys.android.umeng.statistic.UmengPushUtil;
import com.zwc.sellsys.android.umeng.statistic.UmengStatisticUtil;
import com.zwc.sellsys.android.utils.AppUtil;

public abstract class BaseFragmentActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = BaseFragmentActivity.class.getSimpleName();
    protected ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        setActionBar();
        initView();
        initData();
        AppUtil.addActivity(this);
        UmengStatisticUtil.onResume(this);
        UmengPushUtil.onAppStart(this);
    }

    /**
     * 隐藏ActionBar
     */
    protected void setActionBar() {
        mActionBar = getSupportActionBar();
        if(null != mActionBar){
            mActionBar.hide();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                AppUtil.removeActivity(this);
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top_option, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppUtil.removeActivity(this);
        UmengStatisticUtil.onPause(this);
    }

    protected abstract void setContentView();

    protected abstract void initView();

    protected abstract void initData();
}
