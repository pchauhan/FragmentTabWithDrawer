package com.sn.fragmenttabwithdrawer.TabA;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sn.fragmenttabwithdrawer.constant.AppConstants;
import com.sn.fragmenttabwithdrawer.constant.BaseFragment;
import com.sn.fragmenttabwithdrawer.R;

public class AppTabAFirstFragment extends BaseFragment {
    private Button mGotoButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view       =   inflater.inflate(R.layout.app_tab_a_first_screen, container, false);
        mGotoButton =   (Button) view.findViewById(R.id.id_next_tab_a_button);
        TextView tv = (TextView)view.findViewById(R.id.tv);
        tv.setText("Tab A  \nFirst Fragment");
        mGotoButton.setOnClickListener(listener);
        return view;
    }

    private OnClickListener listener        =   new OnClickListener(){
        @Override
        public void onClick(View v){
            mActivity.pushFragments(AppConstants.TAB_A, new AppTabASecondFragment(),true,true);
        }
    };
}
