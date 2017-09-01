package com.clever.www.busbar.line.total;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clever.www.busbar.R;
import com.clever.www.busbar.common.rate.RateEnum;
import com.clever.www.busbar.common.timer.HanderTimer;
import com.clever.www.busbar.dp.data.packages.devdata.DevDatas;
import com.clever.www.busbar.login.LoginStatus;


public class LineTotalCustom extends LinearLayout {
    private TextView rateTv, tempTv;
    private PieChartCst pieVol, pieCur, piePow;


    public LineTotalCustom(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.line_total_custom, this);
        rateTv = view.findViewById(R.id.rate_tv);
        tempTv = view.findViewById(R.id.tem_tv);

        pieVol = view.findViewById(R.id.pie_vol);
        pieCur = view.findViewById(R.id.pie_cur);
        piePow = view.findViewById(R.id.pie_pow);

        new Timers().start(500);
    }



    private void updateData() {
        String str = "---";

        if(LoginStatus.getLogin()) {
            DevDatas packet = LoginStatus.getPacket(0).data;

            int value = packet.line.cur.value.addData();
            int curMax = packet.line.cur.max.addData();
            pieCur.setValue(value, curMax, RateEnum.CUR.getValue(), "A");

            value =  packet.line.vol.value.averData();
            int volMax = packet.line.vol.max.maxData();
            pieVol.setValue(value, volMax, 1, "V");

            value =  packet.line.pow.addData();
            int max = (curMax * volMax )/10;
            piePow.setValue(value, max, RateEnum.POW.getValue(), "KW");

            str = packet.env.tem.value.maxData() + "C";
            tempTv.setText(str);

            str = packet.line.rate.averData() + "Hz";
            rateTv.setText(str);
        } else {
            tempTv.setText(str);
            rateTv.setText(str);

            pieCur.setValue(-1, 100, RateEnum.CUR.getValue(), "A");
            pieVol.setValue(-1, 100, 1, "V");
            piePow.setValue(-1, 100, RateEnum.POW.getValue(), "KW");
        }
    }

    private class Timers extends HanderTimer {
        @Override
        public void timeout() {
            updateData();
        }
    }


}
