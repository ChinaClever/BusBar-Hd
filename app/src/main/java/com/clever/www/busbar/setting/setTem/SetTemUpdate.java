package com.clever.www.busbar.setting.setTem;

import com.clever.www.busbar.common.timer.HanderTimer;
import com.clever.www.busbar.dp.data.packages.DevDataPacket;
import com.clever.www.busbar.dp.data.packages.devdata.DevDataUnit;
import com.clever.www.busbar.login.LoginStatus;

import java.util.List;

/**
 * Created by Lzy on 17-8-26.
 */


public class SetTemUpdate {
    SetTemAdapter mAdapter = null;
    private boolean isRun = false;
    private List<SetTemItem> mItems = null;

    public SetTemUpdate() {
        new Timers().start(500);
    }

    public void setData(SetTemAdapter adapter, List<SetTemItem> list) {
        mAdapter = adapter;
        mItems = list;
    }

    private void checkBoxNum() {
        int num = LoginStatus.getBoxNUm()-1;

        if(num != mItems.size()) {
            mItems.clear();
            if(num > 0) {
                for (int i = 0; i < num; ++i) {
                    SetTemItem item = new SetTemItem(i);
                    mItems.add(item);
                }
            }
        }
    }

    private void setUpdateData() {

        for(int i=0; i<mItems.size(); ++i) {
            DevDataPacket dataPacket = LoginStatus.getPacket(i+1);
            String name = dataPacket.devInfo.name.get();
            if(name.isEmpty())
                name = "iBox- " + (i+1);

            SetTemItem item = mItems.get(i);
            item.setName(name);

            int num = dataPacket.data.line.num;
            item.setNum(num);

            DevDataUnit dataUnit = dataPacket.data.env.tem;
            for(int j=0; j<num; ++j){
                item.setTem(j, dataUnit.value.get(j));
                item.setAlarm(j, dataUnit.alarm.get(j));
                item.setCrAlarm(j, dataUnit.crAlarm.get(j));
            }
        }
    }

    private void updateData() {
        if(LoginStatus.getLogin()) {
            checkBoxNum();
            setUpdateData();
        } else {
            mItems.clear();
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
