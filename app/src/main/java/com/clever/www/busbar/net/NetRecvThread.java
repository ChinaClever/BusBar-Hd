package com.clever.www.busbar.net;

import com.clever.www.busbar.net.data.recv.NetDataReadThread;
import com.clever.www.busbar.net.udp.recv.UdpRecvListThreads;

/**
 * Created by Lzy on 17-7-28.
 */

public class NetRecvThread {
    private UdpRecvListThreads mUdpSocket = new UdpRecvListThreads();
    private NetDataReadThread mNet = new NetDataReadThread();

    public void initNet() {
        mUdpSocket.createSockets();
        new Thread(mNet).start();


//        UdpSend udp = new UdpSend();
//        String str = "luozhiyong";
//        udp.dbSent(8080, str.getBytes(), str.length());
//        new UdpHeartbeat().start();
//        new UdpSockList().createSocket();

//        mUdpSocket.createSocket();
    }

}
