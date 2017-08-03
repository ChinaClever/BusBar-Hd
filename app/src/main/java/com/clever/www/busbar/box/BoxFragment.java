package com.clever.www.busbar.box;


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
public class BoxFragment extends Fragment {
    private List<BoxItem> boxItems = new ArrayList<>();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.box_fragment, container, false);

        init();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        BoxAdapter adapter = new BoxAdapter(boxItems);
        recyclerView.setAdapter(adapter);


        return view;
    }

    private void init() {
        for(int i=0; i<10; ++i) {
//            BoxItem item = new BoxItem(i);
            boxItems.add(new BoxItem(i));
        }
    }
}
