package com.clever.www.busbar.box;

import com.clever.www.busbar.common.timer.HanderTimer;

import java.util.List;

/**
 * Created by Lzy on 17-8-3.
 */

public class BoxUpdate {
    BoxAdapter mAdapter = null;
    private boolean isRun = false;
    private List<BoxItem> mBoxItems = null;
    private int num = 0;

    public BoxUpdate() {
        new Timers().start(500);
    }

    public void setData(BoxAdapter adapter, List<BoxItem> list) {
        mAdapter = adapter;
        mBoxItems = list;
    }

    private void updateData() {
//        num++;
//        for(int i=0; i<5; ++i) {
//            mBoxItems.get(i).setName("lzy " + num);
//        }
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
