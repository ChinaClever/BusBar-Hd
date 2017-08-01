package com.clever.www.busbar.dp.data.packages.net;

import com.clever.www.busbar.dp.data.packages.base.DevStrBase;

/**
 * Created by lzy on 16-9-2.
 */
public class DevNetSNMP {
    public boolean en; // V1、V2是否启用
    public DevStrBase get = new DevStrBase(); // GET共同体:
    public DevStrBase set = new DevStrBase(); //SET共同体

    public DevStrBase trap1 = new DevStrBase(); //Trap1地址
    public DevStrBase trap2 = new DevStrBase(); //Trap2地址

    public DevStrBase server = new DevStrBase();//SNMP服务器位置
    public DevStrBase node = new DevStrBase();//SNMP节点

    public boolean enV3; // SNMP v3
    public DevStrBase usr = new DevStrBase(); // 账号
    public DevStrBase pwd = new DevStrBase(); // 密码:
    public DevStrBase key = new DevStrBase(); // 私钥:
}
