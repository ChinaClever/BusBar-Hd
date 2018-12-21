package com.clever.www.busbar.dp.data.packages.devdata;

import com.clever.www.busbar.dp.data.packages.base.DevDataBase;

/**
 * Created by lzy on 16-9-2.
 * PDU数据对象类，主要包括
 *  电压、电流、功率、电能、功率因素、开关状态
 */
public class DevObjData {
    public int num = 3; //回路数量
    public DevDataUnit vol = new DevDataUnit(); // 电压
    public DevDataUnit cur = new DevDataUnit(); // 电流

    public DevDataBase pow = new DevDataBase(); // 功率
    public DevDataBase ele = new DevDataBase(); // 电能

    public DevDataBase pf = new DevDataBase(); // 功率因素
    public DevDataBase sw = new DevDataBase(); // 开关状态 0 表示未启用

    public DevDataBase apPow = new DevDataBase(); // 视在功率
    public DevDataBase rate = new DevDataBase(); // 谐波值

    public DevDataBase pl = new DevDataBase(); // 负载百分比
    public DevDataBase curThd = new DevDataBase(); // 电流谐波
    public DevDataBase volThd = new DevDataBase(); // 电压谐波

    public DevDataBase[] cThdArray = new DevDataBase[3];
    public DevDataBase[] vThdArray = new DevDataBase[3];
}
