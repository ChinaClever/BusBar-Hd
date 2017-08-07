package com.clever.www.busbar.titlebar;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.clever.www.busbar.R;
import com.clever.www.busbar.common.timer.HanderTimer;
import com.clever.www.busbar.login.LoginActivity;
import com.clever.www.busbar.login.LoginStatus;

/**
 * A simple {@link Fragment} subclass.
 */
public class TitleFragment extends Fragment {
    private Timers timer = new Timers();
    private TextView mIptv = null;
    private Button mLoginBtn = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.title_fragmen, container, false);

        mLoginBtn =  view.findViewById(R.id.loginBtn);
        mLoginBtn.setOnClickListener(onClickListener);

        mIptv = view.findViewById(R.id.ipTv);

        timer.start(500);

        return  view;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            switch (v.getId()) {
                case R.id.loginBtn:
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    private void updateData() {
        String ip = "---";
        if(LoginStatus.isLogin) {
           ip = LoginStatus.login_ip;
        }
        mIptv.setText("IP: "+ip);


        if(LoginStatus.getLogin()) {
            mLoginBtn.setText(R.string.login_quit);
        } else {
            mLoginBtn.setText(R.string.login_btn);
        }

    }

    private class Timers extends HanderTimer {
        @Override
        public void timeout() {
            updateData();
        }
    }

}
