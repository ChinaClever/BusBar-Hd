package com.clever.www.busbar.dp.data.packages;

import com.clever.www.busbar.dp.data.packages.base.DevStrBase;
import com.clever.www.busbar.dp.data.packages.devdata.DevDevData;
import com.clever.www.busbar.dp.data.packages.devinfo.DevInfo;
import com.clever.www.busbar.dp.data.packages.net.DevNetInfo;

/**
 * Author: lzy. Created on: 16-9-29.
 * PDU设备数据包
 *      1、包含整个设备数据信息
 */
public class DevDataPacket {
    public int state = 0; // 工作状态 ==0 正常

    public int boxNum = 0;  // 设备号

    public int offLine = 0; // 离线标识
    public int loopNum = 0; //回路数量

    public DevStrBase ip = new DevStrBase(); //设备IP
    public DevStrBase name = new DevStrBase(); //设备名称

    public DevDevData data = new DevDevData(); //设备数据
    public DevNetInfo net = new DevNetInfo();
    public DevInfo info = new DevInfo(); // 设备型号、设备地址
}
