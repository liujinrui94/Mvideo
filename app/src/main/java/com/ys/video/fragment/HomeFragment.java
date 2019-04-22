package com.ys.video.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ys.video.R;
import com.ys.video.base.BaseFragment;
import com.ys.video.databinding.HomeFragmentBinding;

/**
 * @author:liujinrui
 * @Date:2019/4/17
 * @Description:
 */
public class HomeFragment extends BaseFragment {


    public static HomeFragment newInstance(String s) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("String", s);
        fragment.setArguments(args);
        return fragment;
    }

    private HomeFragmentBinding binding;

    private String string;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        string = getArguments().getString("String");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, null, false);
        binding.setViewCtrl(this);
        binding.tvText.setText(string);
        return binding.getRoot();
    }


}
