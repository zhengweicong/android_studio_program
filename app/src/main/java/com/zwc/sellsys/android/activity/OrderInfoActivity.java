package com.zwc.sellsys.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zwc.sellsys.android.R;
import com.zwc.sellsys.android.adaper.OrderInfoAdapter;
import com.zwc.sellsys.android.db.dao.OrderDao;
import com.zwc.sellsys.android.entity.OrderBean;
import com.zwc.sellsys.android.utils.AppUtil;
import com.zwc.sellsys.android.widget.DividerItemDecoration;

import java.util.List;

public class OrderInfoActivity extends BaseActivity {
    private static final int REQUEST_CODE_ADD_ORDER = 1;
    private RecyclerView mRecyclerView;
    private OrderInfoAdapter mOrderInfoAdapter;
    private OrderDao mOrderDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_order_info);
    }

    @Override
    protected void initView() {
        mRecyclerView = findViewById(R.id.rv_order_info);
    }

    @Override
    protected void initData() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mOrderDao = new OrderDao();
        List<OrderBean> orderBeans = mOrderDao.getOrders();
        mOrderInfoAdapter = new OrderInfoAdapter(this, orderBeans);
        mRecyclerView.setAdapter(mOrderInfoAdapter);


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {
            case R.id.btn_back:
                AppUtil.removeActivity(this);
                break;
            case R.id.btn_insert:
                Intent intent = new Intent(this, AddOrderInfoActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_ORDER);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_ADD_ORDER:
                    List<OrderBean> orderBeans = mOrderDao.getOrders();
                    mOrderInfoAdapter.setDataSetChanged(orderBeans);
                    break;
            }
        }

    }
}
