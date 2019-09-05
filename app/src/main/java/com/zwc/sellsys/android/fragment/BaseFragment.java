package com.zwc.sellsys.android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zwc.sellsys.android.umeng.statistic.UmengStatisticUtil;

public class BaseFragment extends Fragment {
    public static final String TAG = BaseFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        UmengStatisticUtil.onPageStart(TAG);
    }

    @Override
    public void onPause() {
        super.onPause();
        UmengStatisticUtil.onPageEnd(TAG);
    }
}
