package com.zwc.sellsys.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.zwc.sellsys.android.R;
import com.zwc.sellsys.android.constant.Constants;
import com.zwc.sellsys.android.db.dao.OrderDao;
import com.zwc.sellsys.android.entity.OrderBean;
import com.zwc.sellsys.android.entity.SpecificationBean;
import com.zwc.sellsys.android.entity.UserBean;
import com.zwc.sellsys.android.utils.AppUtil;
import com.zwc.sellsys.android.utils.CounterUtil;
import com.zwc.sellsys.android.utils.DateUtil;
import com.zwc.sellsys.android.utils.StringUtil;
import com.zwc.sellsys.android.utils.ToastUtil;

public class AddOrderInfoActivity extends BaseActivity {
    public static final int REQUEST_CODE_CHOOSE_USER = 1;
    public static final int REQUEST_CODE_CHOOSE_INTRODUCER = 2;
    private static final int REQUEST_CODE_CHOOSE_SPECIFICATION = 3;
    private TextView mTextViewBuyer;
    private TextView mTextViewIntroducer;
    private EditText mEditTextCount;
    private TextView mTextViewSpecification;
    private EditText mEditTextSellPrice;
    private Button mButtonMinus;
    private Button mButtonPlus;
    private Button mButtonAddOrder;
    private Switch mSwitchPay;
    private Switch mSwitchFree;

    private OrderDao mOrderDao;
    private OrderBean mOrderBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != mActionBar) {
            mActionBar.show();
            mActionBar.setTitle("添加订单信息");
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeButtonEnabled(true);
        }

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_add_order_info);
    }

    @Override
    protected void initView() {
        mTextViewBuyer = findViewById(R.id.tv_buyer);
        mTextViewIntroducer = findViewById(R.id.tv_introducer);
        mButtonMinus = findViewById(R.id.btn_minus);
        mButtonPlus = findViewById(R.id.btn_plus);
        mButtonAddOrder = findViewById(R.id.btn_add_order);
        mTextViewSpecification = findViewById(R.id.tv_specification);
        mEditTextCount = findViewById(R.id.et_count);
        mSwitchFree = findViewById(R.id.switch_free);
        mSwitchPay = findViewById(R.id.switch_pay);
        mEditTextSellPrice = findViewById(R.id.et_sell_price);

        mTextViewBuyer.setOnClickListener(this);
        mTextViewIntroducer.setOnClickListener(this);
        mButtonMinus.setOnClickListener(this);
        mButtonPlus.setOnClickListener(this);
        mButtonAddOrder.setOnClickListener(this);
        mTextViewSpecification.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mOrderDao = new OrderDao();
        mOrderBean = new OrderBean();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_buyer:
                Intent intent = new Intent(this, ChooseUserActivity.class);
                startActivityForResult(intent, REQUEST_CODE_CHOOSE_USER);
                break;
            case R.id.tv_introducer:
                Intent intent2 = new Intent(this, ChooseUserActivity.class);
                startActivityForResult(intent2, REQUEST_CODE_CHOOSE_INTRODUCER);
                break;
            case R.id.btn_minus:
                String content = mEditTextCount.getText().toString().trim();
                if (!StringUtil.isEmpty(content)) {
                    int orientSum = Integer.valueOf(content);
                    int minusSum = CounterUtil.count(CounterUtil.Type.MINUS, orientSum);
                    mEditTextCount.setText(String.valueOf(minusSum));
                } else {
                    mEditTextCount.setText("0");
                }
                break;
            case R.id.btn_plus:
                String content1 = mEditTextCount.getText().toString().trim();
                if (!StringUtil.isEmpty(content1)) {
                    int orientSum1 = Integer.valueOf(content1);
                    int plusSum = CounterUtil.count(CounterUtil.Type.PLUS, orientSum1);
                    mEditTextCount.setText(String.valueOf(plusSum));
                } else {
                    mEditTextCount.setText("0");
                }
                break;
            case R.id.tv_specification:
                Intent chooseSpecificationIntent = new Intent(this, ChooseSpecificationActivity.class);
                startActivityForResult(chooseSpecificationIntent, REQUEST_CODE_CHOOSE_SPECIFICATION);
                break;
            case R.id.btn_add_order:
                addOrder();
                break;
            default:
                break;
        }
        super.onClick(v);
    }


    /**
     * 添加订单到数据库
     */
    private void addOrder() {
        String sellPrice = mEditTextSellPrice.getText().toString().trim();
        String count = mEditTextCount.getText().toString().trim();
        mOrderBean.setSellPrice(Integer.parseInt(sellPrice));
        mOrderBean.setCount(Integer.parseInt(count));
        mOrderBean.setFree(1);
        mOrderBean.setIsPay(1);
        mOrderBean.setBuyTime(DateUtil.getFormatCurrentDate());
        mOrderBean.setSendTime(DateUtil.getFormatCurrentDate());
        mOrderBean.setPayTime(DateUtil.getFormatCurrentDate());
        mOrderBean.setDeliveryTime(DateUtil.getFormatCurrentDate());
        boolean result = mOrderDao.insert(mOrderBean);
        if (result) {
            ToastUtil.show("添加订单成功");
            setResult(RESULT_OK);
        } else {
            ToastUtil.show("添加订单失败");
        }

        AppUtil.removeActivity(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_CHOOSE_USER:
                    UserBean userBean = (UserBean) data.getSerializableExtra(Constants.EXTRA_USER_BEAN_KEY);
                    mOrderBean.setBuyerId(userBean.getUserId());
                    mTextViewBuyer.setText("购买者：" + userBean.getName());
                    break;
                case REQUEST_CODE_CHOOSE_INTRODUCER:
                    UserBean userBean2 = (UserBean) data.getSerializableExtra(Constants.EXTRA_USER_BEAN_KEY);
                    mOrderBean.setIntroducerId(Integer.parseInt(userBean2.getUserId()));
                    mTextViewIntroducer.setText("推荐人：" + userBean2.getName());
                    break;
                case REQUEST_CODE_CHOOSE_SPECIFICATION:
                    SpecificationBean specBean = (SpecificationBean) data.getSerializableExtra(Constants.EXTRA_SPECIFICATION_KEY);
                    mOrderBean.setSpecificationId(Integer.parseInt(specBean.getSpecificationId()));
                    //mOrderBean.setCostPrice(specBean.getPrice());
                    mTextViewSpecification.setText("规格：" + specBean.getWeight() );
                    break;
            }
        }

    }
}
