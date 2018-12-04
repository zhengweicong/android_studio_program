package com.zwc.sellsys.android.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zwc.sellsys.android.utils.AppUtil;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        setActionBar();
        initView();
        initData();
        AppUtil.addActivity(this);
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
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppUtil.removeActivity(this);
    }

    protected abstract void setContentView();

    protected abstract void initView();

    protected abstract void initData();
}
