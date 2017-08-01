package com.clever.www.busbar.net.udp.recv;

import android.util.Log;

import com.clever.www.busbar.net.data.recv.NetDataList;

/**
 * Created by Lzy on 17-7-27.
 */

public class UdpRecvThread {
    private static final int MAX_SIZE = 1024;
    private UdpRecvSocket mSocket = null;
    private byte[] data = new byte[MAX_SIZE]; // 创建字节数组，指定接收的数据包的大小
    private NetDataList mNetDataList = NetDataList.bulid();
    private static final String TAG = "lzy";

    public void init(int port) {
        mSocket = new UdpRecvSocket();
        boolean ret = mSocket.init(port);
        if(ret) {
            recvThread();
        } else {
            Log.d(TAG, "init: lzy error!! " + port );
        }
    }


    private void recv() {
        StringBuilder ip = new StringBuilder();
        int len = mSocket.readData(ip, data);
        mNetDataList.addUdp(ip.toString(), data, len);
    }

    private void recvThread() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1);
                        recv();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();
    }
}
