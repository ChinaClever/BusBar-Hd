package com.clever.www.busbar.line.linelist;

import com.clever.www.busbar.common.timer.HanderTimer;
import com.clever.www.busbar.dp.data.hash.data.BoxDataHash;
import com.clever.www.busbar.dp.data.hash.data.BusHashTable;
import com.clever.www.busbar.dp.data.packages.DevDataPacket;
import com.clever.www.busbar.login.LoginStatus;

import java.util.List;

/**
 * Created by Lzy on 17-8-4.
 */

public class LineUpdate {
    LineAdapter mAdapter = null;
    private boolean isRun = false;
    private List<LineItem> mItems = null;


    public LineUpdate() {
        new Timers().start(500);
    }

    public void setData(LineAdapter adapter, List<LineItem> list) {
        mAdapter = adapter;
        mItems = list;
    }



    private void setUpdateData() {
        int busId = LoginStatus.login_devNum;
        BoxDataHash boxDataHash = BusHashTable.getHash().get(busId);
        DevDataPacket packet = boxDataHash.getPacket(0);

        for(int i=0; i<mItems.size(); ++i) {
            LineItem item = mItems.get(i);

            String name ="L" + i;
            item.setName(name);

            item.setVol(packet.data.line.vol.value.get(i));
            item.setCur(packet.data.line.cur.value.get(i));
            item.setPow(packet.data.line.pow.get(i));
            item.setApPow(packet.data.line.apPow.get(i));
            item.setPf(packet.data.line.pf.get(i));
            item.setEle(packet.data.line.ele.get(i));
            item.setMaxCur(packet.data.line.cur.max.get(i));
        }
    }

    private void clearData() {
        int data = 0;
        for(int i=0; i<mItems.size(); ++i) {
            LineItem item = mItems.get(i);
            String name ="L" + i;
            item.setName(name);
            item.setVol(data);
            item.setCur(data);
            item.setPow(data);
            item.setApPow(data);
            item.setPf(data);
            item.setEle(data);
            item.setMaxCur(data);
        }
    }

    private void updateData() {
        if(LoginStatus.getLogin()) {
            setUpdateData();
        } else {
            clearData();
        }
        mAdapter.notifyDataSetChanged();
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
