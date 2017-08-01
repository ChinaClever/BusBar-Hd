package com.clever.www.busbar.dp.data.hash.slave;

import android.util.Log;

import com.clever.www.busbar.dp.data.packages.devinfo.DevAddr;
import com.clever.www.busbar.dp.data.packages.devinfo.DevInfo;
import com.clever.www.busbar.net.data.packages.NetDataDomain;


/**
 * Author: lzy. Created on: 16-11-8.
 */

public class DevInfoSave {
    private static final int DEV_CMD_DEV_NAME = 1; // 设备名称
    private static final int DEV_CMD_DEV_MODE = 2; //设备工作模式
    private static final int DEV_CMD_DEV_TYPE = 3; //设备型号
    private static final int DEV_CMD_DEV_AREA = 1; // 机房
    private static final int DEV_CMD_DEV_GROUP = 2;
    private static final int DEV_CMD_DEV_CAB = 3; // 机柜
    private HashSlaveCom mCommon = new HashSlaveCom();


    /**
     * @brief 保存设备工作模式
     * @param type
     * @param data
     */
    private void devMode(DevInfo dev, NetDataDomain data) {
        int mode = data.data.get(0); /*主从模式*/
        if(mode >= 0)
            dev.ms = (byte) mode;
    }

    /**
     * @brief 设备信息保存
     * @param data
     */
    private void hashDevTypeSave(DevInfo dev,NetDataDomain data) {
        int fc = data.fn[1] & 0x0f;
        switch (fc)
        {
            case DEV_CMD_DEV_NAME: // 设备工作名称
                mCommon.devStrSave(dev.name, data);
                break;

            case DEV_CMD_DEV_MODE: // 设备工作模式
                devMode(dev, data);
                break;

            case DEV_CMD_DEV_TYPE: // 设备型号
                mCommon.devStrSave(dev.typeStr, data);
                break;

        }
    }


    /**
     * @brief DEV设备地址
     * @param addr
     * @param data
     */
    private void hashDevAddr(DevAddr addr, NetDataDomain data)
    {
        int fc = data.fn[1] & 0x0f;
        switch (fc)
        {
            case DEV_CMD_DEV_AREA: // 区
                mCommon.devStrSave(addr.idc, data);
                break;

            case DEV_CMD_DEV_GROUP: // 组
                mCommon.devStrSave(addr.room, data);
                break;

            case DEV_CMD_DEV_CAB: //机柜
                mCommon.devStrSave(addr.cab, data);
                break;
        }
    }


    /**
     * @brief 设备信息保存
     * @param info
     * @param data
     */
    public void pduDevInfoSave(DevInfo info, NetDataDomain data)
    {
        int fc = data.fn[1] >> 4;
        switch (fc)
        {
            case DevCmdConstants.DEV_CMD_DEVTYPE: // 设备类型
                hashDevTypeSave(info, data);
                break;

            case DevCmdConstants.DEV_CMD_DEVADDR: // 设备地址
                hashDevAddr(info.addr, data);
                break;

            default:
                Log.d("lzy", "pdu_hashDevInfo_save err");
                break;
        }

    }

}
