package com.example.playandroid.setting;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.playandroid.R;
import com.example.playandroid.collection.CollectionActivity;


/**
 * A simple {@link Fragment} subclass.
 *
 * @author 彭翔宇
 */
public class MyFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout mHeadProtrait;
    private LinearLayout mCollection;
    private LinearLayout mSetting;
    private ImageView mHeadImg;

    public MyFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        mHeadProtrait = view.findViewById(R.id.head_protrait);
        mCollection = view.findViewById(R.id.collection);
        mSetting = view.findViewById(R.id.setting);
        mHeadImg = view.findViewById(R.id.head_img);
        mCollection.setOnClickListener(this);
        mSetting.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_protrait:
                Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
//                ARouter.getInstance().build("/loginmodule/login").navigation();

                break;
            case R.id.collection:
                getActivity().startActivity(new Intent(getActivity(), CollectionActivity.class));
                break;
            case R.id.setting:
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                getActivity().startActivity(intent);
                break;
            default:
                break;
        }
    }
}
