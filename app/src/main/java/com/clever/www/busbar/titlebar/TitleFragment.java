package com.clever.www.busbar.titlebar;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.clever.www.busbar.R;
import com.clever.www.busbar.login.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class TitleFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_title, container, false);

        Button btn =  view.findViewById(R.id.loginBtn);
        btn.setOnClickListener(onClickListener);


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



}
