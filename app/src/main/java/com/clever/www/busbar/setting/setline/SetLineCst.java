package com.clever.www.busbar.setting.setline;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.clever.www.busbar.R;
import com.clever.www.busbar.common.timer.HanderTimer;
import com.clever.www.busbar.dp.data.packages.DevDataPacket;
import com.clever.www.busbar.dp.data.packages.devdata.DevDataUnit;
import com.clever.www.busbar.login.LoginStatus;
import com.clever.www.busbar.setting.setcom.SetComActivity;

/**
 * Created by Lzy on 17-8-14.
 */

public class SetLineCst extends LinearLayout{
    private int mLine = 0;
    private TextView idTv1, idTv2, lineTv, curTv, volTv;
    private ProgressBar curProgress, volProgress;
    private Context mContext;
    private boolean isRun = false;

    public SetLineCst(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.set_line_cst, this);
        initView(view);
        mContext = context;
        new Timers().start(500);
    }

    private void initView(View view) {
        idTv1 = view.findViewById(R.id.id_tv_1);
        idTv2 = view.findViewById(R.id.id_tv_2);

        lineTv = view.findViewById(R.id.line_tv);
        curTv = view.findViewById(R.id.cur_tv);
        volTv = view.findViewById(R.id.vol_tv);

        curProgress = view.findViewById(R.id.cur_progress_bar);
        curProgress.setOnClickListener(onClickListener);

        volProgress = view.findViewById(R.id.vol_progress_bar);
        volProgress.setOnClickListener(onClickListener);
    }

    public void setLine(int line) {
        mLine = line;

        String str = (2*line+1) + "";
        idTv1.setText(str);

        str = (2*line+2)+ "";
        idTv2.setText(str);

        str = "L" + (line+1);
        lineTv.setText(str);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            switch (v.getId()) {
                case R.id.cur_progress_bar:
                    SetComActivity.actionStart(mContext, 0, mLine, 1);
                    break;

                case R.id.vol_progress_bar:
                    SetComActivity.actionStart(mContext, 0, mLine, 2);
                    break;
            }
        }
    };

    private void checkAlarmStatus(DevDataUnit dataUnit, TextView tv) {
        if(dataUnit.alarm.get(mLine) > 0) {
            tv.setTextColor(Color.RED);
        } else if(dataUnit.crAlarm.get(mLine) > 0) {
            tv.setTextColor(Color.YELLOW);
        } else {
            tv.setTextColor(Color.BLACK);
        }
    }

    private void setProgressBar(DevDataUnit dataUnit , ProgressBar progress) {
//        int min = dataUnit.min.get(mLine);
//        if(min>0)  progress.setMin(min);

        int max = dataUnit.max.get(mLine);
        progress.setMax(max);

        int value = dataUnit.value.get(mLine);
        progress.setProgress(value);

        int crValue = dataUnit.crMax.get(mLine);
        progress.setSecondaryProgress(crValue);
    }

    private void curDataUpdate() {
        DevDataPacket dataPacket = LoginStatus.getPacket(0);
        DevDataUnit dataUnit = dataPacket.data.line.cur;

        double cur = dataUnit.value.get(mLine) /10.0;
        String str = cur + "A";
        if(cur < 0)
            str = "---";
        curTv.setText(str);

        setProgressBar(dataUnit, curProgress);
        checkAlarmStatus(dataUnit, curTv);
    }

    private void volDataUpdate() {

        DevDataPacket dataPacket = LoginStatus.getPacket(0);
        DevDataUnit dataUnit = dataPacket.data.line.vol;

        int value = dataUnit.value.get(mLine);
        String str = value + "V";
        if(value < 0)
            str = "---";
        volTv.setText(str);

        setProgressBar(dataUnit, volProgress);
        checkAlarmStatus(dataUnit, volTv);
    }




    private void updateData() {
        if(LoginStatus.getLogin()) {

            curDataUpdate();
            volDataUpdate();
        }
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
