package com.clever.www.busbar.net.udp.recv;

/**
 * Created by Lzy on 17-7-27.
 */

public class UdpRecvListThreads {
    private static final int SOCK_NUM = 20;
    private static final int PORT = 8080;

    public void createSocket(final int num) {
        new Thread() {
            @Override
            public void run() {
                for(int i=0; i<num; ++i)
                    new UdpRecvThread().init(PORT + i);
            }
        }.start();
    }

    public void createSockets() {createSocket(SOCK_NUM);}
}
