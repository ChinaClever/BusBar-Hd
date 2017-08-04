package com.clever.www.busbar.line;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clever.www.busbar.R;
import com.clever.www.busbar.line.linelist.LineAdapter;
import com.clever.www.busbar.line.linelist.LineItem;
import com.clever.www.busbar.line.linelist.LineUpdate;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LineFragment extends Fragment {
    private List<LineItem> mLineItems = new ArrayList<>();
    private LineUpdate mLineUpdate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.line_fragment, container, false);

        initLineItems();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        LineAdapter adapter = new LineAdapter(mLineItems);
        recyclerView.setAdapter(adapter);

        mLineUpdate = new LineUpdate();
        mLineUpdate.setData(adapter, mLineItems);

        return view;
    }

    private void initLineItems() {
        for(int i=0; i<3; ++i) {
            LineItem item = new LineItem(i);
            mLineItems.add(item);
        }
    }


}
