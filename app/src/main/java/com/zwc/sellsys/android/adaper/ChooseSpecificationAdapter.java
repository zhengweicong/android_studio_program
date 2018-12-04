package com.zwc.sellsys.android.adaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zwc.sellsys.android.R;
import com.zwc.sellsys.android.entity.SpecificationBean;
import com.zwc.sellsys.android.listener.OnItemClickListener;

import java.util.List;

public class ChooseSpecificationAdapter extends RecyclerView.Adapter<ChooseSpecificationAdapter.MyHolder> {
    private Context mContext;
    private List<SpecificationBean> mSpecificationBeans;
    private OnItemClickListener<SpecificationBean> listener;

    public void setOnItemClickListener(OnItemClickListener<SpecificationBean> listener) {
        this.listener = listener;
    }

    public ChooseSpecificationAdapter(Context context, List<SpecificationBean> specificationBeans) {
        mContext = context;
        mSpecificationBeans = specificationBeans;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_choose_specification, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        final SpecificationBean specificationBean = mSpecificationBeans.get(position);
        if (null != specificationBean) {
            holder.mTextViewName.setText(specificationBean.getDescription());
            holder.mTextViewWeight.setText(specificationBean.getWeight() + "斤/"
                    + Double.parseDouble(specificationBean.getWeight()) / 2 + "kg");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(specificationBean);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return null != mSpecificationBeans ? mSpecificationBeans.size() : 0;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView mTextViewName;
        TextView mTextViewWeight;

        public MyHolder(View itemView) {
            super(itemView);
            mTextViewName = itemView.findViewById(R.id.tv_choose_specification_name);
            mTextViewWeight = itemView.findViewById(R.id.tv_choose_specification_weight);
        }
    }
}
