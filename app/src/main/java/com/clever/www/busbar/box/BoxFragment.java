package com.clever.www.busbar.box;


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
public class BoxFragment extends Fragment {
    private List<BoxItem> boxItems = new ArrayList<>();
    private BoxUpdate mBoxUpdate = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.box_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        BoxAdapter adapter = new BoxAdapter(boxItems);
        recyclerView.setAdapter(adapter);

        mBoxUpdate = new BoxUpdate();
        mBoxUpdate.setData(adapter, boxItems);

        return view;
    }

}
