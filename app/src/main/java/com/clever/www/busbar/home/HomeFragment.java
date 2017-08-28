package com.clever.www.busbar.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clever.www.busbar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private List<HomeItem> mHomeItems = new ArrayList<>();
    private HomeUpdate mHomeUpdate = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        initHomeItem();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        HomeAdapter adapter = new HomeAdapter(mHomeItems);
        recyclerView.setAdapter(adapter);

        mHomeUpdate = new HomeUpdate();
        mHomeUpdate.setData(adapter, mHomeItems);

        return view;
    }

    private void initHomeItem() {
        for(int i=1; i<=1; i+=2) {
            HomeItem item = new HomeItem(i);
            mHomeItems.add(item);
        }
    }

}
