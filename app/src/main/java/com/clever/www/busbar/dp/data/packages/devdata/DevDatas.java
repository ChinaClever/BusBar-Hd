package com.clever.www.busbar.dp.data.packages.devdata;

/**
 * Created by lzy on 16-9-2.
 * PDU数据类，主要包括
 *  1、相数据
 *  2、输出位数据
 *  3、环境数据
 */
public class DevDatas {
    public DevObjData line = new DevObjData(); // 相数据 相若能耗散
    public DevObjData loop = new DevObjData(); // 相数据 相若能耗散
    public DevEnvData env = new DevEnvData(); // 环境数据
}
