package com.clever.www.busbar.line.linelist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clever.www.busbar.R;

import java.util.List;

/**
 * Created by Lzy on 17-8-4.
 */

public class LineAdapter extends RecyclerView.Adapter<LineAdapter.ViewHolder>{
    private List<LineItem> mLineItems = null;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView  nameTv, statusTv,volTv,curTv, powTv,appowTv,pfTv, eleTv,maxCurTv, temTv;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTv = itemView.findViewById(R.id.name_tv);
            statusTv = itemView.findViewById(R.id.status_tv);
            volTv = itemView.findViewById(R.id.vol_tv);
            curTv = itemView.findViewById(R.id.cur_tv);
            maxCurTv = itemView.findViewById(R.id.max_cur_tv);

            powTv = itemView.findViewById(R.id.pow_tv);
            appowTv = itemView.findViewById(R.id.appow_tv);
            pfTv = itemView.findViewById(R.id.pf_tv);
            eleTv = itemView.findViewById(R.id.ele_tv);
            temTv = itemView.findViewById(R.id.tem_tv);
        }
    }

    public LineAdapter(List<LineItem> list) {
        mLineItems = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.line_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LineItem item = mLineItems.get(position);

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

        value = item.getMaxCur();
        if(value>=0) str = value +"A";
        else  str = "---";
        holder.maxCurTv.setText(str);

        value = item.getPow();
        if(value>=0) str = value +"KW";
        else  str = "---";
        holder.powTv.setText(str);

        value = item.getApPow();
        if(value>=0) str = value +"KVA";
        else  str = "---";
        holder.appowTv.setText(str);

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


        //////////////////// 报警字体着色

        //////////////////////  报警颜色
        ////////////============ 倍数










    }

    @Override
    public int getItemCount() {
        return mLineItems.size();
    }


}
