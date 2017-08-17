package com.clever.www.busbar.setting.setbox;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.clever.www.busbar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lzy on 17-8-16.
 */

public class SetBoxAdapter extends RecyclerView.Adapter<SetBoxAdapter.ViewHolder>{
    private List<SetBoxItem> mItems;
    private int selectedPosition = -5; //默认一个参数

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idTv, nameTv;
        List<TextView> curTvs = new ArrayList<>();

        public ViewHolder(View itemView) {
            super(itemView);

            idTv = itemView.findViewById(R.id.id_tv);
            nameTv = itemView.findViewById(R.id.name_tv);

            TextView tv = itemView.findViewById(R.id.line_tv_1);
            curTvs.add(tv);

            tv = itemView.findViewById(R.id.line_tv_2);
            curTvs.add(tv);

            tv = itemView.findViewById(R.id.line_tv_3);
            curTvs.add(tv);

        }
    }

    public SetBoxAdapter(List<SetBoxItem> list) {
        mItems = list;
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            switch (v.getId()) {
                case R.id.name_tv:
                    Toast.makeText(v.getContext(), "luozhiyong", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.set_box_item, parent,false);
        ViewHolder holder = new ViewHolder(view);

        holder.nameTv.setOnClickListener(onClickListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        SetBoxItem item = mItems.get(position);
        String str = (item.getId() +1) + "";
        holder.idTv.setText(str);

        str = item.getName();
        holder.idTv.setText(str);

        for(int i=0; i<holder.curTvs.size(); ++i) {
            double value = item.getCur(i);
            if(value < 0)
                str = "---";
            else
                str =  value / 10.0 + "A";
            holder.curTvs.get(i).setText(str);
        }

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


}
