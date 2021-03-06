package com.zwc.sellsys.android.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zwc.sellsys.android.R;
import com.zwc.sellsys.android.umeng.statistic.UmengPushUtil;
import com.zwc.sellsys.android.umeng.statistic.UmengStatisticUtil;
import com.zwc.sellsys.android.utils.AppUtil;

public abstract class BaseActivity extends Activity implements View.OnClickListener{
    public static final String TAG = BaseActivity.class.getSimpleName();
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
        mActionBar = getActionBar();
        if(null != mActionBar){
            mActionBar.hide();
        }
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

    @Override
    public void onClick(View v) {

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
}
