package com.clever.www.busbar.dp.data.hash.read;


import com.clever.www.busbar.dp.data.hash.data.BoxDataHash;
import com.clever.www.busbar.dp.data.hash.data.BusDataHash;
import com.clever.www.busbar.dp.data.hash.data.BusHashTable;
import com.clever.www.busbar.dp.data.packages.DevDataPacket;

/**
 * Created by lzy on 16-9-20.
 * Hash读取类，根据设备类型、设备IP、级联编号获取设备数据结构体
 */
public class BusHashRead {
    private BusDataHash mHashData = BusHashTable.getHash();

    public DevDataPacket get(int bus, int box) {
        BoxDataHash boxDataHash =  mHashData.get(bus); // 根据设备代号获取对应的Hash节点
        return boxDataHash.get(box); // 根据不同的设备IP 获取对应的Hash节点
    }

    public int getBoxNum(int bus){
        BoxDataHash boxDataHash =  mHashData.getDev(bus);
        return boxDataHash.size();
    }

}
