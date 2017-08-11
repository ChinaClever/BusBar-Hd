package com.clever.www.busbar.navigation;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.clever.www.busbar.MainActivity;
import com.clever.www.busbar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.navigation_fragment, container, false);

        RadioGroup radioGroup = view.findViewById(R.id.bar_menu_bar);
        radioGroup.setOnCheckedChangeListener(this);

        RadioButton homeBtn = view.findViewById(R.id.homeBtn);
        homeBtn.setChecked(true);

        return view;
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        int id = 0;
        switch (i)  {
            case R.id.homeBtn:   id = 0;  break;
            case R.id.mainBtn: id = 1; break;
            case R.id.branchBtn: id = 2; break;
            case R.id.boxBtn:  id = 3;  break;
            case R.id.settingsBtn:  id = 4;  break;
        }

        MainActivity activity = (MainActivity) getActivity();
        activity.btmenuChanged(id);
    }
}
