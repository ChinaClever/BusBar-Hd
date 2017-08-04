package com.clever.www.busbar.line.total;


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


public class LineTotalCustom extends LinearLayout {
    private TextView rateTv, tempTv, curTv, volTv, powTv;

    public LineTotalCustom(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.line_total_custom, this);
        rateTv = view.findViewById(R.id.rate_tv);
        tempTv = view.findViewById(R.id.tem_tv);
        curTv = view.findViewById(R.id.cur_tv);
        volTv = view.findViewById(R.id.vol_tv);
        powTv = view.findViewById(R.id.pow_tv);

        new Timers().start(500);

    }

    private void updateData() {
        String str = "---";

        if(LoginStatus.getLogin()) {
            DevDatas packet = LoginStatus.getPacket(0).data;

            str = packet.line.cur.value.addData() + "A";
            curTv.setText(str);

            str = packet.line.vol.value.averData() + "V";
            volTv.setText(str);

            str = packet.line.pow.addData() + "KWh";
            powTv.setText(str);

            str = packet.env.tem.value.maxData() + "C";
            tempTv.setText(str);

            str = packet.line.rate.averData() + "Hz";
            rateTv.setText(str);
        } else {
            curTv.setText(str);
            volTv.setText(str);
            powTv.setText(str);
            tempTv.setText(str);
            rateTv.setText(str);
        }
    }

    private class Timers extends HanderTimer {
        @Override
        public void timeout() {
            updateData();
        }
    }


}
