package com.zwc.sellsys.android.adaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zwc.sellsys.android.R;
import com.zwc.sellsys.android.entity.SpecificationBean;

import java.util.List;

public class SpecificationAdapter extends RecyclerView.Adapter<SpecificationAdapter.MyHolder> {

    private Context mContext;
    private List<SpecificationBean> mSpecificationBeans;

    public SpecificationAdapter(Context context, List<SpecificationBean> specificationBeans) {
        mContext = context;
        mSpecificationBeans = specificationBeans;
    }
    public void setDataSetChanged(List<SpecificationBean> specificationBeans){
        mSpecificationBeans.clear();
        mSpecificationBeans.addAll(specificationBeans);
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_specification_info, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        SpecificationBean specificationBean = mSpecificationBeans.get(position);
        holder.mSpecificationIdTextView.setText(specificationBean.getSpecificationId());
        holder.mSpecificationDescriptionTextView.setText(specificationBean.getDescription());
        holder.mSpecificationPriceTextView.setText("￥"+specificationBean.getPrice());
        holder.mSpecificationWeightTextView.setText(specificationBean.getWeight() + "斤/"
                + Double.parseDouble(specificationBean.getWeight()) / 2+"kg");

        SpecificationBean.SizeType sizeType = specificationBean.getType();
        if (sizeType.equals(SpecificationBean.SizeType.BIG)) {
            holder.mSpecificationTypeTextView.setText("大果");
        } else if (sizeType.equals(SpecificationBean.SizeType.MEDIUM)) {
            holder.mSpecificationTypeTextView.setText("中果");
        } else if (sizeType.equals(SpecificationBean.SizeType.SMALL)) {
            holder.mSpecificationTypeTextView.setText("小果");
        } else if (sizeType.equals(SpecificationBean.SizeType.SUPER_SMALL)) {
            holder.mSpecificationTypeTextView.setText("小小果");
        } else if (sizeType.equals(SpecificationBean.SizeType.SUPER_BIG)) {
            holder.mSpecificationTypeTextView.setText("超大果");
        }


    }

    @Override
    public int getItemCount() {
        return null != mSpecificationBeans ? mSpecificationBeans.size() : 0;
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView mSpecificationIdTextView;
        TextView mSpecificationPriceTextView;
        TextView mSpecificationTypeTextView;
        TextView mSpecificationWeightTextView;
        TextView mSpecificationDescriptionTextView;

        public MyHolder(View itemView) {
            super(itemView);
            mSpecificationDescriptionTextView = itemView.findViewById(R.id.tv_specification_decription);
            mSpecificationWeightTextView = itemView.findViewById(R.id.tv_specification_weight);
            mSpecificationTypeTextView = itemView.findViewById(R.id.tv_specification_type);
            mSpecificationPriceTextView = itemView.findViewById(R.id.tv_specification_price);
            mSpecificationIdTextView = itemView.findViewById(R.id.tv_specification_id);
        }
    }
}
