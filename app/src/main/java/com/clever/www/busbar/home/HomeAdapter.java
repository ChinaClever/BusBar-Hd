package com.clever.www.busbar.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clever.www.busbar.R;

import java.util.List;

/**
 * Created by Lzy on 17-8-4.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    private List<HomeItem> mHomeItems;

    static class ViewHolder extends RecyclerView.ViewHolder {
        HomeItemCst itemCst_1, itemCst_2;

        public ViewHolder(View itemView) {
            super(itemView);

            itemCst_1 = itemView.findViewById(R.id.cst_1);
            itemCst_2 = itemView.findViewById(R.id.cst_2);
        }
    }

    public HomeAdapter(List<HomeItem>  list) {
        mHomeItems = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HomeItem item = mHomeItems.get(position);

        holder.itemCst_1.setBoxId(item.getId());
        holder.itemCst_2.setBoxId(item.getId()+1);
    }

    @Override
    public int getItemCount() {
        return mHomeItems.size();
    }




}
