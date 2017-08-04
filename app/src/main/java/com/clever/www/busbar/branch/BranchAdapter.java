package com.clever.www.busbar.branch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.clever.www.busbar.R;

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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.branch_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                BranchItem item = mBranchItems.get(position);

                Toast.makeText(view.getContext(),"branch id " +  item.getId(), Toast.LENGTH_SHORT).show();

            }
        });



        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BranchItem item = mBranchItems.get(position);

        holder.idTv.setText(item.getId() + "");
        holder.nameTv.setText(item.getName());

        String str = item.getVol() +"V";
        holder.volTv.setText(str);

        str = item.getCur() +"A";
        holder.curTv.setText(str);

        str = item.getPow() +"KW";
        holder.powTv.setText(str);

        str = item.getApPow() +"KVA";
        holder.appowTv.setText(str);

        str = item.getPf() +"";
        holder.pfTv.setText(str);

        str = item.getEle() +"KWh";
        holder.eleTv.setText(str);

        str = item.getTem() +"C";
        holder.temTv.setText(str);
    }

    @Override
    public int getItemCount() {
        return mBranchItems.size();
    }


}
