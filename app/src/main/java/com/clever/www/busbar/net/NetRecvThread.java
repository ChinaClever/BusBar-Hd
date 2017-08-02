package com.clever.www.busbar.net;

import com.clever.www.busbar.net.data.recv.NetDataReadThread;
import com.clever.www.busbar.net.udp.recv.UdpRecvListThreads;
import com.clever.www.busbar.net.udp.sent.UdpHeartbeat;

/**
 * Created by Lzy on 17-7-28.
 */

public class NetRecvThread {
    private UdpRecvListThreads mUdpSocket = new UdpRecvListThreads();
    private NetDataReadThread mNet = new NetDataReadThread();
    private UdpHeartbeat udpHeartbeat = new UdpHeartbeat();

    public void initNet() {
        mUdpSocket.createSockets();
        new Thread(mNet).start();

        // UDP 发送心跳包
//        udpHeartbeat.startThread("192.168.1.105");

//        UdpSend udp = new UdpSend();
//        String str = "luozhiyong";
//        udp.dbSent(8080, str.getBytes(), str.length());
//        new UdpHeartbeat().start();
//        new UdpSockList().createSocket();

//        mUdpSocket.createSocket();
    }

}
