package com.sn.fragmenttabwithdrawer.TabC;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sn.fragmenttabwithdrawer.R;
import com.sn.fragmenttabwithdrawer.constant.AppConstants;
import com.sn.fragmenttabwithdrawer.constant.BaseFragment;

import org.w3c.dom.Text;


public class AppTabCFirstFragment extends BaseFragment {

    private Button mGotoButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view       =   inflater.inflate(R.layout.app_tab_b_first_screen, container, false);
        TextView tv = (TextView)view.findViewById(R.id.tv);
        tv.setText("Tab C  \nFirst Fragment");
        mGotoButton =   (Button) view.findViewById(R.id.id_next_tab_b_button);
        mGotoButton.setOnClickListener(listener);

        return view;
    }

    private OnClickListener listener        =   new OnClickListener(){
        @Override
        public void onClick(View v){
            /* Go to next fragment in navigation stack*/
            mActivity.pushFragments(AppConstants.TAB_C, new AppTabCSecondFragment(),true,true);
        }
    };
}
