package com.clever.www.busbar.net.udp.recv;

/**
 * Created by Lzy on 17-7-27.
 */

public class UdpRecvThread {
    private UdpRecvSocket mSocket = null;

    public void init(int port) {
        mSocket = new UdpRecvSocket();
        mSocket.init(port);
        recvThread();
    }


    private void recv() {

    }

    private void recvThread() {
        new Thread() {
            @Override
            public void run() {
                while (true)
                    recv();
            }
        }.start();
    }
}
