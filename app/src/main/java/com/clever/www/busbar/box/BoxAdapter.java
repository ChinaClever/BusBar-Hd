package com.clever.www.busbar.box;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.clever.www.busbar.R;

import java.util.List;

/**
 * Created by Lzy on 17-8-2.
 */

public class BoxAdapter extends RecyclerView.Adapter<BoxAdapter.ViewHolder>{
    private List<BoxItem> mBoxItems;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View mItemView;
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            tv = itemView.findViewById(R.id.lzy_tv);
        }
    }

    public BoxAdapter(List<BoxItem> boxItems) {
        mBoxItems = boxItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.box_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                BoxItem item = mBoxItems.get(position);
                Toast.makeText(view.getContext(), position+item.getName(), Toast.LENGTH_SHORT).show();
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BoxItem boxItem = mBoxItems.get(position);
        holder.tv.setText(boxItem.getName());
    }



    @Override
    public int getItemCount() {
        return mBoxItems.size();
    }
}
