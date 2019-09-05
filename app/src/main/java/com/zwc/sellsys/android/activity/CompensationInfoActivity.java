package com.zwc.sellsys.android.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zwc.sellsys.android.R;
import com.zwc.sellsys.android.adaper.CompensationInfoAdapter;
import com.zwc.sellsys.android.entity.CompensationBean;

import java.util.ArrayList;
import java.util.List;

public class CompensationInfoActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private CompensationInfoAdapter mCompensationInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mActionBar != null){
            mActionBar.show();
            mActionBar.setTitle("赔偿信息");
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_compensation_info);
    }

    @Override
    protected void initView() {
        mRecyclerView = findViewById(R.id.rv_compensation_info);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<CompensationBean> compensationBeans = new ArrayList<CompensationBean>();
        CompensationBean bean = new CompensationBean();
        bean.setOrderId(1);
        bean.setPrice(98.0);
        bean.setDescription("测试数据，这个是一条描述信息");
        compensationBeans.add(bean);
        mCompensationInfoAdapter = new CompensationInfoAdapter(this, compensationBeans);
        mRecyclerView.setAdapter(mCompensationInfoAdapter);

    }

    @Override
    protected void initData() {

    }


}
