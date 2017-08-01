package com.clever.www.busbar.dp.data.packages.net;

import com.clever.www.busbar.dp.data.packages.base.DevStrBase;

/**
 * Created by lzy on 16-9-2.
 */
public class DevNetIPAddr {
    public byte mode = -1; /* 网络模式 0手动设置 1 自动获取*/
    public DevStrBase ip = new DevStrBase(); // iP
    public DevStrBase gw = new DevStrBase(); // 网关
    public DevStrBase mask = new DevStrBase(); // 子网码
    public DevStrBase dns = new DevStrBase(); // dns
    public DevStrBase dns2 = new DevStrBase(); // 备用dns
}
