package com.clever.www.busbar.dp.data.packages.devdata;

import com.clever.www.busbar.dp.data.packages.base.DevDataBase;

/**
 * Created by lzy on 16-9-2.
 * PDU环境数据类，主要包括
 *  1、温湿度、门禁、水浸、烟雾等
 */
public class DevEnvData {
    public DevDataUnit tem = new DevDataUnit(); // 温度
    public DevDataUnit hum = new DevDataUnit(); // 湿度

    public DevDataBase door = new DevDataBase(); // 门禁
    public DevDataBase water = new DevDataBase(); // 水浸
    public DevDataBase smoke = new DevDataBase(); // 烟雾
}
