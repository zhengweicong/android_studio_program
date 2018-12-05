package com.zwc.sellsys.android.adaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zwc.sellsys.android.R;
import com.zwc.sellsys.android.entity.CompensationBean;

import java.util.List;

public class CompensationInfoAdapter extends RecyclerView.Adapter<CompensationInfoAdapter.MyHolder> {
    private Context mContext;
    private List<CompensationBean> mCompensationBeans;

    public CompensationInfoAdapter(Context context, List<CompensationBean> compensationBeans) {
        this.mContext = context;
        this.mCompensationBeans = compensationBeans;
    }

    /**
     * 更新数据
     *
     * @param compensationBeans 需要更新的数据集
     */
    public void update(List<CompensationBean> compensationBeans) {
        this.mCompensationBeans.clear();
        this.mCompensationBeans.addAll(compensationBeans);
        notifyDataSetChanged();
    }

    /**
     * 更新并加载更多数据
     *
     * @param compensationBeans 新加载的数据集
     */
    public void updateAndLoadMore(List<CompensationBean> compensationBeans) {
        this.mCompensationBeans.addAll(mCompensationBeans.size(), compensationBeans);
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.item_compensation_info,parent,false);
        MyHolder holder = new MyHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        CompensationBean compensationBean = mCompensationBeans.get(position);
        if (null != compensationBean) {
            holder.tvOrderId.setText(compensationBean.getOrderId()+"");
            holder.tvDescription.setText(compensationBean.getDescription());
            holder.tvPrice.setText(compensationBean.getPrice()+"");
        }
    }

    @Override
    public int getItemCount() {
        return null != mCompensationBeans ? mCompensationBeans.size() : 0;
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView tvOrderId;
        TextView tvDescription;
        TextView tvPrice;

        public MyHolder(View itemView) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.tv_compensation_order_id);
            tvDescription = itemView.findViewById(R.id.tv_compensation_description);
            tvPrice = itemView.findViewById(R.id.tv_compensation_price);
        }
    }
}


