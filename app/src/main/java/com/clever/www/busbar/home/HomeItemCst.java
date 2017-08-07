package com.clever.www.busbar.home;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clever.www.busbar.R;
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
    private int mBoxID=0;

    public HomeItemCst(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.home_item_cst, this);
        idTv = view.findViewById(R.id.id_tv);
        curTv = view.findViewById(R.id.cur_tv);

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


    private void updateData() {
        if(LoginStatus.getLogin()) {
            checkBoxNum();

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
