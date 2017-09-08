package com.clever.www.busbar.setting.setTem;

import com.clever.www.busbar.dp.data.packages.base.DevDataBase;

/**
 * Created by Lzy on 17-8-26.
 */

public class SetTemItem {
    private int id = 0;
    private String name;
    private int num = 3;
    private DevDataBase mTemData = new DevDataBase();
    private DevDataBase mALarmData = new DevDataBase();
    private DevDataBase mCrALarmData = new DevDataBase();

    public SetTemItem(int id) {
        this.id = id ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean setTem(int local, int data) {
        return mTemData.set(local, data);
    }

    public int getTem(int local) {
        return mTemData.get(local);
    }

    public boolean setAlarm(int local, int data) {
        return mALarmData.set(local, data);
    }

    public int getAlarm(int local) {
        return mALarmData.get(local);
    }

    public boolean setCrAlarm(int local, int data) {
        return mCrALarmData.set(local, data);
    }

    public int getCrAlarm(int local) {
        return mCrALarmData.get(local);
    }
}
