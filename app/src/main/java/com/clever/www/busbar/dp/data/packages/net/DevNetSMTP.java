package com.clever.www.busbar.dp.data.packages.net;

import com.clever.www.busbar.dp.data.packages.base.DevStrBase;

/**
 * Created by lzy on 16-9-2.
 */
public class DevNetSMTP {
    public DevStrBase usr = new DevStrBase(); // SMTP账号
    public DevStrBase pwd = new DevStrBase(); // 密码

    public DevStrBase server = new DevStrBase();// SMTP服务器:
    public int port = 25; // 端口

    public DevStrBase mode = new DevStrBase(); // 认证方式
    public DevStrBase test = new DevStrBase(); // SMTP设置测试 接收账号
}
