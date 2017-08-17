package com.clever.www.busbar.setting.setbox;

import com.clever.www.busbar.common.timer.HanderTimer;
import com.clever.www.busbar.dp.data.packages.DevDataPacket;
import com.clever.www.busbar.dp.data.packages.devdata.DevDataUnit;
import com.clever.www.busbar.login.LoginStatus;

import java.util.List;

/**
 * Created by Lzy on 17-8-17.
 */

public class SetBoxUpdate {
    SetBoxAdapter mAdapter = null;
    private boolean isRun = false;
    private List<SetBoxItem> mItems = null;

    public SetBoxUpdate() {
        new Timers().start(500);
    }

    public void setData(SetBoxAdapter adapter, List<SetBoxItem> list) {
        mAdapter = adapter;
        mItems = list;
    }

    private void checkBoxNum() {
        int num = LoginStatus.getBoxNUm()-1;

        if(num != mItems.size()) {
            mItems.clear();
            if(num > 0) {
                for (int i = 0; i < num; ++i) {
                    SetBoxItem item = new SetBoxItem(i);
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

            SetBoxItem item = mItems.get(i);
            item.setName(name);

            int num = dataPacket.data.line.num;
            item.setNum(num);

            DevDataUnit dataUnit = dataPacket.data.line.cur;
            for(int j=0; j<num; ++j){
                item.setCur(j, dataUnit.value.get(j));
            }

            dataUnit = dataPacket.data.env.tem;
            for(int j=0; j<1; ++j){
                item.setTem(j, dataUnit.value.get(j));
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
