package com.zwc.sellsys.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zwc.sellsys.android.R;
import com.zwc.sellsys.android.adaper.SpecificationAdapter;
import com.zwc.sellsys.android.db.dao.SpecificationDao;
import com.zwc.sellsys.android.entity.SpecificationBean;
import com.zwc.sellsys.android.utils.AppUtil;

import java.util.List;

public class SpecificationActivity extends BaseActivity {
    private static final String TAG = SpecificationActivity.class.getSimpleName();
    private static final int REQUEST_CODE_ADD_SPECIFICAITON = 1;

    private RecyclerView mRecyclerView;
    private SpecificationAdapter mSpecAdapter;
    private SpecificationDao mSpecDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_specification_info);
    }

    @Override
    protected void initView() {
        mRecyclerView = findViewById(R.id.rv_specification);
    }

    @Override
    protected void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mSpecDao = new SpecificationDao();
        List<SpecificationBean> specificationBeans = mSpecDao.findAll();
        mSpecAdapter = new SpecificationAdapter(this, specificationBeans);
        mRecyclerView.setAdapter(mSpecAdapter);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_back:
                AppUtil.removeActivity(this);
                break;
            case R.id.btn_insert:
                Intent intent = new Intent(this,AddSpecificationActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_SPECIFICAITON);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_ADD_SPECIFICAITON:
                    List<SpecificationBean> specificationBeans = mSpecDao.findAll();
                    mSpecAdapter.setDataSetChanged(specificationBeans);
                    break;
            }
        }
    }
}
