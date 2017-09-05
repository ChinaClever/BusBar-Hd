package com.clever.www.busbar.box;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clever.www.busbar.R;

import java.util.List;

/**
 * Created by Lzy on 17-8-8.
 */

public class BoxAdapter extends RecyclerView.Adapter<BoxAdapter.ViewHolder>{
    private List<BoxItem> mBoxItems;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv, swTv, curTv, volTv, powTv, appowTv, eleTv, temTv, pfTv;
        public ViewHolder(View itemView) {
            super(itemView);

            nameTv = itemView.findViewById(R.id.name_tv);
            swTv = itemView.findViewById(R.id.sw_tv);
            curTv = itemView.findViewById(R.id.cur_tv);
            volTv = itemView.findViewById(R.id.vol_tv);
            powTv = itemView.findViewById(R.id.pow_tv);
            appowTv = itemView.findViewById(R.id.appow_tv);
            pfTv = itemView.findViewById(R.id.pf_tv);
            eleTv = itemView.findViewById(R.id.ele_tv);
            temTv = itemView.findViewById(R.id.tem_tv);
        }
    }

    public BoxAdapter(List<BoxItem> list) {
        mBoxItems = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.box_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }


    private void setTextColor(TextView tv, int alarm, int crAlarm) {
        int color = Color.BLACK;

        if(alarm > 0) {
            color = Color.RED;
        } else if(crAlarm > 0) {
            color = Color.YELLOW;
        }

        tv.setTextColor(color);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BoxItem item = mBoxItems.get(position);

        String str = item.getName();
        if(str.isEmpty()) str = "Loop " + (position+1);
        holder.nameTv.setText(str);

        int sw = item.getSw();
        if(sw == 0)  holder.swTv.setText(R.string.box_sw_on);
        else if(sw == 1)  holder.swTv.setText(R.string.box_sw_off);
        else holder.swTv.setText("---");

        double value = item.getVol();
        if(value < 0) str = "---";
        else str = (int)value + "V";
        holder.volTv.setText(str);
        setTextColor(holder.volTv, item.getVolALarm(), item.getVolCrALarm());

         value = item.getCur();
        if(value < 0) str = "---";
        else str = value + "A";
        holder.curTv.setText(str);
        setTextColor(holder.curTv, item.getCurALarm(), item.getCurCrALarm());

         value = item.getPow();
        if(value < 0) str = "---";
        else str = value + "KW";
        holder.powTv.setText(str);

        value = item.getPf();
        if(value < 0) str = "---";
        else str = value + "";
        holder.pfTv.setText(str);

         value = item.getAppow();
        if(value < 0) str = "---";
        else str = value + "KVA";
        holder.appowTv.setText(str);

        value = item.getEle();
        if(value < 0) str = "---";
        else str = value + "KWh";
        holder.eleTv.setText(str);

        value = item.getTem();
        if(value>=0) str = value +"℃";
        else  str = "---";
        holder.temTv.setText(str);
        setTextColor(holder.temTv, item.getTemALarm(), item.getTemCrALarm());
    }

    @Override
    public int getItemCount() {
        return mBoxItems.size();
    }

}
