package com.clever.www.busbar.boxline;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clever.www.busbar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoxLineFragment extends Fragment {
    private List<BoxLineItem> boxLineItems = new ArrayList<>();
    private BoxLineUpdate mBoxUpdate = null;
    private int mBoxId = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.box_line_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        BoxLineAdapter adapter = new BoxLineAdapter(boxLineItems);
        recyclerView.setAdapter(adapter);

        mBoxUpdate = new BoxLineUpdate();
        mBoxUpdate.setData(adapter, boxLineItems, mBoxId);

        return view;
    }

}
