package com.zwc.sellsys.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zwc.sellsys.android.R;
import com.zwc.sellsys.android.adaper.ChooseUserInfoAdapter;
import com.zwc.sellsys.android.constant.Constants;
import com.zwc.sellsys.android.db.dao.UserDao;
import com.zwc.sellsys.android.entity.UserBean;
import com.zwc.sellsys.android.listener.OnItemClickListener;
import com.zwc.sellsys.android.utils.AppUtil;

import java.util.List;

public class ChooseUserActivity extends BaseActivity {
    private RecyclerView mRecyclerView;

    private ChooseUserInfoAdapter mChooseUserInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != mActionBar) {
            mActionBar.show();
            mActionBar.setTitle("选择用户信息");
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_choose_user);
    }

    @Override
    protected void initView() {

        mRecyclerView = findViewById(R.id.rv_choose_user);
    }

    @Override
    protected void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        UserDao userDao = new UserDao();
        List<UserBean> userBeans =  userDao.getUsers();
        mChooseUserInfoAdapter = new ChooseUserInfoAdapter(this,userBeans);
        mRecyclerView.setAdapter(mChooseUserInfoAdapter);

        mChooseUserInfoAdapter.setOnItemClickListener(new OnItemClickListener<UserBean>() {
            @Override
            public void onItemClick(UserBean userBean) {
                Intent intent = new Intent();
                intent.putExtra(Constants.EXTRA_USER_BEAN_KEY,userBean);
                setResult(RESULT_OK,intent);
                AppUtil.removeActivity(ChooseUserActivity.this);
            }
        });
    }
}
