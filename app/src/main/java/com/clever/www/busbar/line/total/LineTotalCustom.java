package com.clever.www.busbar.line.total;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.clever.www.busbar.R;
import com.clever.www.busbar.common.rate.RateEnum;
import com.clever.www.busbar.common.timer.HanderTimer;
import com.clever.www.busbar.dp.data.packages.devdata.DevObjData;
import com.clever.www.busbar.line.linethd.LineThdActivity;
import com.clever.www.busbar.login.LoginStatus;


public class LineTotalCustom extends LinearLayout {
    private TextView rateTv;
    private PieChartCst pieVol, pieCur, piePow;
    private Context mContext;
    private int mLine=0, mId=0;

    public LineTotalCustom(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        new Timers().start(500);

        View view = LayoutInflater.from(context).inflate(R.layout.line_total_custom, this);
        rateTv = view.findViewById(R.id.rate_tv);

        pieVol = view.findViewById(R.id.pie_vol);
        pieCur = view.findViewById(R.id.pie_cur);
        piePow = view.findViewById(R.id.pie_pow);

        initRadiogroup();
        Button btn = view.findViewById(R.id.thd_btn);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                LineThdActivity.actionStart(mContext, 0);
            }
        });
    }

    protected void initRadiogroup()
    {
        RadioGroup radgroup = (RadioGroup) findViewById(R.id.radioGroup);
        radgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btnA: mLine = 0; break;
                    case R.id.btnB: mLine = 1; break;
                    case R.id.btnC: mLine = 2; break;
                }
            }
        });
    }

    protected void setRadioBtn()
    {
        int i = ((mId++) / 10) %3;
        int checkedId = R.id.btnA;
        switch (i) {
            case 0: checkedId = R.id.btnA; break;
            case 1: checkedId = R.id.btnB; break;
            case 2: checkedId = R.id.btnC; break;
        }
        RadioButton radbtn = (RadioButton) findViewById(checkedId);
        radbtn.setChecked(true);
    }


    private void updateData() {
        String str = "---";

        if(LoginStatus.getLogin()) {
            DevObjData packet = LoginStatus.getPacket(0).data.loop;

            int value = packet.cur.value.get(mLine);
            int curMax = packet.cur.max.get(mLine);
            pieCur.setColor(packet.cur.alarm.get(mLine));
            pieCur.setValue(value, curMax, RateEnum.CUR.getValue(), "A");

            value =  packet.vol.value.get(mLine);
            int volMax = packet.vol.max.get(mLine);
            pieVol.setColor(packet.vol.alarm.get(mLine));
            pieVol.setValue(value, volMax, 1, "V");

            value =  packet.pow.get(mLine);
            int max = (curMax * volMax )/10;
            piePow.setValue(value, max, RateEnum.POW.getValue(), "KW");

            str = packet.rate.averData() + "Hz";
            rateTv.setText(str);
        } else {
            rateTv.setText(str);

            pieCur.setValue(-1, 100, RateEnum.CUR.getValue(), "A");
            pieVol.setValue(-1, 100, 1, "V");
            piePow.setValue(-1, 100, RateEnum.POW.getValue(), "KW");
        }

        setRadioBtn();
    }

    private class Timers extends HanderTimer {
        @Override
        public void timeout() {
            updateData();
        }
    }


}
