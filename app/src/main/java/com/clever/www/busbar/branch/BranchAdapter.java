package com.clever.www.busbar.branch;

import android.graphics.Color;
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
        TextView idTv, nameTv, statusTv,curATv,curBTv, curCTv,eleATv, eleBTv,eleCTv;
        View mItemView;

        public ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;

            idTv = itemView.findViewById(R.id.id_tv);
            nameTv = itemView.findViewById(R.id.name_tv);
            statusTv = itemView.findViewById(R.id.status_tv);
            curATv = itemView.findViewById(R.id.cur_tv_A);
            eleATv = itemView.findViewById(R.id.ele_tv_A);
            curBTv = itemView.findViewById(R.id.cur_tv_B);
            eleBTv = itemView.findViewById(R.id.ele_tv_B);
            curCTv = itemView.findViewById(R.id.cur_tv_C);
            eleCTv = itemView.findViewById(R.id.ele_tv_C);
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
                    BoxActivity.actionStart(parent.getContext(), item.getId());
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

        int status = item.getStatus();
        if(status == 0) {
            holder.statusTv.setText(R.string.branch_status_normal);
            holder.statusTv.setTextColor(Color.BLACK);
        } else {
            holder.statusTv.setText(R.string.branch_status_abnormal);
            holder.statusTv.setTextColor(Color.RED);
        }

        double value = item.getCur(0);
        if(value>=0) str = value +"A";
        else  str = "---";
        holder.curATv.setText(str);

        value = item.getCur(1);
        if(value>=0) str = value +"A";
        else  str = "---";
        holder.curBTv.setText(str);

        value = item.getCur(2);
        if(value>=0) str = value +"A";
        else  str = "---";
        holder.curCTv.setText(str);

        value = item.getEle(0);
        if(value>=0) str = value +"KWh";
        else  str = "---";
        holder.eleATv.setText(str);

        value = item.getEle(1);
        if(value>=0) str = value +"KWh";
        else  str = "---";
        holder.eleBTv.setText(str);

        value = item.getEle(2);
        if(value>=0) str = value +"KWh";
        else  str = "---";
        holder.eleCTv.setText(str);
    }

    @Override
    public int getItemCount() {
        return mBranchItems.size();
    }

}
