package com.zwc.sellsys.android.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.zwc.sellsys.android.R;

public class CompensationInfoActivity extends BaseActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       mActionBar.show();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_compensation_info);
    }

    @Override
    protected void initView() {
        mRecyclerView = findViewById(R.id.rv_compensation_info);

    }

    @Override
    protected void initData() {

    }
}
