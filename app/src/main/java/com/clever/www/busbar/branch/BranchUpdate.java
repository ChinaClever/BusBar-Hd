package com.clever.www.busbar.branch;

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

public class BranchUpdate {
    BranchAdapter mAdapter = null;
    private boolean isRun = false;
    private List<BranchItem> mItems = null;


    public BranchUpdate() {
        new Timers().start(500);
    }

    public void setData(BranchAdapter adapter, List<BranchItem> list) {
        mAdapter = adapter;
        mItems = list;
    }


    private void checkBoxNum() {
        int busId = LoginStatus.login_devNum;
        int boxNum = BusHashTable.getBoxNum(busId);

        if(boxNum != mItems.size()) {
            mItems.clear();
            if(boxNum > 0) {
                for (int i = 1; i < boxNum; ++i) {
                    BranchItem item = new BranchItem(i);
                    mItems.add(item);
                }
            }
        }
    }

    private void setUpdateData() {
        int busId = LoginStatus.login_devNum;
        BoxDataHash boxDataHash = BusHashTable.getHash().get(busId);

        for(int i=0; i<mItems.size(); ++i) {
            BranchItem item = mItems.get(i);
            int id = item.getId();
            DevDataPacket packet = boxDataHash.getPacket(id);

            String name = packet.devInfo.name.get();
            if(name.isEmpty())
                name = "iBox-" + id;
            item.setName(name);

            item.setStatus(packet.status);

            int len = packet.data.line.cur.value.size();
            for (int j=0; j<len; ++j) {
                item.setCur(j, packet.data.line.cur.value.get(j) / RateEnum.CUR.getValue());
                item.setEle(j, packet.data.line.ele.get(j) / RateEnum.ELE.getValue());
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
