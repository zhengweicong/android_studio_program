package com.zwc.sellsys.android.adaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zwc.sellsys.android.R;
import com.zwc.sellsys.android.entity.OrderBean;

import java.util.List;

public class OrderInfoAdapter extends RecyclerView.Adapter<OrderInfoAdapter.MyHolder> {
    private Context mContext;
    private List<OrderBean> mOrderBeans;

    public OrderInfoAdapter(Context context, List<OrderBean> orderBeans) {
        mContext = context;
        mOrderBeans = orderBeans;
    }

    public void setDataSetChanged(List<OrderBean> orderBeans) {
        mOrderBeans.clear();
        mOrderBeans.addAll(orderBeans);
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_order_info, parent, false);

        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        OrderBean bean = mOrderBeans.get(position);
        holder.mBuyerTextView.setText("购买人：" + bean.getBuyerId());
        holder.mIntroducerTextView.setText("------------推荐人-----" + bean.getIntroducerId() + "----------------");
        holder.mAddressTextView.setText(bean.getDescription());
        holder.mFinalPriceTextView.setText("￥" + bean.getSellPrice());
        holder.mSellPriceTextView.setText("￥" + bean.getSellPrice());
        holder.mCountTextView.setText("x " + bean.getCount());
        holder.mDescriptionTextView.setText("总共购买 " + bean.getCount() + " 箱(件)\n合计：￥ "
                + bean.getSellPrice() * bean.getCount()
                +(bean.getIsPay()==0?"未付款":"已付款"
                +"\n应收："+bean.getSellPrice() * bean.getCount())
                +"\n实收：" + bean.getCostPrice() * bean.getCount()
                +"\n付款日期："+bean.getPayTime()
                +"\n下单日期："+bean.getBuyTime()
                +"\n寄件日期："+bean.getSendTime()
                +"\n送达日期："+bean.getDeliveryTime());
    }

    @Override
    public int getItemCount() {
        return null != mOrderBeans ? mOrderBeans.size() : 0;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView mBuyerTextView;
        TextView mIntroducerTextView;
        TextView mAddressTextView;
        TextView mFinalPriceTextView;
        TextView mSellPriceTextView;
        TextView mCostPriceTextView;
        TextView mCountTextView;
        TextView mDescriptionTextView;

        public MyHolder(View itemView) {
            super(itemView);
            mBuyerTextView = itemView.findViewById(R.id.tv_order_info_buyer);
            mIntroducerTextView = itemView.findViewById(R.id.tv_order_info_introducer);
            mAddressTextView = itemView.findViewById(R.id.tv_order_info_address);
            mFinalPriceTextView = itemView.findViewById(R.id.tv_order_info_final_price);
            mSellPriceTextView = itemView.findViewById(R.id.tv_order_info_sell_price);
            mCostPriceTextView = itemView.findViewById(R.id.tv_order_info_cost_price);
            mCountTextView = itemView.findViewById(R.id.tv_order_info_count);
            mDescriptionTextView = itemView.findViewById(R.id.tv_order_info_description);
        }
    }
}
