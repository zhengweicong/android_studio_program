package com.zwc.sellsys.android.activity;

import android.view.View;
import android.widget.EditText;

import com.zwc.sellsys.android.R;
import com.zwc.sellsys.android.db.dao.SpecificationDao;
import com.zwc.sellsys.android.entity.SpecificationBean;
import com.zwc.sellsys.android.utils.AppUtil;
import com.zwc.sellsys.android.utils.ResourseUtil;
import com.zwc.sellsys.android.utils.StringUtil;
import com.zwc.sellsys.android.utils.ToastUtil;

public class AddSpecificationActivity extends BaseActivity {
    private EditText mEditTextWeight;
    private EditText mEditTextPrice;
    private EditText mEditTextDescription;
    private EditText mEditTextType;

    @Override
    protected void setContentView() {

        setContentView(R.layout.activity_add_specification);
    }

    @Override
    protected void initView() {
        mEditTextWeight = findViewById(R.id.et_add_specification_weight);
        mEditTextPrice = findViewById(R.id.et_add_specification_price);
        mEditTextDescription = findViewById(R.id.et_add_specification_description);
        mEditTextType = findViewById(R.id.et_add_specification_type);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_add_specification:
                addSpecification();
                break;
            default:
                break;
        }
    }

    private void addSpecification() {
        String weight = mEditTextWeight.getText().toString().trim();
        String description = mEditTextDescription.getText().toString().trim();
        String price = mEditTextPrice.getText().toString().trim();
        String type = mEditTextType.getText().toString().trim();

        if (StringUtil.isEmpty(weight)) {
            ToastUtil.show(ResourseUtil.getString(
                    R.string.activity_add_specification_weight_hint));
            return;
        }

        if (StringUtil.isEmpty(price)) {
            ToastUtil.show(ResourseUtil.getString(
                    R.string.activity_add_specification_price_hint));
            return;
        }

        if (StringUtil.isEmpty(type)) {
            ToastUtil.show(ResourseUtil.getString(
                    R.string.activity_add_specification_type_hint));
            return;
        }
        if (StringUtil.isEmpty(description)) {
            ToastUtil.show(ResourseUtil.getString(
                    R.string.activity_add_specification_description_hint));
            return;
        }

        SpecificationDao specDao = new SpecificationDao();
        SpecificationBean specBean = new SpecificationBean();
        specBean.setDescription(description);
        specBean.setPrice(price);
        specBean.setWeight(weight);
        if (type.equals("2")) {
            specBean.setType(SpecificationBean.SizeType.MEDIUM);
        } else if (type.equals("1")) {
            specBean.setType(SpecificationBean.SizeType.SMALL);
        } else if (type.equals("3")) {
            specBean.setType(SpecificationBean.SizeType.BIG);
        } else if (type.equals("4")) {
            specBean.setType(SpecificationBean.SizeType.SUPER_BIG);
        } else if (type.equals("0")) {
            specBean.setType(SpecificationBean.SizeType.SUPER_SMALL);
        }
        boolean result = specDao.insert(specBean);
        if (result) {
            setResult(RESULT_OK);
            ToastUtil.show("添加成功");
            AppUtil.removeActivity(this);
        } else {
            ToastUtil.show("添加失败");
        }
    }
}
