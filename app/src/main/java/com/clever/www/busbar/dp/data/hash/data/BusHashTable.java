package com.clever.www.busbar.dp.data.hash.data;

/**
 * Created by lzy on 16-9-14.
 * PDU设备Hash表
 *  此Hash为全局变量
 */
public class BusHashTable {
    private static BusDataHash mHashData = new BusDataHash();

    /**
     * 获取表态哈希
     * @return
     */
    public static BusDataHash getHash() {
        return mHashData;
    }


}
