package com.clever.www.busbar.boxline;

import com.clever.www.busbar.common.rate.RateEnum;
import com.clever.www.busbar.common.timer.HanderTimer;
import com.clever.www.busbar.dp.data.packages.devdata.DevDatas;
import com.clever.www.busbar.login.LoginStatus;

import java.util.List;

/**
 * Created by Lzy on 17-8-3.
 */

public class BoxLineUpdate {
    BoxLineAdapter mAdapter = null;
    private boolean isRun = false;
    private List<BoxLineItem> mItems = null;
    private int mBoxID=0;

    public BoxLineUpdate() {
        new Timers().start(500);
    }

    public void setData(BoxLineAdapter adapter, List<BoxLineItem> list, int id) {
        mAdapter = adapter;
        mItems = list;
        mBoxID = id;
    }

    private void checkBoxNum() {
        int num = LoginStatus.getPacket(mBoxID).lineNum;

        if(num != mItems.size()) {
            mItems.clear();
            if(num > 0) {
                for (int i = 0; i < num; ++i) {
                    BoxLineItem item = new BoxLineItem(i);
                    mItems.add(item);
                }
            }
        }
    }

    private void setUpdateData() {
        DevDatas packet = LoginStatus.getPacket(mBoxID).data;

        for(int i=0; i<mItems.size(); ++i) {
            BoxLineItem item = mItems.get(i);

            String name = (char)(('A' + i)) + "";
            item.setName(name);

            item.setVol(packet.line.vol.value.get(i) / RateEnum.VOL.getValue());
            item.setCur(packet.line.cur.value.get(i) / RateEnum.CUR.getValue());
            item.setPow(packet.line.pow.get(i) / RateEnum.POW.getValue() );
            item.setPl(packet.loop.pl.get(i) / RateEnum.PF.getValue());
            item.setPf(packet.line.pf.get(i) / RateEnum.PF.getValue());
            item.setEle(packet.line.ele.get(i) / RateEnum.ELE.getValue());
            item.setCurThd(packet.loop.curThd.get(i) / RateEnum.PF.getValue());
            item.setTem(packet.env.tem.value.get(i));
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
