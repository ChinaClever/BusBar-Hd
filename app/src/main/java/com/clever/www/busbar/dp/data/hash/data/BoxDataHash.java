package com.clever.www.busbar.dp.data.hash.data;

import com.clever.www.busbar.dp.data.packages.DevDataPacket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lzy on 16-9-13.
 * PDU设备Hash表
 *  主要以设备号，保存设备数据
 */
public class BoxDataHash {
    private Map<Integer, DevDataPacket> mMap = new HashMap<>();
    public boolean isNew = false; // 是否是新设备

    public int size() {
        return mMap.size();
    }

    public void del(int num){
        mMap.remove(num);
    }

    /**
     * 获取设备数据包，
     * @param num 设备号
     * @return 没有则返回空
     */
    public DevDataPacket getPacket(int num) {
        return mMap.get(num);
    }

    private DevDataPacket add(int num) {
        DevDataPacket dataPacket = new DevDataPacket();
        mMap.put(num, dataPacket);
        return dataPacket;
    }

    /**
     * 获取设备数据包，没有则创建
     * @param num 设备号
     * @return
     */
    public DevDataPacket get(int num) {
        DevDataPacket dataPacket = getPacket(num);
        if(dataPacket == null) {
            dataPacket = add(num);
            isNew = true;
        } else {
            isNew = false;
        }

        return dataPacket;
    }

    /**
     * 获取主机的数据包
     * @return
     */
    public DevDataPacket getMaster() {
        return getPacket(0);
    }

    /**
     * 列出所有设备号
     * @return 设备数量
     */
    public int list(List<Integer> list) {
        for (Integer key : mMap.keySet()) {
            list.add(key);
        }

        return list.size();
    }


}
