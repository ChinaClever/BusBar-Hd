package com.clever.www.busbar.home;

import com.clever.www.busbar.common.timer.HanderTimer;
import com.clever.www.busbar.dp.data.hash.data.BusHashTable;
import com.clever.www.busbar.login.LoginStatus;

import java.util.List;

/**
 * Created by Lzy on 17-8-4.
 */

public class HomeUpdate {
    HomeAdapter mAdapter = null;
    private boolean isRun = false;
    private List<HomeItem> mItems = null;


    public HomeUpdate() {
        new Timers().start(500);
    }

    public void setData(HomeAdapter adapter, List<HomeItem> list) {
        mAdapter = adapter;
        mItems = list;
    }


    private void checkBoxNum() {
        int busId = LoginStatus.login_devNum;
        int boxNum = BusHashTable.getBoxNUm(busId);

        if(boxNum%2 == 1) boxNum++;
        if(boxNum != (mItems.size()*2))
        {
            mItems.clear();
            if(boxNum > 0) {
                for (int i = 0; i < boxNum; i += 2) {
                    HomeItem item = new HomeItem(i);
                    mItems.add(item);
                }
            }
        }
    }


    private void updateData() {
        if(LoginStatus.getLogin()) {
            checkBoxNum();
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
