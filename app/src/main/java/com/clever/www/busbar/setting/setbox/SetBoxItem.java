package com.clever.www.busbar.setting.setbox;

import com.clever.www.busbar.dp.data.packages.base.DevDataBase;

/**
 * Created by Lzy on 17-8-16.
 */

public class SetBoxItem {
    private int id = 0;
    private String name;
    private int num = 3;
    private DevDataBase mCurData = new DevDataBase();
    private DevDataBase mTemData = new DevDataBase();

    public SetBoxItem(int id) {
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

    public boolean setCur(int local, int data) {
       return mCurData.set(local, data);
    }

    public int getCur(int local) {
          return mCurData.get(local);
    }

    public boolean setTem(int local, int data) {
        return mTemData.set(local, data);
    }

    public int getTem(int local) {
        return mTemData.get(local);
    }

}


