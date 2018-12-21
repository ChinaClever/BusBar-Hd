package com.clever.www.busbar.line.linelist;

import com.clever.www.busbar.common.rate.RateEnum;
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

            String name = (char)(('A' + i)) + "";
            item.setName(name);

            item.setVol(packet.data.loop.vol.value.get(i));
            item.setVolALarm(packet.data.loop.vol.alarm.get(i));
            item.setVolCrALarm(packet.data.loop.vol.crAlarm.get(i));

            item.setCur(packet.data.loop.cur.value.get(i) / RateEnum.CUR.getValue());
            item.setCurALarm(packet.data.loop.cur.alarm.get(i));
            item.setCurCrALarm(packet.data.loop.cur.crAlarm.get(i));

            item.setPow(packet.data.loop.pow.get(i) / RateEnum.POW.getValue() );
            item.setPl(packet.data.loop.pl.get(i));
            item.setPf(packet.data.loop.pf.get(i) / RateEnum.PF.getValue());
            item.setEle(packet.data.loop.ele.get(i) / RateEnum.ELE.getValue());
            item.setCurThd(packet.data.loop.curThd.get(i) / RateEnum.PF.getValue());
            item.setVolThd(packet.data.loop.volThd.get(i) / RateEnum.PF.getValue());

            item.setTem(packet.data.env.tem.value.get(i));
            item.setTemALarm(packet.data.env.tem.alarm.get(i));
            item.setTemCrALarm(packet.data.env.tem.crAlarm.get(i));
        }
    }

    private void clearData() {
        int data = -1;
        for(int i=0; i<mItems.size(); ++i) {
            LineItem item = mItems.get(i);
            String name = (char)(('A' + i)) + "";
            item.setName(name);
            item.setVol(data);
            item.setCur(data);
            item.setPow(data);
            item.setPl(data);
            item.setPf(data);
            item.setEle(data);
            item.setCurThd(data);
            item.setVolThd(data);
            item.setTem(data);
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
