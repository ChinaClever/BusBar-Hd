package com.clever.www.busbar.home;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.clever.www.busbar.R;
import com.clever.www.busbar.box.BoxActivity;
import com.clever.www.busbar.common.timer.HanderTimer;
import com.clever.www.busbar.dp.data.hash.data.BoxDataHash;
import com.clever.www.busbar.dp.data.hash.data.BusHashTable;
import com.clever.www.busbar.dp.data.packages.DevDataPacket;
import com.clever.www.busbar.login.LoginStatus;

/**
 * Created by Lzy on 17-8-4.
 */

public class HomeItemCst extends LinearLayout{
    private TextView idTv, curTv;
    private ImageView loopIv;
    private int mBoxID=0;
    private Context mContext;

    public HomeItemCst(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.home_item_cst, this);
        idTv = view.findViewById(R.id.id_tv);
        curTv = view.findViewById(R.id.cur_tv);
        loopIv = view.findViewById(R.id.loopIv);

        mContext = context;
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int busId = LoginStatus.login_devNum;
                int boxNum = BusHashTable.getBoxNUm(busId);
                if(mBoxID < boxNum) {
                    BoxActivity.actionStart(mContext, mBoxID);
                } else {
                    Toast.makeText(mContext, R.string.box_offline_info, Toast.LENGTH_SHORT).show();
                }
            }
        });

        new Timers().start(500);
    }

    public void setBoxId(int boxId) {
        mBoxID = boxId;

        String str = (mBoxID +1) +"";
        idTv.setText(str);
    }

    private void checkBoxNum() {
        int busId = LoginStatus.login_devNum;
        int boxNum = BusHashTable.getBoxNUm(busId);

//        if(mBoxID >= boxNum) {
//            setVisibility(View.GONE);
//        }

//        Log.d("lzy", "checkBoxNum: " + mBoxID + "  " + boxNum);
    }


    private void setAlarmIcon(ImageView iv, int status) {
        int id = 0;

        switch (status) {
            case 0: // 离线
                id = R.drawable.home_box_offine;
                break;

            case 1: // 在线
                id = R.drawable.home_box_online;
                break;

            case 2: // 报警
                id = R.drawable.home_box_cralarm;
                break;

            case 3:
                id = R.drawable.home_box_alarm;
                break;
        }
        iv.setImageResource(id);
    }


    private void checkALarmStatus() {
        int alarm = 0;
        int busId = LoginStatus.login_devNum;
        int boxNum = BusHashTable.getBoxNUm(busId);
        if(mBoxID < boxNum) {
            DevDataPacket packet = LoginStatus.getPacket(mBoxID);
            if(packet.offLine > 0) {
                alarm = 1;
            }

            if (packet.curAlarm == 1) {
                alarm = 2;
            }

            if (packet.curAlarm == 2) {
                alarm = 3;
            }

            setAlarmIcon(loopIv, alarm);
        }
    }


    private void updateData() {
        if(LoginStatus.getLogin()) {
            checkBoxNum();
            checkALarmStatus();

            int busId = LoginStatus.login_devNum;
            BoxDataHash boxDataHash = BusHashTable.getHash().get(busId);
            DevDataPacket packet = boxDataHash.getPacket(mBoxID);

            double cur = packet.data.line.cur.value.addData();
            String str = cur + "A";
            curTv.setText(str);

        } else {
            curTv.setText("---");
        }
    }

    private class Timers extends HanderTimer {
        @Override
        public void timeout() {
            updateData();
        }
    }

}
