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

        public ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
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

    private void updateData(ViewHolder holder, BoxItem boxItem) {
        TextView tv =  holder.mItemView.findViewById(R.id.id_tv);
        String str = (boxItem.getId() + 1) +"";
        tv.setText(str);

        tv = holder.mItemView.findViewById(R.id.name_tv);
        str = boxItem.getName();
        if(str.isEmpty())
            str = "iBox-" +(boxItem.getId() + 1);
        tv.setText(str);

        tv = holder.mItemView.findViewById(R.id.temp_tv);
        double value = boxItem.getTemp();
        if(value < 0) str = "---";
        else str = value + "";
        tv.setText(str);

        tv = holder.mItemView.findViewById(R.id.line_tv);
        value = boxItem.getLine();
        if(value < 0) str = "---";
        else str = ((int)value+1) + "";
        tv.setText(str);

        tv = holder.mItemView.findViewById(R.id.vol_tv);
        value = boxItem.getVol();
        if(value < 0) str = "---";
        else str = (value+1) + "";
        tv.setText(str);

        tv = holder.mItemView.findViewById(R.id.cur_tv);
        value = boxItem.getCur();
        if(value < 0) str = "---";
        else str = (value+1) + "";
        tv.setText(str);

        tv = holder.mItemView.findViewById(R.id.ele_tv);
        value = boxItem.getEle();
        if(value < 0) str = "---";
        else str = (value+1) + "";
        tv.setText(str);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BoxItem boxItem = mBoxItems.get(position);
        updateData(holder, boxItem);
    }

    @Override
    public int getItemCount() {
        return mBoxItems.size();
    }
}
