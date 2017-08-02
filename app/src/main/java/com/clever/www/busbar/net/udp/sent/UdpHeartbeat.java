package com.clever.www.busbar.net.udp.sent;

/**
 * Author: lzy. Created on: 16-11-7.
 */

public class UdpHeartbeat {
    private static final int PORT = 8080;
    private static final String MSG = "Clever-Manager PDU APP Server OK!";
    private UdpSend udp = new UdpSend();
    private static UdpHeartbeat mUdpHeartbeat = null;
    private boolean isRun = false;
    private String mIpAddr;

    public static UdpHeartbeat bulid() {
        if(mUdpHeartbeat == null) {
            mUdpHeartbeat = new UdpHeartbeat();
        }

        return mUdpHeartbeat;
    }

    public void startThread(String ip) {
        mIpAddr = ip;
        isRun = true;
        sentThread();
    }

    public void stopThread() {
        isRun = false;
    }

    private void sentThread() {
        new Thread() {
            @Override
            public void run() {
                while (isRun) {
                    sent();
                }
            }
        }.start();
    }

    private void sent() {
        try {
            udp.sent(mIpAddr, PORT, MSG.getBytes(), MSG.length());
//            udp.dbSent(PORT, MSG.getBytes(), MSG.length());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
