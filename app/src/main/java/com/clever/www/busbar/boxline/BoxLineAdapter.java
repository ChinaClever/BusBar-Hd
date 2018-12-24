package com.clever.www.busbar.boxline;

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

public class BoxLineAdapter extends RecyclerView.Adapter<BoxLineAdapter.ViewHolder>{
    private List<BoxLineItem> mBoxLineItems;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView  nameTv, volTv,curTv, powTv,pfTv, eleTv,plTv, curThdTv,temTv;
        View mItemView;

        public ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;

            nameTv = itemView.findViewById(R.id.name_tv);
            volTv = itemView.findViewById(R.id.vol_tv);
            curTv = itemView.findViewById(R.id.cur_tv);
            plTv = itemView.findViewById(R.id.pl_tv);
            curThdTv = itemView.findViewById(R.id.cur_thd_tv);

            powTv = itemView.findViewById(R.id.pow_tv);
            pfTv = itemView.findViewById(R.id.pf_tv);
            eleTv = itemView.findViewById(R.id.ele_tv);
            temTv = itemView.findViewById(R.id.tem_tv);
        }
    }

    public BoxLineAdapter(List<BoxLineItem> boxLineItems) {
        mBoxLineItems = boxLineItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.box_line_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                BoxLineItem item = mBoxLineItems.get(position);
                Toast.makeText(view.getContext(), position+item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return holder;
    }

    private void updateData(ViewHolder holder, BoxLineItem boxLineItem) {
        BoxLineItem item = boxLineItem;

        String str = item.getName();
        holder.nameTv.setText(str);

        double value = item.getVol();
        if(value>=0) str = (int)value +"V";
        else  str = "---";
        holder.volTv.setText(str);

        value = item.getCur();
        if(value>=0) str = value +"A";
        else  str = "---";
        holder.curTv.setText(str);

        value = item.getPl();
        if(value>=0) str = value +"%";
        else  str = "---";
        holder.plTv.setText(str);

        value = item.getCurThd();
        if(value>=0) str = value +"%";
        else  str = "---";
        holder.curThdTv.setText(str);


        value = item.getPow();
        if(value>=0) str = value +"KW";
        else  str = "---";
        holder.powTv.setText(str);

        value = item.getPf();
        if(value>=0) str = value +"";
        else  str = "---";
        holder.pfTv.setText(str);

        value = item.getEle();
        if(value>=0) str = value +"KWh";
        else  str = "---";
        holder.eleTv.setText(str);

        value = item.getTem();
        if(value>=0) str = value +"℃";
        else  str = "---";
        holder.temTv.setText(str);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BoxLineItem boxLineItem = mBoxLineItems.get(position);
        updateData(holder, boxLineItem);
    }

    @Override
    public int getItemCount() {
        return mBoxLineItems.size();
    }
}
