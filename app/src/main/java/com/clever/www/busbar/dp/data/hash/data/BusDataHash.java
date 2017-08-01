package com.clever.www.busbar.dp.data.hash.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lzy on 16-9-13.
 */
public class BusDataHash {
    private Map<Integer, BoxDataHash> mMap = new HashMap<>();

    public int size() {
        return mMap.size();
    }

    public void del(int num){
        mMap.remove(num);
    }

    /**
     * 获取设备数据，
     * @param ip 设备ip
     * @return 没有则返回空
     */
    public BoxDataHash getDev(int num) {
        return mMap.get(num);
    }

    private BoxDataHash add(int num) {
        BoxDataHash dev = new BoxDataHash();
        mMap.put(num, dev);
        return dev;
    }

    /**
     * 获取设备数据，没有则创建
     * @return
     */
    public BoxDataHash get(int num) {
        BoxDataHash dev = getDev(num);
        if(dev == null) {
            dev = add(num);
        }
        return dev;
    }

    /**
     * 列出所有设备IP
     * @return 设备数量
     */
    public int list(List<Integer> list) {
        for (Integer key : mMap.keySet()) {
            list.add(key);
        }

        return list.size();
    }
}
