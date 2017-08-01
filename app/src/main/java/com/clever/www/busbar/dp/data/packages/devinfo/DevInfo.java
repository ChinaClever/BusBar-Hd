package com.clever.www.busbar.dp.data.packages.devinfo;

import com.clever.www.busbar.dp.data.packages.base.DevStrBase;

/**
 * Created by lzy on 16-9-2.
 */
public class DevInfo {
    public int ms = 0;
    public DevStrBase name = new DevStrBase(); //设备名称
    public DevStrBase typeStr = new DevStrBase(); //设备型号
    public DevAddr addr = new DevAddr(); //设备地址
}
