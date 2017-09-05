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
            DevDataPacket packet = boxDataHash.getPacket(i);

            String name = packet.devInfo.name.get();
            if(name.isEmpty())
                name = "iBox-" + (i+1);
            item.setName(name);

            item.setStatus(packet.status);

            item.setVol(packet.data.line.vol.value.averData());
            item.setCur(packet.data.line.cur.value.addData() / RateEnum.CUR.getValue());
            item.setPow(packet.data.line.pow.addData()  / RateEnum.POW.getValue());
            item.setApPow(packet.data.line.apPow.addData()  / RateEnum.POW.getValue());
            item.setPf(packet.data.line.pf.averData() / RateEnum.PF.getValue());
            item.setEle(packet.data.line.ele.addData() / RateEnum.ELE.getValue());
            item.setTem(packet.data.env.tem.value.averData());
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
