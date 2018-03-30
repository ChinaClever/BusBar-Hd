package com.clever.www.busbar.dp.data.hash.slave;


import com.clever.www.busbar.dp.data.packages.base.DevDataBase;
import com.clever.www.busbar.dp.data.packages.devdata.DevDataUnit;
import com.clever.www.busbar.dp.data.packages.devdata.DevDatas;
import com.clever.www.busbar.dp.data.packages.devdata.DevEnvData;
import com.clever.www.busbar.dp.data.packages.devdata.DevObjData;
import com.clever.www.busbar.net.data.packages.NetDataDomain;

/**
 * Created by lzy on 16-9-18.
 */
public class DevDataSlave {
    private static final int DEV_CMD_VALUE =1;
    private static final int DEV_CMD_MIN = 2; //最小值
    private static final int DEV_CMD_MAX = 3; //最大值
    private static final int DEV_CMD_ALARM = 4; //报警
    private static final int DEV_CDM_CRMIN = 5;//临界最小值
    private static final int DEV_CMD_CRMAX = 6; //临界最大值
    private static final int DEV_CMD_CRALARM = 7; //临界报警

    private static final int DEV_CMD_NUM = 0; //数量
    private static final int DEV_CMD_CUR = 1; //电流
    private static final int DEV_CMD_VOL = 2; // 电压
    private static final int DEV_CMD_POW = 3; // 功率
    private static final int DEV_CMD_ELE = 4; // 电能
    private static final int DEV_CMD_PF = 5; // 功率因素
    private static final int DEV_CMD_SW = 6; // 开关状态 0 表示未启用
    private static final int DEV_CMD_CA = 7; // 排碳量
    private static final int DEV_CMD_RATE = 8;//电压频率
    private static final int DEV_CMD_APPOW = 9;//视在功率
    private static final int DEV_CMD_WAVE = 10;//波什

    private HashSlaveCom mCommon = new HashSlaveCom();

    // 数据单元处理 主要针对 当前值、最大、小值等数据的处理
    private void unitData(DevDataUnit unit, NetDataDomain data)  {
        DevDataBase ptr = null;
        int sizeBit = 2;

        int fc = data.fn[1] & 0x0f; // 处理功能码，第二字节的低四位数据
        switch (fc) {
            case DEV_CMD_VALUE:
                ptr = unit.value;
                break;

            case DEV_CMD_MIN:
                ptr = unit.min;
                break;

            case DEV_CMD_MAX:
                ptr = unit.max;
                break;

            case DEV_CMD_ALARM:
                sizeBit = 1;
                ptr = unit.alarm;
                break;

            case DEV_CDM_CRMIN:
                ptr = unit.crMin;
                break;

            case DEV_CMD_CRMAX:
                ptr = unit.crMax;
                break;

            case DEV_CMD_CRALARM:
                sizeBit = 1;
                ptr = unit.crAlarm;
                break;

            default:
                break;
        }

        if(ptr != null)
            mCommon.saveHashIntData(ptr, data.len, data.data, sizeBit);
    }

    // 设备对象数据的处理 主要电流、电压、功率、电能
    private void objData(DevObjData obj, NetDataDomain data){
        DevDataBase ptr = null;
        int sizeBit = 2;

        int fc = 0x0f & (data.fn[1] >> 4); // // 处理功能码，第二字节的高四位
        switch (fc){
            case DEV_CMD_NUM: // 数量
                obj.num = data.data.get(0);
                break;

            case DEV_CMD_CUR: // 电流
                unitData(obj.cur, data);
                break;

            case DEV_CMD_VOL: // 电压
                unitData(obj.vol, data);
                break;

            case DEV_CMD_POW: // 功率
                sizeBit = 4;
                ptr = obj.pow;
                break;

            case DEV_CMD_ELE: // 电能
                sizeBit = 4;
                ptr = obj.ele;
                break;

            case DEV_CMD_PF: // 功率因素
                ptr = obj.pf;
                break;

            case DEV_CMD_SW: // 开关状态
                sizeBit = 1;
                ptr = obj.sw;
                break;

            case DEV_CMD_CA: // 排碳量
                ptr = obj.carbon;
                break;

            case DEV_CMD_RATE: //电压频率
                ptr = obj.rate;
                break;

            case DEV_CMD_APPOW:
                ptr = obj.apPow;
                break;

            case DEV_CMD_WAVE:
                ptr = obj.wave;
                break;

            default:
                break;
        }

        if(ptr != null)
            mCommon.saveHashIntData(ptr, data.len, data.data, sizeBit);
    }

    // 环境数据的处理
    private void envData(DevEnvData env, NetDataDomain data)  {
        DevDataBase ptr = null;
        int sizeBit = 1;

        int fc = data.fn[1] >> 4; // // 处理功能码，第二字节的高四位
        switch (fc) {
            case DevCmdConstants.DEV_CMD_TEMP: // 温度
                unitData(env.tem, data);
                break;

            case DevCmdConstants.DEV_CMD_HUM: //湿度
                unitData(env.hum, data);
                break;

            default:
                break;
        }
        if(ptr != null)
            mCommon.saveHashIntData(ptr, data.len, data.data, sizeBit);
    }

    public void hashDevDataSlave(DevDatas dev, NetDataDomain data){
        int fc = data.fn[0]; //根据功能码，进行分支处理
        switch (fc) {
            case DevCmdConstants.DEV_CMD_LINE: //设备相参数
                objData(dev.line, data);
                break;

            case DevCmdConstants.DEV_CMD_LOOP: //设备相参数
                objData(dev.loop, data);
                break;

            case DevCmdConstants.DEV_CMD_ENV: // 环境数据
                envData(dev.env, data);
                break;
        }
    }
}
