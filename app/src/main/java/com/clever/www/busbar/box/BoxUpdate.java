package com.clever.www.busbar.box;

import com.clever.www.busbar.common.rate.RateEnum;
import com.clever.www.busbar.common.timer.HanderTimer;
import com.clever.www.busbar.dp.data.packages.devdata.DevEnvData;
import com.clever.www.busbar.dp.data.packages.devdata.DevObjData;
import com.clever.www.busbar.login.LoginStatus;

import java.util.List;

/**
 * Created by Lzy on 17-8-8.
 */

public class BoxUpdate {
    BoxAdapter mAdapter = null;
    private boolean isRun = false;
    private List<BoxItem> mItems = null;
    private int mBoxID=0;

    public BoxUpdate() {
        new Timers().start(500);
    }

    public void setData(BoxAdapter adapter, List<BoxItem> list, int id) {
        mAdapter = adapter;
        mItems = list;
        mBoxID = id;
    }

    private void checkBoxNum() {
        int num = LoginStatus.getPacket(mBoxID).data.line.num;

        if(num != mItems.size()) {
            mItems.clear();
            if(num > 0) {
                for (int i = 0; i < num; ++i) {
                    BoxItem item = new BoxItem(i);
                    mItems.add(item);
                }
            }
        }
    }

    private void setUpdateData() {
        DevObjData objData = LoginStatus.getPacket(mBoxID).data.line;
        DevEnvData envObjData = LoginStatus.getPacket(mBoxID).data.env;


        for(int i=0; i<mItems.size(); ++i) {
            BoxItem item = mItems.get(i);

            String name = "Loop " + (i+1);
            item.setName(name);

            item.setCur(objData.cur.value.get(i) / RateEnum.CUR.getValue());
            item.setCurALarm(objData.cur.alarm.get(i));
            item.setCurCrALarm(objData.cur.crAlarm.get(i));

            item.setVol(objData.vol.value.get(i));
            item.setVolALarm(objData.vol.alarm.get(i));
            item.setVolCrALarm(objData.vol.crAlarm.get(i));

            item.setEle(objData.ele.get(i) / RateEnum.ELE.getValue());
            item.setPow(objData.pow.get(i) / RateEnum.POW.getValue());
            item.setAppow(objData.apPow.get(i) / RateEnum.POW.getValue());
            item.setPf(objData.pf.get(i) / RateEnum.PF.getValue());
            item.setSw(objData.sw.get(i));

            item.setTem(envObjData.tem.value.get(i));
            item.setTemALarm(envObjData.tem.alarm.get(i));
            item.setTemCrALarm(envObjData.tem.crAlarm.get(i));
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
