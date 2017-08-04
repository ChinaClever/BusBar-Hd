package com.clever.www.busbar.dp.data.hash.data;

import java.util.ArrayList;
import java.util.List;

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

    public static void clear() {
        List<Integer> list = new ArrayList<>();
        mHashData.list(list);
        for (int i=0; i<list.size(); ++i) {
            int id = list.get(i);
            mHashData.del(id);
        }
    }

    public static int getBoxNUm(int id) {
        return mHashData.get(id).size();
    }


}
