package com.zwc.sellsys.android.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zwc.sellsys.android.R;
import com.zwc.sellsys.android.adaper.ChooseSpecificationAdapter;
import com.zwc.sellsys.android.constant.Constants;
import com.zwc.sellsys.android.db.dao.SpecificationDao;
import com.zwc.sellsys.android.entity.SpecificationBean;
import com.zwc.sellsys.android.listener.OnItemClickListener;
import com.zwc.sellsys.android.utils.AppUtil;

import java.util.List;

public class ChooseSpecificationActivity extends BaseActivity{
    private RecyclerView mRecyclerView;


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_choose_specification);

    }

    @Override
    protected void initView() {
        mRecyclerView = findViewById(R.id.rv_choose_specification);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        SpecificationDao specificationDao = new SpecificationDao();
        List<SpecificationBean> specificationBeans =  specificationDao.findAll();
        ChooseSpecificationAdapter adapter = new ChooseSpecificationAdapter(this,specificationBeans);
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener<SpecificationBean>() {
            @Override
            public void onItemClick(SpecificationBean bean) {
                Intent intent = new Intent();
                intent.putExtra(Constants.EXTRA_SPECIFICATION_KEY,bean);
                setResult(RESULT_OK,intent);
                AppUtil.removeActivity(ChooseSpecificationActivity.this);
            }
        });
    }

    @Override
    protected void initData() {

    }
}
