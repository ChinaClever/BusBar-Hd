package com.clever.www.busbar.branch;


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
public class BranchFragment extends Fragment {
    private List<BranchItem> mBranchItems = new ArrayList<>();
    private BranchUpdate mBranchUpdate = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.branch_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        BranchAdapter adapter = new BranchAdapter(mBranchItems);
        recyclerView.setAdapter(adapter);

        mBranchUpdate = new BranchUpdate();
        mBranchUpdate.setData(adapter, mBranchItems);

        return view;
    }


}
