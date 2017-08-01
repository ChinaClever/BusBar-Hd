package com.clever.www.busbar.dp.data.packages.devdata;


import com.clever.www.busbar.dp.data.packages.base.DevDataBase;

/**
 * Created by lzy on 16-9-2.
 * PDU数据单元，主要包括
 *  1、最大值、最小值、报警值
 *  2、临界下值，临界上值，临界报警值
 */
public class DevDataUnit {
    public DevDataBase value = new DevDataBase(); // 当前值

    public DevDataBase min = new DevDataBase(); // 最小值
    public DevDataBase max = new DevDataBase(); // 最大值
    public DevDataBase alarm = new DevDataBase(); // 报警值

    public DevDataBase crMin = new DevDataBase(); // 临界最小值
    public DevDataBase crMax = new DevDataBase(); // 临界最大值
    public DevDataBase crAlarm = new DevDataBase(); // 临界报警值
}
