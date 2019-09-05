package com.zwc.sellsys.android.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zwc.sellsys.android.R;
import com.zwc.sellsys.android.adaper.AboutFragmentAdpater;
import com.zwc.sellsys.android.entity.AboutCategoryBean;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends BaseFragment {
    public static final String TAG = AboutUsFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private AboutFragmentAdpater mAdpater;



    public AboutUsFragment() {
        // Required empty public constructor
    }

    public static AboutUsFragment newInstance() {
        AboutUsFragment fragment = new AboutUsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);



        mRecyclerView = view.findViewById(R.id.rv_about_category);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<AboutCategoryBean> beans = new ArrayList<AboutCategoryBean>();
        AboutCategoryBean bean = new AboutCategoryBean();
        bean.setName("关于我");
        beans.add(bean);
        mAdpater = new AboutFragmentAdpater(getActivity(), beans);
        mRecyclerView.setAdapter(mAdpater);

        return view;
    }

}
