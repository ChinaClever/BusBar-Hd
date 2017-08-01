package com.clever.www.busbar.dp.data.hash.slave;

import com.clever.www.busbar.dp.data.hash.read.BusHashRead;
import com.clever.www.busbar.dp.data.packages.DevDataPacket;
import com.clever.www.busbar.net.data.datadone.NetConstants;
import com.clever.www.busbar.net.data.packages.NetDataDomain;
import com.clever.www.busbar.net.data.packages.NetDataPacket;

/**
 * Created by lzy on 16-9-18.
 * 数据保存
 */
public class DevHashDataSlave {
    private HashSlaveCom mCommon = new HashSlaveCom();
    private BusHashRead mBusHashRead = new BusHashRead();
    private DevDataSlave mDevDataSlave = new DevDataSlave();
    private DevInfoSave mDevInfo = new DevInfoSave();
    private DevNetSave mDevNet = new DevNetSave();
    private OutputsNameSave mOutput = new OutputsNameSave();
    private DevUsersSave mDevUsr = new DevUsersSave();

    private void hashDataFunction(DevDataPacket dev, NetDataDomain data) {
        int fc = data.fn[0]; //根据功能码，进行分支处理
        switch (fc) {
            case DevCmdConstants.DEV_CMD_STATUS: // 设备工作状态
                dev.status = data.data.get(0);
                break;

            case DevCmdConstants.DEV_CMD_LOOP: //回路参数
            case DevCmdConstants.DEV_CMD_LINE: //设备相参数
            case DevCmdConstants.DEV_CMD_OUTPUT: // 设备输出位
            case DevCmdConstants.DEV_CMD_ENV: // 环境数据
                mDevDataSlave.hashDevDataSlave(dev.data, data);
                break;

            case DevCmdConstants.DEV_CMD_DEVINFO: // 设备信息
                mDevInfo.pduDevInfoSave(dev.devInfo, data);
                break;

            case DevCmdConstants.DEV_CMD_DEVUSR: // 设备用户信息
                mDevUsr.hashDevUsrSave(dev.users, data);
                break;

            case DevCmdConstants.DEV_CMD_DEVNET: // 设备网络信息
                mDevNet.devNetSave(dev.net,data);
                break;

            case DevCmdConstants.DEV_CMD_OUTPUT_NAME: // 输出位名称
                mOutput.outputName(dev.outputName, data);
                break;
        }
    }

    /**
     * Hash数据保存的入口函数
     * 主要是针对代号段的处理，pdu_dev_code
     *      对数据进行网络传输类型、传输方向、及版本的验证;
     *      根据IP、代号段中的设备类型、设备号来查找对应的设备数据节点
     * @param ip 设备IP
     * @param dataPacket 数据包
     */
    public void slave(String ip, NetDataPacket dataPacket) {
        boolean ret = mCommon.checkTranType(dataPacket.code.type, dataPacket.code.trans); // 网络传输类型、传输方向验证
        if(ret) {
            int devType = mCommon.getDevCode(dataPacket.code.devCode);  // 获取设备类型码
            if(devType > 0) {
                DevDataPacket dev = mBusHashRead.get(dataPacket.dataDomain.num, dataPacket.dataDomain.addr);
                if(dataPacket.code.version == NetConstants.DATA_DEV_VERSION) { // 版本号的验证
                    dev.boxNum = dataPacket.dataDomain.addr; // 设备地址
                    dev.ip.set(ip); // 设备IP
                    dev.offLine = NetConstants.DEV_OFF_LINE_TIME; // 设备在线标识

                    hashDataFunction(dev, dataPacket.dataDomain);
                }
            }
        }
    }

}
