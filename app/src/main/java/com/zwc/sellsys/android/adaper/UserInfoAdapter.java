package com.zwc.sellsys.android.adaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zwc.sellsys.android.R;
import com.zwc.sellsys.android.entity.UserBean;

import java.util.List;

public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.MyHolder> {
    private Context mContext;
    private List<UserBean> mUserInfos;

    public UserInfoAdapter(Context context, List<UserBean> userinfos) {
        this.mContext = context;
        this.mUserInfos = userinfos;
    }

    /**
     * 更新数据
     *
     * @param userinfos 需要更新的数据集
     */
    public void update(List<UserBean> userinfos) {
        this.mUserInfos.clear();
        this.mUserInfos.addAll(userinfos);
        notifyDataSetChanged();
    }

    /**
     * 更新并加载更多数据
     *
     * @param userinfos 新加载的数据集
     */
    public void updateAndLoadMore(List<UserBean> userinfos) {
        this.mUserInfos.addAll(mUserInfos.size(), userinfos);
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.item_user_info,parent,false);
        MyHolder holder = new MyHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        UserBean userBean = mUserInfos.get(position);
        if (null != userBean) {
            holder.tvUserName.setText(userBean.getName());
            holder.tvMobile.setText(userBean.getPhone());
            holder.tvUserAddress.setText(userBean.getAddress());
            holder.tvUserId.setText(userBean.getUserId());
        }
    }

    @Override
    public int getItemCount() {
        return null != mUserInfos ? mUserInfos.size() : 0;
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView tvUserName;
        TextView tvUserAddress;
        TextView tvMobile;
        TextView tvUserId;

        public MyHolder(View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tv_user_name);
            tvUserAddress = itemView.findViewById(R.id.tv_address);
            tvMobile = itemView.findViewById(R.id.tv_mobile);
            tvUserId = itemView.findViewById(R.id.tv_user_id);
        }
    }
}


