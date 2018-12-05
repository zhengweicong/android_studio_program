package com.zwc.sellsys.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.zwc.sellsys.android.R;
import com.zwc.sellsys.android.adaper.UserInfoAdapter;
import com.zwc.sellsys.android.db.dao.UserDao;
import com.zwc.sellsys.android.entity.PagerBean;
import com.zwc.sellsys.android.entity.UserBean;
import com.zwc.sellsys.android.utils.AppUtil;
import com.zwc.sellsys.android.utils.DensityUtil;
import com.zwc.sellsys.android.utils.ResourseUtil;
import com.zwc.sellsys.android.utils.ToastUtil;
import com.zwc.sellsys.android.widget.DividerItemDecoration;

import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.MaterialHeader;

public class UserInfoActivity extends BaseActivity {
    private static final String TAG = UserInfoActivity.class.getSimpleName();
    private static final int REQUEST_CODE_ADD_USERINFO = 1;

    private TextView mTextViewCurrentPage;
    private TextView mTextViewPageCount;
    private TextView mTextViewTotalCount;

    private RecyclerView mRecyclerView;
    private UserInfoAdapter mUserInfoAdapter;

    private PtrFrameLayout mPtrFrameLayout;

    private UserDao mUserDao;
    private PagerBean mPagerBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != mActionBar) {
            mActionBar.show();
            mActionBar.setTitle("用户信息");
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeButtonEnabled(true);
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_top_add:
                Intent addUserIntent = new Intent(UserInfoActivity.this, AddUserActivity.class);
                startActivityForResult(addUserIntent, REQUEST_CODE_ADD_USERINFO);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initView() {
        mRecyclerView = findViewById(R.id.rv_user_info);

        mTextViewCurrentPage = findViewById(R.id.tv_pager_current_page);
        mTextViewPageCount = findViewById(R.id.tv_pager_page_count);
        mTextViewTotalCount = findViewById(R.id.tv_pager_total_count);

        mPtrFrameLayout = findViewById(R.id.material_style_ptr_frame);
        // header
        final MaterialHeader header = new MaterialHeader(this);
        int[] colors = getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, DensityUtil.dp2px(15), 0, DensityUtil.dp2px(10));
        header.setPtrFrameLayout(mPtrFrameLayout);

        mPtrFrameLayout.setLoadingMinTime(1000);
        mPtrFrameLayout.setDurationToCloseHeader(1500);
        mPtrFrameLayout.setHeaderView(header);
        mPtrFrameLayout.addPtrUIHandler(header);
        mPtrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrameLayout.autoRefresh(false);
            }
        }, 100);
        mPtrFrameLayout.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(final PtrFrameLayout frame) {


                if (null != mPagerBean
                        && mPagerBean.hasMore()
                        && null != mUserDao
                        && null != mUserInfoAdapter) {
                    mPagerBean.setCurrentPage(mPagerBean.getNextPage());
                    List<UserBean> users = mUserDao.getUsers(mPagerBean);
                    mUserInfoAdapter.updateAndLoadMore(users);

                    long delay = (long) (1000 + Math.random() * 2000);
                    delay = Math.max(0, delay);
                    frame.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            frame.refreshComplete();
                        }
                    }, delay);
                    //更新分页信息
                    updateCurrentPage();
                } else {
                    ToastUtil.show(R.string.pager_no_more);
                }
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                if (null != mPagerBean) {
                    mPagerBean.setCurrentPage(mPagerBean.getFirstPage());
                    List<UserBean> users = mUserDao.getUsers(mPagerBean);
                    mUserInfoAdapter.update(users);
                    long delay = (long) (1000 + Math.random() * 2000);
                    delay = Math.max(0, delay);
                    frame.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            frame.refreshComplete();
                        }
                    }, delay);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert:

                break;

            case R.id.btn_back:
                AppUtil.removeActivity(this);
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_user_info);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_ADD_USERINFO:
                    if (null != mUserInfoAdapter) {
                        //查询数据
                        UserDao userDao = new UserDao();
                        PagerBean pagerBean = new PagerBean();
                        pagerBean.setCurrentPage(1);
                        List<UserBean> users = userDao.getUsers(pagerBean);
                        mUserInfoAdapter.update(users);
                    }
                    break;
            }
        }

    }

    @Override
    public void initData() {
        //查询数据
        mUserDao = new UserDao();
        mPagerBean = new PagerBean();
        mPagerBean.setCurrentPage(1);
        List<UserBean> users = mUserDao.getUsers(mPagerBean);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 10, ResourseUtil.getColor(R.color.colorPrimary)));
        mUserInfoAdapter = new UserInfoAdapter(this, users);
        mRecyclerView.setAdapter(mUserInfoAdapter);

        updateCurrentPage();
    }

    /**
     * 更新分页信息
     */
    public void updateCurrentPage() {
        mTextViewTotalCount.setText(ResourseUtil.getString(R.string.pager_total_count, mPagerBean.getTotalCount()));
        mTextViewCurrentPage.setText(ResourseUtil.getString(R.string.pager_current_page, mPagerBean.getCurrentPage()));
        mTextViewPageCount.setText(ResourseUtil.getString(R.string.pager_page_count, mPagerBean.getPageCount()));
    }
}


