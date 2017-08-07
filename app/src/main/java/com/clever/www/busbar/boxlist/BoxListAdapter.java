package com.clever.www.busbar.boxlist;

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

public class BoxListAdapter extends RecyclerView.Adapter<BoxListAdapter.ViewHolder>{
    private List<BoxListItem> mBoxListItems;

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

    public BoxListAdapter(List<BoxListItem> boxListItems) {
        mBoxListItems = boxListItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.box_list_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                BoxListItem item = mBoxListItems.get(position);
                Toast.makeText(view.getContext(), position+item.getName(), Toast.LENGTH_SHORT).show();
            }
        });


        return holder;
    }

    private void updateData(ViewHolder holder, BoxListItem boxListItem) {
        String str = (boxListItem.getId() + 1) +"";
        holder.idTv.setText(str);

        str = boxListItem.getName();
        if(str.isEmpty())
            str = "iBox-" +(boxListItem.getId() + 1);
        holder.nameTv.setText(str);

        double value = boxListItem.getTemp();
        if(value < 0) str = "---";
        else str = value + "C";
        holder.temTv.setText(str);

        value = boxListItem.getLine();
        if(value < 0) str = "---";
        else str = "L" + ((int)value+1) + "";
        holder.lineTv.setText(str);

        value = boxListItem.getVol();
        if(value < 0) str = "---";
        else str = (value+1) + "";
        holder.volTv.setText(str);

        value = boxListItem.getCur();
        if(value < 0) str = "---";
        else str = (value+1) + "";
        holder.curTv.setText(str);

        value = boxListItem.getEle();
        if(value < 0) str = "---";
        else str = (value+1) + "";
        holder.eleTv.setText(str);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BoxListItem boxListItem = mBoxListItems.get(position);
        updateData(holder, boxListItem);
    }

    @Override
    public int getItemCount() {
        return mBoxListItems.size();
    }
}
