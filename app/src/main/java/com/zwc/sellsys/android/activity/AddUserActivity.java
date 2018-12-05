package com.zwc.sellsys.android.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.zwc.sellsys.android.R;
import com.zwc.sellsys.android.db.dao.UserDao;
import com.zwc.sellsys.android.entity.UserBean;
import com.zwc.sellsys.android.utils.RegularExpressionUtil;
import com.zwc.sellsys.android.utils.ResourseUtil;
import com.zwc.sellsys.android.utils.StringUtil;
import com.zwc.sellsys.android.utils.ToastUtil;

public class AddUserActivity extends BaseActivity {
    public static final String TAG = "AddUserActivity";
    private EditText mEditTextUserName;
    private EditText mEditTextAddress;
    private EditText mEditTextPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != mActionBar) {
            mActionBar.show();
            mActionBar.setTitle("添加用户信息");
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_user:
                checkInfo();
                break;
            default:
                break;
        }

        super.onClick(v);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_add_user);
    }

    @Override
    public void initView() {
        mEditTextUserName = findViewById(R.id.et_add_specification_description);
        mEditTextAddress = findViewById(R.id.et_add_specificaition_price);
        mEditTextPhone = findViewById(R.id.et_add_specification_type);

    }

    @Override
    public void initData() {


    }


    /**
     * 检查信息合法性，并添加到数据库
     */
    private void checkInfo() {
        String address = mEditTextAddress.getText().toString().trim();
        String username = mEditTextUserName.getText().toString().trim();
        String phone = mEditTextPhone.getText().toString().trim();

        if (StringUtil.isEmpty(address)) {
            ToastUtil.show(ResourseUtil.getString(
                    R.string.activity_add_user_address_info));
            return;
        }

        if (StringUtil.isEmpty(username)) {
            ToastUtil.show(ResourseUtil.getString(
                    R.string.activity_add_user_username_info));
            return;
        }

        if (StringUtil.isEmpty(phone)) {
            ToastUtil.show(ResourseUtil.getString(
                    R.string.activity_add_user_phone_info));
            return;
        } else if (!RegularExpressionUtil.isMobile(phone)) {
            ToastUtil.show(ResourseUtil.getString(
                    R.string.activity_add_user_phone_wrong));
            return;
        }


        UserDao userDao = new UserDao();
        UserBean userBean = new UserBean();
        userBean.setPhone(phone);
        userBean.setAddress(address);
        userBean.setName(username);
        //查询用户信息，不存在则进行保存，负责不保存
        boolean isExists = userDao.getUser(userBean);
        if (!isExists) {
            Log.i(AddUserActivity.TAG, "用户不存在");
            //添加用户信息
            long result = userDao.insert(userBean);
            if (result > 0) {
                ToastUtil.show(ResourseUtil.getString(
                        R.string.activity_add_user_success));
                setResult(RESULT_OK);
                finish();
            } else {
                ToastUtil.show(ResourseUtil.getString(
                        R.string.activity_add_user_fail));
            }
        } else {
            Log.i(AddUserActivity.TAG, "用户存在");
            ToastUtil.show(ResourseUtil.getString(
                    R.string.activity_add_user_exist));
        }
    }

}
