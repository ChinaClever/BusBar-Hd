package com.clever.www.busbar.box;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clever.www.busbar.R;
import com.clever.www.busbar.common.timer.HanderTimer;
import com.clever.www.busbar.dp.data.packages.devdata.DevDatas;
import com.clever.www.busbar.login.LoginStatus;

/**
 * Created by Lzy on 17-8-8.
 */

public class BoxTotalCst extends LinearLayout{
    private TextView curTv, eleTv, pfTv, powTv, appow, temTv;
    private boolean isRun = false;
    private int mBoxId = 0;

    public BoxTotalCst(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.box_total_cst, this);
        curTv = view.findViewById(R.id.cur_tv);
        eleTv = view.findViewById(R.id.ele_tv);
        pfTv = view.findViewById(R.id.pf_tv);
        powTv = view.findViewById(R.id.pow_tv);
        appow = view.findViewById(R.id.appow_tv);
        temTv = view.findViewById(R.id.tem_tv);

        new Timers().start(500);
    }

    public void setBoxId(int id) {
        mBoxId = id;
    }


    private void updateData() {
        DevDatas devData  = LoginStatus.getPacket(mBoxId).data;

        int value = devData.line.cur.value.addData();
        String str = value + "A";
        curTv.setText(str);

        value = devData.line.ele.addData();
        str = value + "KWh";
        eleTv.setText(str);

        value = devData.line.pf.averData();
        str = value + "";
        pfTv.setText(str);

        value = devData.line.pow.addData();
        str = value + "KW";
        powTv.setText(str);

        value = devData.line.apPow.addData();
        str = value +"KVA";
        appow.setText(str);

        value = devData.env.tem.value.maxData();
        str = value + "°C";
        temTv.setText(str);
    }

    private class Timers extends HanderTimer {
        @Override
        public void timeout() {
            if(!isRun) {
                isRun = true;
                updateData();
                isRun = false;
            }
        }
    }
}
