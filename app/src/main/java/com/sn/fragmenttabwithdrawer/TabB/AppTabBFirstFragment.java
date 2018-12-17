package com.sn.fragmenttabwithdrawer.TabB;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.sn.fragmenttabwithdrawer.R;
import com.sn.fragmenttabwithdrawer.constant.AppConstants;
import com.sn.fragmenttabwithdrawer.constant.BaseFragment;


public class AppTabBFirstFragment extends BaseFragment {

    private Button mGotoButton;
    int counter ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view       =   inflater.inflate(R.layout.app_tab_b_first_screen, container, false);
        TextView tv = (TextView)view.findViewById(R.id.tv);
        tv.setText("Tab B  \nFirst Fragment counter"+String.valueOf(counter));
        mGotoButton =   (Button) view.findViewById(R.id.id_next_tab_b_button);
        mGotoButton.setOnClickListener(listener);

        return view;
    }

    private OnClickListener listener        =   new OnClickListener(){
        @Override
        public void onClick(View v){
            /* Go to next fragment in navigation stack*/
            counter = counter+1;
            mActivity.pushFragments(AppConstants.TAB_B, new AppTabBSecondFragment(),true,true);
        }
    };
}
