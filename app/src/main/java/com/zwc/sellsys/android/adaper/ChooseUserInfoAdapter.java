package com.zwc.sellsys.android.adaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zwc.sellsys.android.R;
import com.zwc.sellsys.android.entity.UserBean;
import com.zwc.sellsys.android.listener.OnItemClickListener;

import java.util.List;

public class ChooseUserInfoAdapter extends RecyclerView.Adapter<ChooseUserInfoAdapter.MyHolder> {
    private Context mContext;
    private List<UserBean> mUserBeans;

    private OnItemClickListener<UserBean> listener;

    public ChooseUserInfoAdapter(Context context, List<UserBean> userBeans) {
        mContext = context;
        mUserBeans = userBeans;
    }

    public void setOnItemClickListener(OnItemClickListener<UserBean> listener) {
        this.listener = listener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_choose_user_info, parent,false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        final UserBean userBean = mUserBeans.get(position);
        if (null != userBean) {
            holder.mTextViewName.setText(userBean.getName());
            holder.mTextViewId.setText(userBean.getUserId());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.onItemClick(userBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return null != mUserBeans ? mUserBeans.size() : 0;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView mTextViewId;
        TextView mTextViewName;

        public MyHolder(View itemView) {
            super(itemView);
            mTextViewId = itemView.findViewById(R.id.tv_choose_user_id);
            mTextViewName = itemView.findViewById(R.id.tv_choose_user_name);
        }
    }
}
