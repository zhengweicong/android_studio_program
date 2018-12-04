package com.zwc.sellsys.android.adaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zwc.sellsys.android.R;
import com.zwc.sellsys.android.entity.AboutCategoryBean;

import java.util.List;

public class AboutFragmentAdpater extends RecyclerView.Adapter<AboutFragmentAdpater.MyHolder> {
    private Context mContext;
    private List<AboutCategoryBean> nameList;

    public AboutFragmentAdpater(Context context, List<AboutCategoryBean> names) {
        mContext = context;
        nameList = names;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate( R.layout.item_about_category,parent,false);
        MyHolder holder = new MyHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        final AboutCategoryBean cb = nameList.get(position);
        holder.name.setText(cb.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
