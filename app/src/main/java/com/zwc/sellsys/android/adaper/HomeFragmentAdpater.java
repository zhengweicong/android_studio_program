package com.zwc.sellsys.android.adaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zwc.sellsys.android.R;
import com.zwc.sellsys.android.activity.CompensationInfoActivity;
import com.zwc.sellsys.android.activity.OrderInfoActivity;
import com.zwc.sellsys.android.activity.SpecificationActivity;
import com.zwc.sellsys.android.activity.UserInfoActivity;
import com.zwc.sellsys.android.entity.HomeCategoryBean;
import com.zwc.sellsys.android.entity.Type;
import com.zwc.sellsys.android.utils.IntentUtil;

import java.util.List;

public class HomeFragmentAdpater extends RecyclerView.Adapter<HomeFragmentAdpater.MyHolder> {
    private Context mContext;
    private List<HomeCategoryBean> nameList;

    public HomeFragmentAdpater(Context context, List<HomeCategoryBean> names) {
        mContext = context;
        nameList = names;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate( R.layout.item_home_category,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        final HomeCategoryBean cb = nameList.get(position);
        holder.name.setText(cb.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cb.getType().equals(Type.BUY_RECORD)) {
                    IntentUtil.startActivity(mContext, OrderInfoActivity.class);
                } else if (cb.getType().equals(Type.USER_INFO)) {
                    IntentUtil.startActivity(mContext, UserInfoActivity.class);
                }else if(cb.getType().equals(Type.SPECIFICATION)){
                    IntentUtil.startActivity(mContext, SpecificationActivity.class);
                }else if(cb.getType().equals(Type.COMPENSATION)){
                    IntentUtil.startActivity(mContext, CompensationInfoActivity.class);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView name;

        public MyHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
        }
    }
}
