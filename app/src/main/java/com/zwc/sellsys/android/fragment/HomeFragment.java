package com.zwc.sellsys.android.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zwc.sellsys.android.R;
import com.zwc.sellsys.android.adaper.HomeFragmentAdpater;
import com.zwc.sellsys.android.entity.HomeCategoryBean;
import com.zwc.sellsys.android.entity.Type;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class HomeFragment extends BaseFragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView mRecyclerView;
    private HomeFragmentAdpater mAdpater;


    public HomeFragment() {
        // Required empty public constructor
    }



    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        mRecyclerView = view.findViewById(R.id.rv_category);
        List<HomeCategoryBean> names = new ArrayList<HomeCategoryBean>();
        HomeCategoryBean cat1 = new HomeCategoryBean();
        cat1.setName("用户信息");
        cat1.setType(Type.USER_INFO);
        names.add(cat1);

        HomeCategoryBean cat2 = new HomeCategoryBean();
        cat2.setName("购买记录信息");
        cat2.setType(Type.BUY_RECORD);
        names.add(cat2);

        HomeCategoryBean cat3 = new HomeCategoryBean();
        cat3.setName("苹果规格信息");
        cat3.setType(Type.SPECIFICATION);
        names.add(cat3);
        HomeCategoryBean cat4 = new HomeCategoryBean();
        cat4.setName("赔偿信息");
        cat4.setType(Type.COMPENSATION);
        names.add(cat4);
        mAdpater = new HomeFragmentAdpater(getActivity(),names);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(mAdpater);
        return view;
    }



}
