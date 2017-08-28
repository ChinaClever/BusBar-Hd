package com.clever.www.busbar.branch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.clever.www.busbar.R;
import com.clever.www.busbar.box.BoxActivity;
import com.clever.www.busbar.dp.data.hash.data.BusHashTable;
import com.clever.www.busbar.login.LoginStatus;

import java.util.List;

/**
 * Created by Lzy on 17-8-4.
 */

public class BranchAdapter extends RecyclerView.Adapter<BranchAdapter.ViewHolder> {
    private List<BranchItem> mBranchItems = null;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idTv, nameTv, statusTv,volTv,curTv, powTv,appowTv,pfTv, eleTv,temTv;
        View mItemView;

        public ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;

            idTv = itemView.findViewById(R.id.id_tv);
            nameTv = itemView.findViewById(R.id.name_tv);
            statusTv = itemView.findViewById(R.id.status_tv);
            volTv = itemView.findViewById(R.id.vol_tv);
            curTv = itemView.findViewById(R.id.cur_tv);
            powTv = itemView.findViewById(R.id.pow_tv);
            appowTv = itemView.findViewById(R.id.appow_tv);
            pfTv = itemView.findViewById(R.id.pf_tv);
            eleTv = itemView.findViewById(R.id.ele_tv);
            temTv = itemView.findViewById(R.id.tem_tv);
        }
    }

    public BranchAdapter(List<BranchItem> branchItems) {
        mBranchItems = branchItems;
    }


    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.branch_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);

        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                BranchItem item = mBranchItems.get(position);

                int busId = LoginStatus.login_devNum;
                int boxNum = BusHashTable.getBoxNum(busId);
                if(position < boxNum) {
                    BoxActivity.actionStart(parent.getContext(), position);
                } else {
                    Toast.makeText(view.getContext(), R.string.box_offline_info, Toast.LENGTH_SHORT).show();
                }

            }
        });



        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BranchItem item = mBranchItems.get(position);

        holder.idTv.setText(item.getId() + "");

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
        if(value>=0) str = (int)value +"C";
        else  str = "---";
        holder.temTv.setText(str);

    }

    @Override
    public int getItemCount() {
        return mBranchItems.size();
    }

}
