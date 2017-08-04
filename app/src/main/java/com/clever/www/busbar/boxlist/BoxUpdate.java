package com.clever.www.busbar.boxlist;

import com.clever.www.busbar.common.timer.HanderTimer;
import com.clever.www.busbar.dp.data.hash.data.BoxDataHash;
import com.clever.www.busbar.dp.data.hash.data.BusHashTable;
import com.clever.www.busbar.dp.data.packages.DevDataPacket;
import com.clever.www.busbar.login.LoginStatus;

import java.util.List;

/**
 * Created by Lzy on 17-8-3.
 */

public class BoxUpdate {
    BoxAdapter mAdapter = null;
    private boolean isRun = false;
    private List<BoxItem> mItems = null;
    private int mLineId=0, mCount=0;

    public BoxUpdate() {
        new Timers().start(500);
    }

    public void setData(BoxAdapter adapter, List<BoxItem> list) {
        mAdapter = adapter;
        mItems = list;
    }

    private void checkBoxNum() {
        int busId = LoginStatus.login_devNum;
        int boxNum = BusHashTable.getBoxNUm(busId);

        if(boxNum != mItems.size()) {
            mItems.clear();
            if(boxNum > 0) {
                for (int i = 0; i < boxNum; ++i) {
                    BoxItem item = new BoxItem(i);
                    mItems.add(item);
                }
            }
        }
    }

    private void setUpdateData() {
        int busId = LoginStatus.login_devNum;
        BoxDataHash boxDataHash = BusHashTable.getHash().get(busId);
        if((++mCount % 6) == 0) // 每3抄r轮显
            mLineId++; // 相数

        for(int i=0; i<mItems.size(); ++i) {
            BoxItem item = mItems.get(i);
            DevDataPacket packet = boxDataHash.getPacket(i);

            String name = packet.devInfo.name.get();
            if(name.isEmpty()) name = "iBox-" + i;
            item.setName(name);

            int id = mLineId % packet.data.line.num;
            item.setLine(id);
            item.setVol(packet.data.line.vol.value.get(id));
            item.setCur(packet.data.line.cur.value.get(id));
            item.setEle(packet.data.line.ele.get(id));
            item.setTemp(packet.data.env.tem.value.get(id));
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
