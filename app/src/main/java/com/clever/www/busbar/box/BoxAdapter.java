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
        TextView idTv, nameTv, lineTv, volTv,curTv, eleTv,temTv;
        View mItemView;

        public ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;

            idTv = itemView.findViewById(R.id.id_tv);
            nameTv = itemView.findViewById(R.id.name_tv);
            temTv = itemView.findViewById(R.id.tem_tv);

            lineTv = itemView.findViewById(R.id.line_tv);
            volTv = itemView.findViewById(R.id.vol_tv);
            curTv = itemView.findViewById(R.id.cur_tv);
            eleTv = itemView.findViewById(R.id.ele_tv);
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
        String str = (boxItem.getId() + 1) +"";
        holder.idTv.setText(str);

        str = boxItem.getName();
        if(str.isEmpty())
            str = "iBox-" +(boxItem.getId() + 1);
        holder.nameTv.setText(str);

        double value = boxItem.getTemp();
        if(value < 0) str = "---";
        else str = value + "C";
        holder.temTv.setText(str);

        value = boxItem.getLine();
        if(value < 0) str = "---";
        else str = "L" + ((int)value+1) + "";
        holder.lineTv.setText(str);

        value = boxItem.getVol();
        if(value < 0) str = "---";
        else str = (value+1) + "";
        holder.volTv.setText(str);

        value = boxItem.getCur();
        if(value < 0) str = "---";
        else str = (value+1) + "";
        holder.curTv.setText(str);

        value = boxItem.getEle();
        if(value < 0) str = "---";
        else str = (value+1) + "";
        holder.eleTv.setText(str);
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
